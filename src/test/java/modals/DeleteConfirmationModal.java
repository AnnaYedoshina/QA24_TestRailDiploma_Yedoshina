package modals;

import elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteConfirmationModal {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By CONFIRMATION_WINDOW = By.id("dialog-ident-deleteDialog");

    public DeleteConfirmationModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Step("Confirmation deleting project")
    public void confirmDelete() {
        new Button(driver, CONFIRM_DELETE_BUTTON).click();
    }
    @Step("Waiting for confirmation delete window to be displayed")
    public void waitForConfirmationWindowDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRMATION_WINDOW));
    }

}

