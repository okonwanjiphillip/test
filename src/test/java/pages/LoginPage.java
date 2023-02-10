package pages;

import base.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By usernameField = By.name("email");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath(".//button[contains(@class, 'py-2 px-12') and text()='Login']");
    private final By loginButton1 = By.xpath(".//button[contains(text(),'Login')]");
    private final By emptyEmailError = By.xpath(".//p[contains(text(), 'email is required')]");
    String emptyEmailText = "email is required";
    private final By emptyPasswordError = By.xpath(".//p[contains(text(), 'password is required')]");
    String emptyPasswordText = "password is required";
    private final By invalidEmailError = By.xpath(".//p[contains(text(), 'email is not valid')]");
    String invalidEmailText = "email is not valid";

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username){
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton1).click();
    }

    public void clickLoginBtn() {
        driver.findElement(loginButton).click();
    }

    public void assertEmptyEmailError() {
        TestUtils.assertSearchText(emptyEmailError, emptyEmailText);
    }

    public void assertEmptyPasswordError() {
        TestUtils.assertSearchText(emptyPasswordError, emptyPasswordText);
    }

    public void assertInvalidEmailError() {
        TestUtils.assertSearchText(invalidEmailError, invalidEmailText);
    }
}