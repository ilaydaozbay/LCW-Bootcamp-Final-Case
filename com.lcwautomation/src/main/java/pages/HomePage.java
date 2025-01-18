package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage{

    public HomePage (WebDriver driver){
        super(driver);
    }

    // Locator for the login icon
    By loginIcon = By.id("user_1_");
    // Locator for the login button
    By loginBtn = By.xpath("//a[@class='cart-action__btn cart-action__btn--bg-blue']");


    @Step("Open LCW Home Page..")
    public LoginPage goToLoginPage(){
        BasePage base = new BasePage(driver);
        base.initializeDriver();

        // Actions class is used for mouse hover actions
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(loginIcon)).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));

        // Clicking the login button
        loginButton.click();

        return new LoginPage(driver);
    }
}
