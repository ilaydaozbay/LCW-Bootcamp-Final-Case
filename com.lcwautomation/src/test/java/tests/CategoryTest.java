package tests;

import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.LoginPage;


public class CategoryTest extends BaseTest{

    HomePage homepage = new HomePage(driver);
    LoginPage loginpage = new LoginPage(driver);
    CategoryPage categorypage = new CategoryPage(driver);

    @Test
    public void setupTest(){
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        categorypage = new CategoryPage(driver);
    }

    @Test
    public void selectCategory() throws InterruptedException {
        homepage.goToLoginPage().logintoLCW("ilaydaozbaay@gmail.com", "LCW_Test_Automation1");
        categorypage.selectCategoryType("Çocuk&Bebek","Kız Çocuk (6-14 YAŞ)","Mont ve Kaban");
    }
}
