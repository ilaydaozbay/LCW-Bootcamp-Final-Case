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

public class FavoritesPage extends BasePage{

    public FavoritesPage(WebDriver driver){
        super(driver);
    }

    // Locators for fav elements
    By addFavoritesBtn = By.xpath("//i[@class='fa fa-heart-o']");
    By favoritesIcon = By.xpath("//a[@href='/favorilerim']");
    By favProductNameLocator = By.cssSelector(".product-card__title");

    // Add product to favorites
    @Step("Add product to favorites")
    public FavoritesPage addToFavorites() throws InterruptedException {

        // Find and click the add to favorites button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addFavoritesBtnElement = wait.until(ExpectedConditions.presenceOfElementLocated(addFavoritesBtn));
        addFavoritesBtnElement.click();
        Thread.sleep(5000);
        return this;
    }

    // Navigate to the favorites page
    @Step("Navigate to the favorites page")
    public FavoritesPage navigateToFavorites() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find and click the favorites icon
        WebElement favoritesIconElement = wait.until(ExpectedConditions.presenceOfElementLocated(favoritesIcon));
        favoritesIconElement.click();
        return this;
    }

    // Verify the product name in the favorites
    @Step("Verify the product name in the favorites")
    public FavoritesPage verifyFavoriteProductName(String expectedFavName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the product name from the favorites
        String actualFavoriteName = wait.until(ExpectedConditions.visibilityOfElementLocated(favProductNameLocator)).getText();

        // Extract keywords from the expected fav product name
        List<String> expectedKeywords = extractKeywords(expectedFavName);

        // Verify that at least one expected keyword is in the actual fav name
        boolean isMatch = expectedKeywords.stream().anyMatch(actualFavoriteName::contains);

        Assert.assertTrue(isMatch,
                "Favoriler ekranındaki ürün adı, ürün detay sayfasındaki adı içermiyor! " +
                        "\nBeklenen: " + expectedFavName +
                        "\nAnahtar Kelimeler: " + expectedKeywords +
                        "\nBulunan: " + actualFavoriteName);

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
}
