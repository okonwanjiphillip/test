package logins;

import base.TestBase;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {

    @Test
    public void emptyLoginDetails() {
        var objLogin = new LoginPage(getDriver());
        objLogin.clickLoginButton();
        objLogin.setUsername("");
        objLogin.setPassword("");
        objLogin.clickLoginBtn();
        objLogin.assertEmptyEmailError();
        objLogin.assertEmptyPasswordError();
    }

    @Test
    public void testSuccessfulLogin() {
        var objLogin = new LoginPage(getDriver());
        objLogin.setUsername("tomsmith");
        objLogin.setPassword("SuperSecretPassword!");
        objLogin.clickLoginBtn();
    }
}
