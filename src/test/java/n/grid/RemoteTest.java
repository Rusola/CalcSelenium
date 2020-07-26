package n.grid;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class RemoteTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities(); // мне нужно передать стенделону инф-цию на каком браузере я хочу запускать. В версии grid4 это будут options
        capabilities.setBrowserName("chrome"); // указать желаемые возможности-что я хочу от этого remote server(стенделона)ю Я хочу браузер.
        /* раз больше ничего мне от него не надо, теперь нужно передать эти capabilities -> remote driver (стенделону),
         чтобы он передал это -> chromedriver и тот запустил браузер chrome */

        //driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"), capabilities); // class RemoteWebDriver also implements WebDriver interface. Где находится Remote Webdriver
        driver = new RemoteWebDriver(new URL("http://192.168.1.7:4445/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    void remote_test(){
        driver.get("https://www.selenium.dev/selenium/docs/api/java/index.html");
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().contains("Overview"));
    }
}
