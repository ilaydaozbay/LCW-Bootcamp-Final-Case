package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class CategoryPage extends BasePage{

    public CategoryPage(WebDriver driver){
        super(driver);
    }

    // Locators for sub-category and product category
    By subCategory = By.xpath("//span[normalize-space()='KIZ ÇOCUK']");
    By productCategory= By.xpath("//section[contains(@class,'content-tab')]//a[contains(@class,'')][normalize-space()='Mont ve Kaban']");


    @Step("Selecting the main category: {0}")
    private void selectMainCategory(String mainCategoryName) {
        WebElement mainCategory = driver.findElement(By.xpath("//a[normalize-space()='ÇOCUK & BEBEK']"));
        Actions action = new Actions(driver);
        action.moveToElement(mainCategory).perform();
    }

    @Step("Selecting the sub-category: {0}")
    private void selectSubCategory(String subCategoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement subCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subCategory));
        Actions action = new Actions(driver);
        action.moveToElement(subCategoryElement).perform();
    }

    @Step("Selecting the product category: {0}")
    private void selectProductCategory(String subSubCategoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productCategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productCategory));
        productCategoryElement.click();
    }

    @Step("Verifying the page title after selection")
    private void verifyPageTitle() {
        Assert.assertEquals(driver.getTitle(), "Kız Çocuk Dış Giyim Fiyatları ve Modelleri | LCW");
    }

    @Step("Selecting category type with main category: {0}, sub-category: {1}, and product category: {2}")
    public CategoryPage selectCategoryType(String mainCategoryName, String subCategoryName, String subSubCategoryName) {
        selectMainCategory(mainCategoryName);
        selectSubCategory(subCategoryName);
        selectProductCategory(subSubCategoryName);
        verifyPageTitle();
        return this;
    }
}
