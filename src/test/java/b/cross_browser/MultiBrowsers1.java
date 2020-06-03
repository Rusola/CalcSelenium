package b.cross_browser;

import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;

public class MultiBrowsers1 {

    private WebDriver driver;// Always MUST HAVE. WebDriver is an Interface that represents AN idealised web browser.
    static private String myBrowser = "chrome";

    @BeforeEach
    void setUp(){
        switch (myBrowser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
                driver = new ChromeDriver();/* будем работать через переменную интерфейса
                 т.е. огоаничиваем методы класса ChromeDriver,
                 возьмем только те что перечислена в интерфейсе WebDriver.
                 Зато сможем использовать эту же переменную для работы с другими классами = Polimorphism */
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "/Users/Olga/projects/drivers/geckodriver");
                driver = new FirefoxDriver();
            }
        }
    }

    @Test
    void test1() {
        driver.get("https://www.selenium.dev/");
        System.out.println("BEEN HERE");
        String found_title = driver.getTitle();
        System.out.println(found_title);
    }

    @AfterEach
    void tearDown(){
        driver.quit(); // 1) quit executable chromedriver 2)quit chrome window 3) kill the object 'driver'
    }
}
