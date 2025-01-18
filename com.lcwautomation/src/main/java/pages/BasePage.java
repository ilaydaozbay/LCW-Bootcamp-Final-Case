package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    // Base URL of the application
    final public String baseURL = "https://www.lcw.com/";

    // Constructor to initialize the WebDriver instance
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @BeforeClass
    public WebDriver initializeDriver(){
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        // Navigate to the base URL
        driver.navigate().to(baseURL);
        // Maximize the browser window for better visibility
        driver.manage().window().maximize();
        // Clear all cookies to ensure a clean session
        driver.manage().deleteAllCookies();

        closeCookieBannerOnce();

        tdriver.set(driver);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    // Method to close the cookie banner if it's not already closed
    private void closeCookieBannerOnce() {

        Boolean cookieBannerClosed = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('cookieBannerClosed');"
        );


        if (cookieBannerClosed == null || !cookieBannerClosed) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement cookieBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookieseal-banner")));

                if (cookieBanner != null) {
                    WebElement closeButton = cookieBanner.findElement(By.id("cookieseal-banner-reject"));
                    closeButton.click();
                    System.out.println("Çerez banner'ı kapatıldı.");
                    ((JavascriptExecutor) driver).executeScript("window.localStorage.setItem('cookieBannerClosed', 'true');");
                }
            } catch (NoSuchElementException | TimeoutException e) {
                System.out.println("Çerez banner'ı bulunamadı veya zaten kapalı.");
            }
        } else {
            System.out.println("Çerez banner'ı zaten kapatılmış.");
        }
    }
}
