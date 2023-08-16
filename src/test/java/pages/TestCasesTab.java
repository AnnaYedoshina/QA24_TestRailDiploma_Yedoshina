package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Section;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

@Log4j2
public class TestCasesTab extends BasePage {
    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Test Cases')]");
    private static final By ADD_SECTION_BUTTON = By.id("addSection");
    private static final By ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS = By.id("addSectionInline");
    private static final By SECTION_NAME = By.name("editSectionName");
    private static final By SECTION_DESCRIPTION = By.id("editSectionDescription_display");
    private static final By SUBMIT_SECTION_BUTTON = By.id("editSectionSubmit");
    private static final By ALL_SECTIONS = By.xpath("//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName')]");
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By BLOCK_WINDOW = By.cssSelector("[class='blockUI blockOverlay']");
    private static final String DELETE_SECTION_ICON_LOCATOR = "//span[contains(@id,'sectionName') and text()='%s']/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    private static final String EDIT_SECTION_ICON_LOCATOR = "//span[contains(@id,'sectionName') and text()='%s']/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    private static final String CASE_LOCATOR = "//div[contains(@class,'grid-container')]/descendant::span[text()='%s']";
    private static final String SECTION_LOCATOR = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and text()='%s']";

    public TestCasesTab(WebDriver driver) {
        super(driver);
    }

    private void waitDisappearBlockingWindow() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(BLOCK_WINDOW));
    }

    @Step("Checking the existence of the section with title '{sectionName}'")
    public boolean isSectionExist(String sectionName) {
        waitForPendoImage();
        waitForPendoBubbleImage();
        List<WebElement> sectionsList = driver.findElements(ALL_SECTIONS);
        for (WebElement section : sectionsList) {
            if (section.getText().equals(sectionName)) {
                return true;
            }
        }
        return false;
    }


    @Step("Editing section with title '{sectionName}'")
    public void clickEditSection(String sectionName) {
        log.info("Clicking edit Section");
        waitForPendoBubbleImage();
        String sectionLocator = String.format(CASE_LOCATOR, sectionName);
        String editSectionLocator = String.format(EDIT_SECTION_ICON_LOCATOR, sectionName);
        waitForElementVisibility(sectionLocator);
        WebElement sectionElement = driver.findElement(By.xpath(sectionLocator));
        Button sectionButton = new Button(driver, sectionElement);
        sectionButton.scroll();
        WebElement editSectionIcon = driver.findElement(By.xpath(editSectionLocator));
        editSectionIcon.click();
    }

    @Step("Deleting section with title '{sectionName}'")
    public void clickDeleteSection(String sectionName) {
        log.info("Clicking delete Section");
        waitForPendoImage();
        waitForPendoBubbleImage();
        String sectionLocator = String.format(SECTION_LOCATOR, sectionName);
        String deleteSectionLocator = String.format(DELETE_SECTION_ICON_LOCATOR, sectionName);
        waitForElementVisibility(sectionLocator);
        WebElement sectionElement = driver.findElement(By.xpath(sectionLocator));
        Button sectionButton = new Button(driver, sectionElement);
        sectionButton.hover();
        waitForElementVisibility(deleteSectionLocator);
        WebElement deleteSectionElement = driver.findElement(By.xpath(deleteSectionLocator));
        Button deleteSectionButton = new Button(driver, deleteSectionElement);
        deleteSectionButton.click();
        waitForPendoBubbleImage();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    @Step("Cliking add section button")
    public void clickCreateSectionButton() {
        log.info("Click 'Add section' button");
        waitDisappearBlockingWindow();
        List<WebElement> testSectionsList = driver.findElements(ALL_SECTIONS);
        if (testSectionsList.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS));
            new Button(driver, ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON));
            new Button(driver, ADD_SECTION_BUTTON).click();
        }
    }

    @Step("Creating section with title '{sectionName}'")
    public void createSection(Section section) {
        log.info("Creating section with title '{}'", section.getName());
        waitDisappearBlockingWindow();
        waitForPendoImage();
        new Input(driver, SECTION_NAME).setValue(section.getName());
        new Input(driver, SECTION_DESCRIPTION).setValue(section.getDescription());
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_SECTION_BUTTON));
        new Button(driver, SUBMIT_SECTION_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(SECTION_LOCATOR, section.getName()))));
    }

    @Step("Opening page containing Sections and Cases")
    public void openCaseTab() {
        log.info("Opening page containing Sections and Cases");
        waitForPendoImage();
        wait.until(ExpectedConditions.elementToBeClickable(CASE_TAB));
        new Button(driver, CASE_TAB).click();
        acceptAlertIfPresent();
    }

    @Step("Creating new section with title '{newSectionName}'")
    public void updateSection(Section section) {
        log.info("Updating primary section to section with title '{}'", section.getName());
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SECTION_BUTTON));
        new Input(driver, SECTION_NAME).clearValue();
        new Input(driver, SECTION_NAME).setValue(section.getName());
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_SECTION_BUTTON));
        new Input(driver, SECTION_DESCRIPTION).clearValue();
        new Input(driver, SECTION_DESCRIPTION).setValue(section.getDescription());
        new Button(driver, SUBMIT_SECTION_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(SECTION_LOCATOR, section.getName()))));
    }

    @Step
    public boolean isAddSectionButtonDisplayed() {
        WebElement addSectionButton = driver.findElement(ADD_SECTION_BUTTON);
        return addSectionButton.isDisplayed();

    }
}
