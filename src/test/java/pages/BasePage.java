package pages;

import elements.Checkbox;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = PropertyReader.getProperty("base_url");
    private static final By PENDO_IMAGE = By.xpath("//img[contains(@id,'pendo-image-badge')]");
    private static final By PENDO_IMAGE_BUBBLE = By.xpath("//style[@id = 'pendo-resource-center-bubble-animation']/following-sibling::div");
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void scroll(String targetLocator, String targetName) {
        log.info("Scrolling page to case or section with title '{}'", targetName);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", targetTitle);
        ((JavascriptExecutor) driver).executeScript("scrollBy(0, -200)");
    }

    public void hover(String targetLocator, String targetName) {
        log.info("Hovering over the case or section with title '{}'", targetName);
        Actions actions = new Actions(driver);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        actions.moveToElement(targetTitle).build().perform();
    }

    public void waitForPendoImage() {
        wait.until(ExpectedConditions.elementToBeClickable(PENDO_IMAGE));

    }
    public void waitForPendoBubbleImage() {
        wait.until(ExpectedConditions.elementToBeClickable(PENDO_IMAGE_BUBBLE));

    }

    public void acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }
    public void scrollIntoView(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hover(String locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(locator));
        actions.moveToElement(element).build().perform();
    }
    public void confirmDelete() {
        new Checkbox(driver, CONFIRM_DELETE_BUTTON).check();
    }
    public void waitForElementVisibility(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }


}
