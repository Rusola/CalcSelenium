package c.screen_shot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenShotsTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpBeforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("http://google.com/");
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void test() throws IOException {
        var my_screenShotTaker = (TakesScreenshot) driver; // переориентируем на TakesScreenshot интерфейс

        File file_in_main_memory = my_screenShotTaker.getScreenshotAs(OutputType.FILE); // Сделать скриншот (файл в памяти)
        FileHandler.copy(file_in_main_memory, new File("visibleArea.png")); // скопировать содержимое из файла памяти в файл на нашем диске
    }
}
