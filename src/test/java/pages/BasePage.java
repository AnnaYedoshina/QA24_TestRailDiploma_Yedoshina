package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
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


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

    public void waitForElementVisibility(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }


}
