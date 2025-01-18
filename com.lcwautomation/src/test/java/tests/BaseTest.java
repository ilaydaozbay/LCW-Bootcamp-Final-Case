package tests;

import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class})
public class BaseTest {

    public static WebDriver driver;

    // Setup process before the tests
    @BeforeClass
    public void setup(){
        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Delete all cookies
        driver.manage().deleteAllCookies();
    }

    //Closing the browser
    @AfterClass
    public void quit(){
        // Quit the browser
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
