package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DeleteCheckBoxModal;
import utils.DeleteConfirmationModal;


@Log4j2
public class AdministrationPage extends BasePage {
    private static final By PROJECTS_ON_NAVIGATION_BAR = By.id("navigation-sub-projects");
    private static final String DELETE_ICON_PROJECT_LOCATOR = "//*[contains(text(),'%s')]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]";
    private static final String EDIT_PROJECT_ICON_LOCATOR = "//*[contains(text(),'%s')]/ancestor::tr/descendant::div[contains(@class,'icon-small-edit')]";

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Deleting project with title '{projectName}'")
    public void deleteProject(String projectName) {
        log.info("Deleting project with title '{}'", projectName);
        new Button(driver, By.xpath(String.format(DELETE_ICON_PROJECT_LOCATOR, projectName))).click();
    }

    @Step("Editing project with title '{projectName}'")
    public void editProject(String projectName) {
        log.info("Editing project with title '{}'", projectName);
        new Button(driver, By.xpath(String.format(EDIT_PROJECT_ICON_LOCATOR, projectName))).click();
    }

    @Step("Confirmation deleting project")
    public void confirmDeleteProject() {
        log.info("Confirmation deleting project");
        DeleteCheckBoxModal deleteCheckBoxModal = new DeleteCheckBoxModal(driver);
        deleteCheckBoxModal.checkCheckbox();
        DeleteConfirmationModal deleteConfirmationModal = new DeleteConfirmationModal(driver);
        deleteConfirmationModal.confirmDelete();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECTS_ON_NAVIGATION_BAR));
    }
}
