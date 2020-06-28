package j.actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkingWithActions {
    private WebDriver driver;

    /* Google for Advanced User Interactions API */

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://book.theautomatedtester.co.uk/chapter4");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void moveMouseToAlert() throws IOException {

        Actions builder = new Actions(driver);
        WebElement button = driver.findElement(By.id("hoverOver"));
        builder.moveToElement(button).perform(); // не забыть в конце цепочки действий сделать perform

        Alert myAlert = driver.switchTo().alert();

        // скриншот убивает popup


        var my_screenShotTaker = (TakesScreenshot) driver; // переориентируем на TakesScreenshot интерфейс

        File file_in_main_memory = my_screenShotTaker.getScreenshotAs(OutputType.FILE); // Сделать скриншот (файл в памяти)
        FileHandler.copy(file_in_main_memory, new File("visibleAlert.png")); // скопировать содержимое из файла памяти в файл на нашем диске
    }

    @Test
    void hw_drag_drop_67() {
        driver.get("http://www.demoqa.com/dragabble");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement draggable = driver.findElement(By.id("dragBox"));
        int current_x = draggable.getLocation().getX();
        int current_y = draggable.getLocation().getY();

        Actions builder = new Actions(driver);
        builder.dragAndDropBy(draggable,  67, 67).perform();
//        builder.clickAndHold(draggable).moveByOffset(current_x + 67,current_y + 67).click().perform();
//        WebElement draggable_after = driver.findElement(By.id("dragBox"));
        int x_after_dragging = draggable.getLocation().getX();
        int y_after_dragging = draggable.getLocation().getY();
        assertEquals(current_x + 67, x_after_dragging);
        assertEquals(current_y + 67, y_after_dragging);
    }

    @Test
    void wh2_drag_and_drop() throws IOException {

        driver.get("https://www.testandquiz.com/selenium/testing.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scroll(0, 5000)");

        WebElement draggable = driver.findElement(By.id("sourceImage"));
        WebElement target_square = driver.findElement(By.id("targetDiv"));
        int height = target_square.getRect().height;
        int y_square = target_square.getRect().getY();
        int x_square = target_square.getRect().getX();

        System.out.println(height);

        int draggable_x = draggable.getLocation().getX();
        int draggable_y = draggable.getLocation().getY();

        int target_x = target_square.getLocation().getX();
        int target_y = target_square.getLocation().getY();

        Actions builder = new Actions(driver);
//        builder.dragAndDrop(draggable, target_square).perform();
        builder.dragAndDropBy(draggable, draggable_x + 300, draggable_y - 300).perform();
//
//        builder.clickAndHold(draggable).moveToElement(target_square).click().perform();
//        builder.moveToElement(draggable, target_square).perform();

    }
}
