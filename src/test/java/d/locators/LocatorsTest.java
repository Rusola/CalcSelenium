package d.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LocatorsTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpBeforeAll() throws Exception{
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() throws  Exception{
        driver = new ChromeDriver();
        driver.get("https://www.kmart.com/"); // the exact same driver.navigate().to("https://www.kmart.com/")
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach comment it in order to see the page
    void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    void test(){
        WebElement logo = driver.findElement(By.className("ribbon-kmart-logo"));
        logo.click();
        WebElement myAccountBtn = driver.findElement(By.cssSelector("#yourAccount"));
        myAccountBtn.click();

        driver.navigate().refresh(); // Interface WebDriver.Navigation

        WebElement searchInput = driver.findElement(By.id("keyword"));
        searchInput.sendKeys("milk");

        driver.findElement(By.linkText("Kmart home")).click();
        driver.findElement(By.partialLinkText("home")).click();

        driver.findElement(By.name("keyword")).sendKeys("Water");

        WebElement body = driver.findElement(By.tagName("body"));
        System.out.println(body.getText()); // только все тексты

        #gnf_dept_tree_item_2 > span > a

    }
}
