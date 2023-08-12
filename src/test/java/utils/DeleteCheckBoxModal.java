package utils;

import elements.Checkbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteCheckBoxModal {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public DeleteCheckBoxModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    private static final By DELETE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    public void checkCheckbox() {
        new Checkbox(driver, DELETE_CHECKBOX).check();
    }
}
