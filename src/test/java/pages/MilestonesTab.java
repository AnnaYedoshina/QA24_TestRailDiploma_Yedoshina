package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.DeleteConfirmationModal;

import java.util.List;


@Log4j2
public class MilestonesTab extends BasePage {

    public MilestonesTab(WebDriver driver) {
        super(driver);
    }

    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Milestones')]");
    private static final By ADD_MILESTONE_BUTTON = By.id("navigation-milestones-add");
    private static final By MILESTONE_NAME = By.id("name");
    private static final By MILESTONE_DESCRIPTION = By.id("description_display");
    private static final By SUBMIT_MILESTONE_BUTTON = By.id("accept");
    private static final By MILESTONE_TAB = By.id("navigation-milestones");
    private static final By ALL_MILESTONES = By.cssSelector(".summary-title");
    private static final String MILESTONE_LOCATOR = "//div[contains(@class,'summary-title')]/descendant::a[text()='%s']";
    private static final String EDIT_MILESTONE_LOCATOR = "//div[contains(@class,'summary-title')]/a[text()='%s']/ancestor::div[contains(@class, 'row')]//a[contains(text(), 'Edit')]";
    private static final String DELETE_MILESTONE_LOCATOR = "//div[contains(@class,'summary-title')]/a[text()='%s']/ancestor::div[contains(@class, 'row')]//a[@class='deleteLink']";

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void clickAddMilestoneButton() {
        log.info("Click 'Add milestone' button");
        waitForPendoImage();
        new Button(driver, ADD_MILESTONE_BUTTON).click();
    }

    @Step("Creating milestone with title '{milestoneName}'")
    public void createMilestone(Milestone milestone) {
        log.info("Creating milestone with title '{}'", milestone.getName());
        waitForPendoImage();
        waitForPendoBubbleImage();
        new Input(driver, MILESTONE_NAME).setValue(milestone.getName());
        new Input(driver, MILESTONE_DESCRIPTION).setValue(milestone.getDescription());
        new Button(driver, SUBMIT_MILESTONE_BUTTON).click();
    }

    public void openMilestoneTab() {
        log.info("Opening page containing Milestones");
        waitForPendoImage();
        waitForPendoBubbleImage();
        new Button(driver, MILESTONE_TAB).click();
    }

    @Step("Checking the existence of the milestone with title '{milestoneName}'")
    public boolean isMilestoneExist(String milestoneName) {
        waitForPendoImage();
        waitForPendoBubbleImage();
        List<WebElement> milestonesList = driver.findElements(ALL_MILESTONES);
        for (WebElement milestone : milestonesList) {
            if (milestone.getText().equals(milestoneName)) {
                return true;
            }
        }
        return false;
    }

    @Step("Creating new milestone with title '{newMilestoneName}'")
    public void updateMilestone(Milestone milestone) {
        log.info("Updating primary milestone to milestone with title '{}'", milestone.getName());
        waitForPendoImage();
        waitForPendoBubbleImage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_MILESTONE_BUTTON));
        new Input(driver, MILESTONE_NAME).clearValue();
        new Input(driver, MILESTONE_NAME).setValue(milestone.getName());
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_MILESTONE_BUTTON));
        new Button(driver, SUBMIT_MILESTONE_BUTTON).click();
    }

    @Step("Editing milestone with title '{milestoneName}'")
    public void clickEditMilestone(String milestoneName) {
        log.info("Clicking edit Milestone");
        waitForPendoBubbleImage();
        String milestoneLocator = String.format(MILESTONE_LOCATOR, milestoneName);
        String editMilestoneLocator = String.format(EDIT_MILESTONE_LOCATOR, milestoneName);
        waitForElementVisibility(milestoneLocator);
        WebElement milestoneElement = driver.findElement(By.xpath(milestoneLocator));
        Button milestoneButton = new Button(driver, milestoneElement);
        milestoneButton.scroll();
        WebElement editIcon = driver.findElement(By.xpath(editMilestoneLocator));
        waitForElementVisibility(editMilestoneLocator);
        editIcon.click();
    }


    @Step("Deleting milestone with title '{milestoneName}'")
    public void clickDeleteMilestone(String milestoneName) {
        log.info("Clicking delete Milestone");
        waitForPendoImage();
        waitForPendoBubbleImage();
        String milestoneLocator = String.format(MILESTONE_LOCATOR, milestoneName);
        String deleteMilestoneLocator = String.format(DELETE_MILESTONE_LOCATOR, milestoneName);
        waitForElementVisibility(milestoneLocator);
        WebElement milestoneElement = driver.findElement(By.xpath(milestoneLocator));
        Button milestoneButton = new Button(driver, milestoneElement);
        milestoneButton.scroll();
        WebElement deleteMilestoneIcon = driver.findElement(By.xpath(deleteMilestoneLocator));
        waitForElementVisibility(deleteMilestoneLocator);
        deleteMilestoneIcon.click();
    }

    @Step("Confirmation delete milestone")
    public void confirmDeleteMilestone() {
        log.info("Confirmation delete milestone");
        DeleteConfirmationModal deleteConfirmationModal = new DeleteConfirmationModal(driver);
        deleteConfirmationModal.confirmDelete();
    }


}




