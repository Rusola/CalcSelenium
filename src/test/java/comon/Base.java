package comon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    protected WebDriver driver = null;
    protected void clickElement(WebElement elem){
        WebDriverWait my_wait = new WebDriverWait(driver, 10);
        my_wait.until(ExpectedConditions.elementToBeClickable(elem));
    }
}
