package tests;

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
    public void emptyEmailTest() {
        var objLogin = new LoginPage(getDriver());
        objLogin.setUsername("");
        objLogin.setPassword("Password");
        objLogin.clickLoginBtn();
        objLogin.assertEmptyEmailError();
    }

    @Test
    public void emptyPasswordTest() {
        var objLogin = new LoginPage(getDriver());
        objLogin.setUsername("test@gmail.com");
        objLogin.setPassword("");
        objLogin.clickLoginBtn();
        objLogin.assertEmptyPasswordError();
    }

    @Test
    public void invalidEmailTest() {
        var objLogin = new LoginPage(getDriver());
        objLogin.setUsername("test");
        objLogin.setPassword("");
        objLogin.clickLoginBtn();
        objLogin.assertInvalidEmailError();
    }

    @Test
    public void testSuccessfulLogin() {
        var objLogin = new LoginPage(getDriver());
        objLogin.setUsername("tomsmith");
        objLogin.setPassword("SuperSecretPassword!");
        objLogin.clickLoginBtn();
    }
}
