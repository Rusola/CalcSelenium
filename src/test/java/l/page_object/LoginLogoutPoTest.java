package l.page_object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginLogoutPoTest {
    private WebDriver driver;

    @BeforeAll
    static void setupBeforeAll() throws Exception{
        WebDriverManager.chromedriver().setup();
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
    void validLoginLogout() {
        HomePage home_page = LoginPage.open(driver) // 06/24 можно и через создание объекта
                .validLogin("tomsmith", "SuperSecretPassword!");
        String found_mwg = home_page.getLoginConfirmation(); // теперь надо проверить правда ли мы на правтльной странице теперь
        assertTrue(found_mwg.contains("You logged into"));

        LoginPage login_page = home_page.logout();
        String found_msg_2 = login_page.getLogoutConfirmation();
        assertTrue(found_msg_2.contains("You logged out"));
    }

    @Test
    void invalidLogin(){

    }
}
