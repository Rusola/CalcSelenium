package f.tabs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/** Tab or Window, no matter. Window Handle is just a String */

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SwitchingTabs {
    private WebDriver driver;
    private String firstTabTitle = "The Internet";
    private String secondTabTitle = "New Window";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void test(){
        String firstTabHandle = driver.getWindowHandle();
        String secondTabHandle = ""; // we do not know yet

        WebElement link = driver.findElement(By.linkText("Click Here"));
        link.click(); // now we have 2 windows opened

        Set<String> allWindows = driver.getWindowHandles();

        // 1. If >= 2 windows:Search by page Titles, no window handles known:
        for(String myWindow: allWindows){ // двигаемся по окнам и когда совпадет title -> значит на него можно переключаться
            // здесь мы переключились куда-то
            driver.switchTo().window(myWindow); // Returns a TargetLocator interface which can be used to select a frame or window

            // проверим где мы
            if(secondTabHandle.equals(driver.getTitle())){
                break;
            }
        }

        // 2. If = 2 windows only(or 1 handle unknown): can Search by window handle
        for(String myWindow: allWindows){
            if(!firstTabTitle.equals(myWindow)){
                secondTabHandle = myWindow;
                driver.switchTo().window(secondTabHandle);
            }
        }

        // Go back to the 1st window
        driver.switchTo().window(firstTabHandle);
        driver.close(); // закрывает таб, но не завершает браузер, не уничтожает driver object
        driver.switchTo().window(secondTabHandle); // хоть и закрыли первое окно, надо еще и перевести driver чтобы работать с оставшимся вторым
        assertEquals(secondTabTitle, driver.getTitle());

    }
}
