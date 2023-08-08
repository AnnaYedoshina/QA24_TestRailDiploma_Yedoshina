package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;

import java.util.List;

@Log4j2
public class DashboardPage extends BasePage {
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By ALL_PROJECTS = By.cssSelector(".summary-title");
    private static final String PROJECT_LOCATOR = "//div[@id='content_container']/descendant::a[text()='%s']";
    private static final String PROJECT_LINK_LOCATOR = "//a[text() = '%s']";

    private static final String ADD_PROJECT_BUTTON_ID = "sidebar-projects-add";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Opening Dashboard page")
    public void open() {
        log.info("Opening Dashboard page");
        driver.get(BASE_URL + "index.php?/dashboard");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Step("Clicking addProject button")
    public void clickCreateProjectButton() {
        log.info("Click 'Add project' button");
        new Button(driver, ADD_PROJECT_BUTTON).click();
    }

    @Step("Checking the existence of the project '{projectName}'")
    public boolean isProjectExist(String projectName) {
        waitForPendoImage();
        log.info("Checking the existence of the project '{}'", projectName);
        List<WebElement> projectsList = driver.findElements(ALL_PROJECTS);
        for (WebElement project : projectsList) {
            if (project.getText().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

    @Step("Opening project")
    public void openProject(String projectName) {
        log.info("Opening project '{}'", projectName);
        new Button(driver, By.xpath(String.format(PROJECT_LOCATOR, projectName))).click();
    }

    @Step("Searching for project button to be displayed")
    public boolean isAddProjectButtonDisplayed() {
        return driver.findElement(By.id(ADD_PROJECT_BUTTON_ID)).isDisplayed();
    }

    @Step("Opening project by name")
    public void openProjectByName(String projectName) {
        log.info(String.format("Opening project by name = %s", projectName));
        new Button(driver, By.xpath(String.format(PROJECT_LINK_LOCATOR, projectName))).click();
    }

}

