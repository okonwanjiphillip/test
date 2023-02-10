package base;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import enums.TargetTypeEnum;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import test.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestUtils extends TestBase{

    /**
     * @description This method is used to get a screenshot and convert it to base 64
     */
    public static String getScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File scrFile = ts.getScreenshotAs(OutputType.FILE);

        String encodedBase64 = null;
        try (FileInputStream fileInputStreamReader = new FileInputStream(scrFile)) {
            byte[] bytes = new byte[(int) scrFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }

         return "data:image/png;base64," + encodedBase64;
    }

    /**
     * @description This method is used to add screenshot to the Report
     */
    public static void addScreenShot() {
        String screenshotPath = TestUtils.getScreenshot();
        testInfo.get().addScreenCaptureFromBase64String(screenshotPath);
    }

    /**
     * @description This method is used to get browser name and version for the Report purposes
     */
    public static String checkBrowser() {
        // Get Browser name and version.
        Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getVersion();

        // return browser name and version.
        return browserName + " " + browserVersion;
    }

    /**
     * @description This method is used to clear input fields and send keys
     */
    public static void sendKeys(By locator, String text) {
        getDriver().findElement(locator).click();
        getDriver().findElement(locator).clear();
        getDriver().findElement(locator).sendKeys(text);
    }

    /**
     * @description This method clicks on elements and also elements that are overlapped by other elements
     */
//    public static void clickElement(String type, String element) {
//        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
//        WebElement clickThis;
//        TargetTypeEnum targetTypeEnum = TargetTypeEnum.valueOf(type);
//        clickThis = switch (targetTypeEnum) {
//            case ID -> getDriver().findElement(By.id(element));
//            case NAME -> getDriver().findElement(By.name(element));
//            case CSSSELECTOR -> getDriver().findElement(By.cssSelector(element));
//            case XPATH -> getDriver().findElement(By.xpath(element));
//            case CLASSNAME -> getDriver().findElement(By.className(element));
//        };
//        ex.executeScript("arguments[0].click()", clickThis);
//    }

    /**
     * @description to check if the expected text is present in the page. For the purpose
     * of this automation it produces flaky test as the expected texts are constantly changing.
     * This method works best when expected texts are constant.
     */
    public static void assertSearchText(By locator, String value) {

        StringBuilder verificationErrors = new StringBuilder();
        String text = getDriver().findElement(locator).getText();

        try {
            Assert.assertEquals(text, value);
            testInfo.get().log(Status.INFO, value + " found");
        } catch (Error e) {
            verificationErrors.append(e);
            String verificationErrorString = verificationErrors.toString();
            testInfo.get().error(value + " not found");
            testInfo.get().error(verificationErrorString);
        }
    }

    /**
     * @return  Retuns a string "newD"
     * @description Method returns a given future date, this method focuses on monthly increases.
     */
    public static String getFutureDate(int futureTimePeriod) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        testInfo.get().info("Current Date = " + calendar.getTime());

        calendar.add(Calendar.MONTH, futureTimePeriod);
        Date newDate = calendar.getTime();
        String newD = formatter.format(newDate);

        testInfo.get().info("Updated Date = " + calendar.getTime());
        return newD;
    }

    /**
     * @description This method is used to return a list of items on a string
     */
    public static void printList(int count, String validLocation, By path) {
        String flag = null;
        for (int i = 1; i <= count; i++) {
            try {
                WebElement records = getDriver().findElement(path);
                if (records != null) {
                    flag = getDriver().findElement(path).getText();
                    Assert.assertTrue(flag.contains(validLocation));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag != null) {
            boolean neededText = flag.contains(validLocation);
            if (!neededText) {
                testInfo.get().error(validLocation + " text is not present in the list.");
            } else {
                testInfo.get().info(validLocation + " text is present in the list.");
            }
        }
    }

    /**
     * @description to scroll to a particular element on the page.
     */
//    public static void scrollToElement(String type, String element) {
//        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
//        WebElement scrollDown = null;
//        TargetTypeEnum targetTypeEnum = TargetTypeEnum.valueOf(type);
//        scrollDown = switch (targetTypeEnum) {
//            case ID -> getDriver().findElement(By.id(element));
//            case NAME -> getDriver().findElement(By.name(element));
//            case CSSSELECTOR -> getDriver().findElement(By.cssSelector(element));
//            case XPATH -> getDriver().findElement(By.xpath(element));
//            case CLASSNAME -> getDriver().findElement(By.className(element));
//        };
//
//        jse.executeScript("arguments[0].scrollIntoView();", scrollDown);
//        TestUtils.getScreenshot();
//    }

    /*public static void getList() {
        String flag = null;
        for (int i = 1; i <= count; i++) {
            try {
                WebElement records = getDriver().findElement(path);
                if (records != null) {
                    flag = getDriver().findElement(path).getText();
                    Assert.assertTrue(flag.contains(validLocation));
                }
                if (flag != null) {
                    boolean neededText = flag.contains(validLocation);
                    if (!neededText) {
                        testInfo.get().error(validLocation + " text is not present in the list.");
                    } else {
                        testInfo.get().info(validLocation + " text is present in the list.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * @description This method extracts an integer from a list
     */
//    public static int listOfProperties() {
//        String list = getDriver().findElement(By.xpath(Search.TEXT)).getText();
//        String[] word = list.split(" ");
//        String word1 = word[1];
//        return Integer.parseInt(word1);
//    }

    /**
     * @description This method closes the cookies modal
     */
    /*public static void closeModal() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);

        header("Close Cookie Modal");

        TestUtils.addScreenShot();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-policy-title")));
        assertSearchText("ID", "onetrust-policy-title", "Manage cookie preferences");
        clickElement("ID", "onetrust-accept-btn-handler");
    }*/

    /**
     * @description Prints a header markup to the report
     */
    public static void header(String text) {
        Markup a = MarkupHelper.createLabel(text, ExtentColor.BLUE);
        testInfo.get().info(a);
    }

    /**
     * @description Prints a subheader markup to the report
     */
    public static void subHeader(String text) {
        Markup a = MarkupHelper.createLabel(text, ExtentColor.GREEN);
        testInfo.get().info(a);
    }

    /**
     * @description Prints a validation header markup to the report
     */
    public static void validationHeader(String text) {
        Markup a = MarkupHelper.createLabel(text, ExtentColor.BROWN);
        testInfo.get().info(a);
    }
}
