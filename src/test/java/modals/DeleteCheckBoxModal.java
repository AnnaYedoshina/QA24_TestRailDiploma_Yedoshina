package modals;

import elements.Checkbox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteCheckBoxModal extends DeleteConfirmationModal{
    public DeleteCheckBoxModal(WebDriver driver) {
        super(driver);
    }
    private static final By DELETE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    @Step("Checking confirm delete checkbox")
    public void checkCheckbox() {
        new Checkbox(driver, DELETE_CHECKBOX).check();
    }
}
