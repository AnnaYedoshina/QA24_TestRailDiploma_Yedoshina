package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Button {
    private final MyElement element;

    public Button(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Button(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
    }

    public Button(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }
    public void click() {
        element.click();
    }


}
