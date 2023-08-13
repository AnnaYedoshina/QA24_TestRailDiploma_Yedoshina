package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Button {
    private final MyElement element;
    private final Actions actions;

    public Button(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
        this.actions = new Actions(driver);
    }

    public Button(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
        this.actions = new Actions(driver);
    }

    public Button(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
        this.actions = new Actions(driver);
    }

    public void click() {
        element.click();
    }

    public void scroll() {
        this.element.scrollIntoView();
    }


}
