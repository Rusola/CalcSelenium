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
}
