package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class TestCaseInfoPage extends BasePage {
    public TestCaseInfoPage(WebDriver driver) {
        super(driver);
    }

    private static final By TITLE_LOCATOR = By.cssSelector(".content-header-title.page_title");
    private static final By SECTION_LOCATOR = By.xpath("//div[@class = 'content-breadcrumb']");
    private static final By TYPE_LOCATOR = By.id("cell_type_id");
    private static final By PRIORITY_LOCATOR = By.id("cell_priority_id");
    private static final By ESTIMATE_LOCATOR = By.id("cell_estimate");
    private static final By REFERENCES_LOCATOR = By.id("cell_refs");
    private static final By AUTOMATION_TYPE_LOCATOR = By.id("cell_custom_automation_type");
    private static final By PRECONDITIONS_LOCATOR = By.xpath("//span[@class =  'field-title-inner' and text() = 'Preconditions']/parent::div/following-sibling::div[@class='field-content'][1]//p");
    private static final By STEPS_LOCATOR = By.xpath("//span[@class='field-title-inner' and text() = 'Steps']/parent::div/following-sibling::div[@class='field-content'][1]//p");
    private static final By EXPECTED_RESULT_LOCATOR = By.xpath("//span[@class='field-title-inner' and text() = 'Expected Result']/parent::div/following-sibling::div[@class='field-content'][1]//p");
    private static final By UPLOADED_FILE = By.xpath("//div[contains(@title, '%S')]");

    @Step("Getting test case info")
    public TestCase getTestCaseInfo() {
        waitForPendoImage();
        TestCase testCase = TestCase.builder()
                .setTitle(driver.findElement(TITLE_LOCATOR).getText())
                .setSection(driver.findElement(SECTION_LOCATOR).getText())
                .setType(driver.findElement(TYPE_LOCATOR).getText().substring(5))
                .setPriority(driver.findElement(PRIORITY_LOCATOR).getText().substring(9))
                .setEstimate(driver.findElement(ESTIMATE_LOCATOR).getText().substring(9))
                .setReferences(driver.findElement(REFERENCES_LOCATOR).getText().substring(11))
                .setAutomationType(driver.findElement(AUTOMATION_TYPE_LOCATOR).getText().substring(16))
                .setPreconditions(driver.findElement(PRECONDITIONS_LOCATOR).getText())
                .setSteps(driver.findElement(STEPS_LOCATOR).getText())
                .setExpectedResult(driver.findElement(EXPECTED_RESULT_LOCATOR).getText()).build();
        return testCase;

    }

    @Step("Checking is file uploaded")
    public boolean isFileUploaded(String fileName) {
        waitForPendoImage();
        WebElement uploadedFile = driver.findElement(By.xpath(String.format("//div[contains(@title, '%s')]", fileName)));
        return uploadedFile.isDisplayed();
    }
}
