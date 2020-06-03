package b.cross_browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsingWDManager1Test {

    private WebDriver driver;

    @BeforeAll
    static void setUpBeforeAll() {
        WebDriverManager.chromedriver().setup(); // 3d party library that will check, download & setup the newest binary drivers
    }

    @BeforeEach
    void setUp () throws Exception {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() throws Exception {
        driver.quit();
    }

    @Test
    void test(){
        driver.get("http://google.com/");
    }
}
