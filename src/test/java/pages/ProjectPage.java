package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectPage extends BasePage {
    private static final String addTestCaseButtonId = "sidebar-cases-add";
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By MILESTONE_TAB = By.id("navigation-milestones");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Clicking addTestCaseLink")
    public void clickAddTestCasesLink() {
        log.info("Clicking addTestCaseLink");
        new Button(driver, addTestCaseButtonId).click();

    }

    @Step("Opening page containing Sections and Cases")
    public void openCaseTab() {
        log.info("Opening page containing Sections and Cases");
        new Button(driver, CASE_TAB).click();
        acceptAlertIfPresent();
    }

    @Step("Opening page containing Milestones")
    public void openMilestoneTab() {
        log.info("Opening page containing Milestones");
        new Button(driver, MILESTONE_TAB).click();
        acceptAlertIfPresent();
    }


}
