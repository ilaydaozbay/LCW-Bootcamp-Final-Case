package tests;

import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.FilterPage;
import pages.HomePage;
import pages.LoginPage;

public class FilterTest extends BaseTest{

    HomePage homepage;
    LoginPage loginpage;
    CategoryPage categorypage;
    FilterPage filterpage;

    @Test
    public void selectFilter() throws InterruptedException {
        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        categorypage = new CategoryPage(driver);
        filterpage = new FilterPage(driver);

        homepage.goToLoginPage().logintoLCW("ilaydaozbaay@gmail.com", "LCW_Test_Automation1");
        categorypage.selectCategoryType("Çocuk&Bebek","Kız Çocuk (6-14 YAŞ)","Mont ve Kaban");
        filterpage.selectFilters();
    }
}
