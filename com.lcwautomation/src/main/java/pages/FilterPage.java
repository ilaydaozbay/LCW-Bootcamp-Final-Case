package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterPage extends BasePage{

    public FilterPage (WebDriver driver){
        super(driver);
    }

    // Filter options
    By sizeFilterSection = By.xpath("//div[normalize-space()='Beden']");
    By sizeOption5to6 = By.xpath("//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[2]//div[1]//span[1]");
    By sizeOption6 = By.xpath("//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[3]//div[1]//span[1]");
    By sizeOption6to7 = By.xpath("//div[@class='collapsible-filter-container__content-area collapsible-filter-container__content-area--size-filter']//div[4]//div[1]//span[1]");

    // Color options
    By colorFilterSection = By.xpath("//div[normalize-space()='Renk']");
    By colorOptionBeige = By.xpath("//img[@src='https://img-lcwaikiki.mncdn.com//resource/images/icon/bej.png']");


    @Step("Scrolling to the 'Size' filter section")
    private void scrollToSizeFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement sizeFilter = wait.until(ExpectedConditions.presenceOfElementLocated(sizeFilterSection));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", sizeFilter);
    }

    @Step("Selecting the size 5-6 option")
    private void selectSize5to6() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement option5to6 = wait.until(ExpectedConditions.visibilityOfElementLocated(sizeOption5to6));
        option5to6.click();
        Thread.sleep(2000); // Wait for UI update
    }

    @Step("Selecting the size 6 option")
    private void selectSize6() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement option6 = wait.until(ExpectedConditions.visibilityOfElementLocated(sizeOption6));
        option6.click();
        Thread.sleep(2000); // Wait for UI update
    }

    @Step("Selecting the size 6-7 option")
    private void selectSize6to7() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement option6to7 = wait.until(ExpectedConditions.visibilityOfElementLocated(sizeOption6to7));
        option6to7.click();
        Thread.sleep(2000); // Wait for UI update
    }

    @Step("Scrolling to the 'Color' filter section")
    private void scrollToColorFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement colorFilter = wait.until(ExpectedConditions.presenceOfElementLocated(colorFilterSection));
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", colorFilter);
    }

    @Step("Selecting the Beige color option")
    private void selectColorBeige() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement optionBeige = wait.until(ExpectedConditions.visibilityOfElementLocated(colorOptionBeige));
        optionBeige.click();
    }

    @Step("Applying all filters: size and color")
    public FilterPage selectFilters() throws InterruptedException {
        scrollToSizeFilter();
        selectSize5to6();
        selectSize6();
        selectSize6to7();
        scrollToColorFilter();
        selectColorBeige();

        // Wait for 2 seconds after filtering and scroll the page to the top
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);

        return this;
    }
}
