package l.page_object;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.LinkOption;

import static org.junit.Assert.assertEquals;
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

    // data_driven
    @ParameterizedTest
    @CsvFileSource(resources = "/data_for_invalid_login_po.scv", numLinesToSkip = 1)
    void invalidLoginTest(String username_from_file, String password_from_file, String expected){
        LoginPage login_page = LoginPage.open(driver)
                .invalidLogin(username_from_file, password_from_file);
        String found_error = login_page.getErrorMessage().split("\n")[0];
        System.out.printf("USERNAME:%s, PASSWORD:%s", username_from_file, password_from_file);
        assertEquals(expected, found_error);
    }
}
