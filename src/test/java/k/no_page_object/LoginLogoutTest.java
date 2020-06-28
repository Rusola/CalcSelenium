package k.no_page_object;

import comon.Base;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class LoginLogoutTest extends Base {
//    private WebDriver driver; т к в родителе

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void loginLogout() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        WebElement buttonLogin =  driver.findElement(By.cssSelector("[type=\"submit\"]"));
        buttonLogin.click();

        WebElement confirmLogin = driver.findElement(By.id("flash"));
        assertTrue(confirmLogin.getText().contains("You logged into a secure area!"));

        WebElement buttonLogout = driver.findElement(By.cssSelector(".secondary.radius"));
        buttonLogout.click();

        WebElement confirmLogout = driver.findElement(By.id("flash"));
        assertTrue(confirmLogout.getText().contains("You logged out of the secure area!"));
    }
}
