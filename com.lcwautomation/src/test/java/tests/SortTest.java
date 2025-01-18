package tests;

import org.testng.annotations.Test;
import pages.*;

public class SortTest extends BaseTest{

    HomePage homepage;
    LoginPage loginpage;
    CategoryPage categorypage;
    FilterPage filterpage;
    SortPage sortpage;

    @Test
    public void sortbyBestSellers() throws InterruptedException {

        homepage = new HomePage(driver);
        loginpage = new LoginPage(driver);
        categorypage = new CategoryPage(driver);
        filterpage = new FilterPage(driver);
        sortpage = new SortPage(driver);

        homepage.goToLoginPage().logintoLCW("ilaydaozbaay@gmail.com", "LCW_Test_Automation1");
        categorypage.selectCategoryType("Çocuk&Bebek","Kız Çocuk (6-14 YAŞ)","Mont ve Kaban");
        filterpage.selectFilters();
        sortpage.sortby();
    }
}
