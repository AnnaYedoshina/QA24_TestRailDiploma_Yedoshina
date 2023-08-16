package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Button {
    private final MyElement element;
    private final WebDriver driver;

    public Button(WebDriver driver, By by) {
        this.driver = driver;
        this.element = new MyElement(driver, by);
    }

    public Button(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = new MyElement(driver, element);
    }

    public Button(WebDriver driver, String id) {
        this.driver = driver;
        this.element = new MyElement(driver, By.id(id));
    }

    public void click() {
        element.click();
    }

    public void scroll() {
        this.element.scrollIntoView();
    }

    public void hover() {
        this.element.hover();
    }
}



