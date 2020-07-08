package m.log_for_cachelokup;

import io.github.bonigarcia.wdm.WebDriverManager;
import l.page_object.HomePage;
import l.page_object.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassWithLog {

    private WebDriver driver;

    @BeforeAll
    static void setupBeforeAll() throws Exception{
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.logfile", "my_log.txt");
    }

    @BeforeEach
    void setUp() throws  Exception{
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    void testWithoutCacheLookup() {

        // засечь врем до
        System.out.println(Math.random());
     
        HomePage home_page = LoginPage.open(driver)
                .validLogin("tomsmith", "SuperSecretPassword!");; // зделаем любые действия где есть findElement под копотом

        // засечь врем после
    }

    @Test
    void testWithCacheLookup() {
        HomePage home_page = LoginPage.open(driver)
                .validLogin("tomsmith", "SuperSecretPassword!");; // зделаем любые действия где есть findElement под копотом
    }


}

