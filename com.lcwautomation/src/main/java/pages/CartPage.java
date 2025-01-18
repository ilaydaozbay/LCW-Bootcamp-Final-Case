package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Locators for cart elements
    By mycartButton = By.xpath("//body/div[@id='root']/div[@class='page-wrapper']/div[@id='header__container']/header[@class='header header--low']/div[@class='header__middle']/div[@class='header__middle__right']/div[@class='header-section']/div[2]");
    private By productNameLocator = By.cssSelector(".rd-cart-item-main-info.pl-15"); // Product name in cart
    private By productColorLocator = By.cssSelector(".rd-cart-item-color"); // Product color in cart
    private By productQuantityLocator = By.cssSelector("input[value='1']"); // Product quantity
    private By productPriceLocator = By.cssSelector(".rd-cart-item-price.mb-15");// Product price
    private By paymentSummaryPriceLocator = By.cssSelector("div[class='price-info-area'] span[class='total-grand-box-amount']");// Payment summary price

    // Navigate to the cart page
    @Step("Navigate to the cart page")
    public CartPage navigateToCart() throws InterruptedException {
        WebElement mycartButtonElement = driver.findElement(mycartButton);
        mycartButtonElement.click();
        return this;
    }

    // Verify the product name in the cart
    @Step("Verify the product name in the cart")
    public CartPage verifyProductName(String expectedName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the product name from the cart
        String actualName = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator)).getText();

        // Extract keywords from the expected name for validation
        List<String> expectedKeywords = extractKeywords(expectedName);

        // Verify that at least one expected keyword is in the actual name
        boolean isMatch = expectedKeywords.stream().anyMatch(actualName::contains);

        // Assert and log the result
        Assert.assertTrue(isMatch,
                "Sepet ekranındaki ürün adı, ürün detay sayfasındaki adı içermiyor! " +
                        "\nBeklenen: " + expectedName +
                        "\nAnahtar Kelimeler: " + expectedKeywords +
                        "\nBulunan: " + actualName);

        return this;
    }

    // Verify the product color in the cart
    @Step("Verify the product color in the cart")
    public CartPage verifyProductColor(String expectedColor) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the product color from the cart
        String actualColor = wait.until(ExpectedConditions.visibilityOfElementLocated(productColorLocator)).getText();

        // Extract keywords from the expected color for validation
        List<String> expectedKeywords = extractKeywords(expectedColor);

        // Verify that at least one expected keyword is in the actual color
        boolean isMatch = expectedKeywords.stream().anyMatch(actualColor::contains);

        // Assert and log the result
        Assert.assertTrue(isMatch,
                "Sepet ekranındaki ürün rengi, ürün detay sayfasındaki rengi içermiyor! " +
                        "\nBeklenen: " + expectedColor +
                        "\nAnahtar Kelimeler: " + expectedKeywords +
                        "\nBulunan: " + actualColor);

        return this;
    }

    // Verify the product quantity in the cart
    @Step("Verify the product quantity in the cart")
    public CartPage verifyProductQuantity(String expectedQuantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the product quantity from the cart
        String actualQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantityLocator)).getAttribute("value").trim();

        //The "Adet: " part is removed to get only the number, and surrounding spaces are trimmed
        String expectedQuantityNumber = expectedQuantity.replace("Adet: "," ").trim();

        // Extract keywords from the expected quantity for validation
        List<String> expectedKeywords = extractKeywords(expectedQuantity);

        // Assert and log the result
        Assert.assertEquals(actualQuantity, expectedQuantityNumber,
                "Sepet ekranındaki ürün adedi, ürün detay sayfasındaki adedi içermiyor! " +
                        "\nBeklenen: " + expectedQuantityNumber +
                        "\nBulunan: " + actualQuantity);

        return this;
    }

    // Verify the product price in the cart
    @Step("Verify the product price in the cart matches payment summary")
    public CartPage verifyProductPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the product price and payment summary price
        String productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator)).getText();
        String paymentSummaryPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSummaryPriceLocator)).getText();

        // Verify that the product price matches the payment summary price
        if (!paymentSummaryPrice.contains(productPrice)) {
            throw new AssertionError("Ürün fiyatı ödeme adımındaki fiyatla eşleşmiyor. Ürün Fiyatı: " + productPrice + ", Ödeme Fiyatı: " + paymentSummaryPrice);
        }

        return this;
    }

    // Extract keywords from a given product information
    @Step("Extract keywords from product information")
    private List<String> extractKeywords(String productInfo) {
        // Split the product info by spaces and filter out words shorter than 2 characters
        return Arrays.stream(productInfo.split(" "))
                .filter(word -> word.length() > 2)
                .collect(Collectors.toList());
    }

    // Add a product to the cart
    @Step("Add a product to the cart")
    public CartPage addProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Locate and click the add product button
        WebElement plusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[normalize-space()='+'])[1]")));
        plusButton.click();
        Thread.sleep(2000);
        return this;
    }

    // Remove a product from the cart
    @Step("Remove a product from the cart")
    public CartPage removeProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Locate and click the remove product button
        WebElement minusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[normalize-space()='-'])[1]")));
        minusButton.click();
        Thread.sleep(2000);
        return this;
    }
}
