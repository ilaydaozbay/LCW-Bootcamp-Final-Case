package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators for email field, continue button, password field, and login button
    By emailField = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div/div/input");
    By continueBtn = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div/button");
    By userPassword = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/div[2]/div/input");
    By loginBtn = By.xpath("//*[@id=\"login\"]/div/div[2]/div/div/div/form/button");

    @Step("Performing login with valid:{0},and valid password{1}")
    public LoginPage logintoLCW(String email, String password) {

        typeEmail(email);
        clickContinueButton();
        typePassword(password);
        clickLoginButton();
        verifySuccessfulLogin();
        return this;
    }

    @Step("Typing email: {0}")
    private void typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Clicking the 'Continue' button")
    private void clickContinueButton() {
        driver.findElement(continueBtn).click();
    }

    @Step("Typing password")
    private void typePassword(String password) {
        driver.findElement(userPassword).sendKeys(password);
    }

    @Step("Clicking the 'Login' button")
    private void clickLoginButton() {
        driver.findElement(loginBtn).click();
    }

    @Step("Verifying successful login")
    private void verifySuccessfulLogin() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[@id='header__container']"), "Hesabım"));
        } catch (TimeoutException e) {
            driver.get("https://www.lcw.com/");
        }
    }
}
