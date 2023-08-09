package pages;

import elements.Checkbox;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
@Log4j2
public class BaseTabPage extends BasePage {
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final String ENTITY_LOCATOR = "//div[contains(@class,'grid-container')]/descendant::span[text()='%s']";

    public BaseTabPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEntityExist(String entityName, By entityLocator) {
        List<WebElement> entitiesList = driver.findElements(entityLocator);
        boolean isEntityExist = false;
        for (WebElement entity : entitiesList) {
            if (entity.getText().equals(entityName)) {
                isEntityExist = true;
            }
        }
        return isEntityExist;
    }

    public void confirmDelete() {
        new Checkbox(driver, CONFIRM_DELETE_BUTTON).check();
    }
    protected void clickIcon(String entityName, String entityTitleLocator, String iconActionLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ENTITY_LOCATOR, entityName))));
        scroll(entityTitleLocator, entityName);
        hover(entityTitleLocator, entityName);
        WebElement icon = driver.findElement(By.xpath(String.format(iconActionLocator, entityName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(iconActionLocator, entityName))));
        log.info("Click edit/delete icon");
        icon.click();
    }
}

