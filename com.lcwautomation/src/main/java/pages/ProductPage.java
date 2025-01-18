package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage{

    public String detailName; //Product name on the product detail page
    public String detailColor; //Product color on the product detail page
    public String detailQuantity; //Product quantity on the product detail page

    public ProductPage(WebDriver driver){
        super(driver);
    }

    // Locators for product elements
    By product = By.xpath("(//div[@class='product-card product-card--one-of-4'])[4]");
    By option5to6_age = By.xpath("//button[contains(text(),'5-6 Yaş')]");
    By addToCartButton = By.xpath("//button[normalize-space()='SEPETE EKLE']");
    By productDetailName = By.xpath("//h1[@class='product-title']");
    By productDetailColor = By.xpath("//div[@class='col-xs-7 col-sm-9']//div[@class='product-code']");
    By productDetailQuantity = By.xpath("//div[contains(@class,'drop-down-menu__wrapper')]//div[1]//div[1]//div[1]//span[1]//p[2]");
    By transparentOverlay = By.xpath("/html/body");


    // Select a product
    @Step("Select product and navigate to the product detail page")
    public ProductPage selectProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the top of the page
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);

        // Wait until the product element is visible, then scroll to it
        WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(product));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productElement);
        Thread.sleep(1000);

        // Click the product element to navigate to the product page
        productElement.click();

        return this;
    }

    // Close the transparent overlay
    @Step("Close the transparent overlay if visible")
    public ProductPage closeTransparentOverlay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if the overlay is visible
        List<WebElement> overlays = driver.findElements(transparentOverlay);

        // Close the overlay if visible
        if (!overlays.isEmpty() && overlays.get(0).isDisplayed()) {
            overlays.get(0).click();
            System.out.println("Overlay kapatıldı.");
        } else {
            System.out.println("Overlay bulunamadı veya görünür değil.");
        }
        return this;
    }

    // Add the product to the cart and retrieve quantity from the popup
    @Step("Add product to the cart and retrieve quantity from the popup")
    public ProductPage addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Extract product details (name, color)
        extractProductDetails(wait);

        // Select the age option (5-6 Yaş)
        selectAgeOption(wait);

        // Add the product to the cart
        clickAddToCartButton(wait);

        // After adding to cart, retrieve the quantity from the popup
        detailQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailQuantity)).getText();

        return this;
    }

    // Helper method to extract product details (name, color)
    @Step("Extract product details (name, color)")
    private void extractProductDetails(WebDriverWait wait) {
        // Get the product name
        detailName = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailName)).getText();

        // Get the product color
        detailColor = wait.until(ExpectedConditions.visibilityOfElementLocated(productDetailColor)).getText();
    }

    // Helper method to select the age option
    @Step("Select the age option (5-6 Yaş)")
    private void selectAgeOption(WebDriverWait wait) throws InterruptedException {
        WebElement option5to6_ageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(option5to6_age));
        option5to6_ageElement.click();
        Thread.sleep(1000); // Wait for UI update
    }

    // Helper method to click the "Add to Cart" button
    @Step("Click the 'Add to Cart' button")
    private void clickAddToCartButton(WebDriverWait wait) throws InterruptedException {
        WebElement addToCartButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        addToCartButtonElement.click();
        Thread.sleep(1000); // Wait for UI update
    }
}
