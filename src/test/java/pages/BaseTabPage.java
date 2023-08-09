package pages;

import elements.Checkbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BaseTabPage extends BasePage{
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");

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
}
