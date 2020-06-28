package comon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Здесь записываются методы используемые для синхронизации*/

public class Base {
    protected WebDriver driver = null; // нужно прописывать его команды, но пока заглушка, тк нам надо чтобы тест мог сам передавть какой именно driver:тест передаст в конструктор LoginPage а онструктор LoginPage передаст в this.driver = сюда

    protected void clickElement(WebElement elem){
        WebDriverWait my_wait = new WebDriverWait(driver, 10);
        WebElement element = my_wait.until(ExpectedConditions.elementToBeClickable(elem));
        element.click();
    }
/* Method overloading */
    protected void clickElement(By locator){
        WebDriverWait my_wait = new WebDriverWait(driver, 10);
        WebElement element = my_wait.until((ExpectedConditions.elementToBeClickable(locator)));
        element.click();
    }
}
