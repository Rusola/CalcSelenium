package b.cross_browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UsingWDManager2 {

    private WebDriver driver;

    @Test
    void testChrome1(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://google.com/");
    }

    @Test
    void testChrome2(){
        WebDriverManager.chromedriver().browserVersion("73");
        driver = new ChromeDriver();
        driver.get("http://google.com/");
    }

    @Test
    void testFirefox(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://google.com/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
