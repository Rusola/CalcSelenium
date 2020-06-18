package g.elements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/** Tab or Window, no matter. Window Handle is just a String */

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElementExistAndScrolling {
    private WebDriver driver;
    private String titleToSwitch = "Kmart Help";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.kmart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach
    void tearDown() throws Exception{
        driver.quit();
    }
/** здест прыгающий title второго окна поэтому лучше ориентироваться на windowHandle */
    @Test
    void verifyElementExist() throws InterruptedException {
        driver.findElement((By.linkText("Help"))).click();

        WebElement img = driver.findElement(By.cssSelector(".bottom-half-margin img"));
        System.out.printf("Before click on img the title was '%s'\n", driver.getTitle());
        img.click();

        Set<String> allWindows = driver.getWindowHandles();
        System.out.println(allWindows.size());
        for(String myWindow :  allWindows){
            driver.switchTo().window(myWindow);
            System.out.printf("After switchTo the title is '%s'\n", driver.getTitle());
            if(driver.getTitle().equals(titleToSwitch)){
                System.out.println("I AM IN");
                break;
            }
        }

        System.out.printf("At the end the title iS '%s'\n", driver.getTitle());
        Thread.sleep(2000);
        System.out.printf("At the end the title iS '%s'\n", driver.getTitle());

        // Verify that ELEMENT EXISTS
        assertEquals(1, driver.findElements(By.tagName("input")).size());

        // Verify that ELEMENT DOES NOT EXIST
        List <WebElement> elems = driver.findElements(By.id("fakeId")); // no exception will be thrown vs findElement
        assertTrue(elems.size()==0);
    }

    @Test
    void scrolling(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 5000)");
    }
}

