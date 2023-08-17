package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private static final By EMAIL_INPUT = By.id("name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("button_primary");
    private static final By ERROR_MESSAGE = By.cssSelector(".loginpage-message");
    private static final By ERROR_TEXT = By.cssSelector(".error-text");

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    @Step("Logging in")
    public void logIn(String email, String password) {
        log.info(String.format("logging in with email= %s and password = %s", email, password));
        new Input(driver, EMAIL_INPUT).setValue(email);
        new Input(driver, PASSWORD_INPUT).setValue(password);
        new Button(driver, LOGIN_BUTTON).click();
    }

    @Step
    public String getErrorMessageText() {
        return driver.findElement(this.ERROR_MESSAGE).getText();

    }

    @Step
    public String getErrorText() {
        return driver.findElement(this.ERROR_TEXT).getText();

    }
}
