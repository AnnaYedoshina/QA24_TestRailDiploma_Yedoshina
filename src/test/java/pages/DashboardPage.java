package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class DashboardPage extends BasePage {
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By ALL_PROJECTS = By.cssSelector(".summary-title");
    private static final By PENDO_IMAGE = By.xpath("//img[contains(@id,'pendo-image-badge')]");
    private String projectLocator = "//div[@id='content_container']/descendant::a[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        log.info("Opening Dashboard page");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
            driver.get(BASE_URL + "index.php?/dashboard");
        }
    }

    public void clickCreateProjectButton() {
        log.info("Click 'Add project' button");
        new Button(driver, ADD_PROJECT_BUTTON).click();
    }

    @Step("Checking the existence of the project '{projectName}'")
    public boolean isProjectExist(String projectName) {
        wait.until(ExpectedConditions.elementToBeClickable(PENDO_IMAGE));
        log.info("Checking the existence of the project '{}'", projectName);
        List<WebElement> projectsList = driver.findElements(ALL_PROJECTS);
        for (WebElement project : projectsList) {
            if (project.getText().equals(projectName)) {
                return true;

            }
        }
        return false;
    }


    public void openProject(String projectName) {
        log.info("Opening project '{}'", projectName);
        new Button(driver, By.xpath(String.format(projectLocator, projectName))).click();
    }

}

