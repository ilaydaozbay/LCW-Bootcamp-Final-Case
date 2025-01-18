package tests;

import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


public class LoginTest extends BaseTest {

    HomePage homepage;
    LoginPage loginpage;

    // Initial setup for the test
    @Test
    public void setupTest() {
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
    }

    // Test for logging into the system
    @Test
    public void loginToSystem() {
        HomePage homepage = new HomePage(driver);
        homepage.goToLoginPage().logintoLCW("ilaydaozbaay@gmail.com", "LCW_Test_Automation1");
    }
}
