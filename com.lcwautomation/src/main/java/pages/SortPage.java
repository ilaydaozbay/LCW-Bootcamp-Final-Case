package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SortPage extends BasePage{

    public SortPage(WebDriver driver){
        super(driver);
    }

    // Sort button (dropdown menu)
    By sortButton = By.xpath("//span[@class='dropdown-button__text']");

    // Best seller option in the sorting menu
    By bestSellerBtn = By.xpath("//a[normalize-space()='En çok satanlar']");

    @Step("Scrolling to the top of the page")
    private void scrollToTop() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000); // Wait for UI update
    }

    @Step("Clicking the 'Sort' dropdown button")
    private void clickSortButton() throws InterruptedException {
        WebElement sortButtonElement = driver.findElement(sortButton);
        sortButtonElement.click();
        Thread.sleep(1000); // Wait for dropdown to appear
    }

    @Step("Selecting the 'Best Seller' option from the sort dropdown")
    private void selectBestSeller() throws InterruptedException {
        WebElement bestSellerBtnElement = driver.findElement(bestSellerBtn);
        bestSellerBtnElement.click();
        Thread.sleep(2000); // Wait for the sorting action to complete
    }

    @Step("Performing sorting by Best Seller")
    public SortPage sortby() throws InterruptedException {
        scrollToTop();
        clickSortButton();
        selectBestSeller();
        return this;
    }
}
