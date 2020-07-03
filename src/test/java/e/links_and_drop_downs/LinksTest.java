package e.links_and_drop_downs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinksTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://book.theautomatedtester.co.uk//"); // the exact same driver.navigate().to("...")
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterEach // comment it in order to see the page
    void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    void testingLinks() {
        List<WebElement> list = driver.findElements(By.tagName("a"));
        System.out.println("Number of links: " + list.size());

        for (WebElement link : list) {
            System.out.println(link.getText() + " = " + link.getAttribute("href"));
        }
    }

    @Test
    void testingHtml() {
        String body = driver.getPageSource();
        System.out.println(body);
        assertTrue(body.contains("Selenium"));
    }

    @Test
    void gettingStates() {
        driver.findElement(By.linkText("Chapter1")).click();

        // Verify that checkbox is displayed
        WebElement checkbox = driver.findElement((By.name("selected(1234)")));
        assertTrue(checkbox.isDisplayed());

        checkbox.click();

        // Verify that checkbox is selected
        assertTrue(checkbox.isSelected());
    }

    @Test
    void readInoutField() {
        WebElement textbox = driver.findElement((By.id("q")));
        textbox.sendKeys("students");

        // Find text if that is a value
        String found_text = textbox.getAttribute("value");
        assertEquals("students", found_text);
    }

    @Test
    void testingDropDowns() {
        driver.get("https://compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement dropDown = driver.findElement(By.name("dropdown"));
        Select s = new Select(dropDown); // в констрактор передать какой из элементов рассматриавть как селект
        s.selectByVisibleText("Drop Down Item 5");
        s.selectByIndex(1); // 2nd option

        // Get all options as WebElements -> extract texts
        List<WebElement> options = s.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }

    @Test
    void testingMultiSelect() {
        driver.get("https://compendiumdev.co.uk/selenium/basic_html_form.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement multiSelect = driver.findElement(By.xpath("//select[starts-with(@name, 'multipleselect')]"));
        //WebElement multiSelect = driver.findElement(By.xpath("//*[@id=\"HTMLFormElements\"]/table/tbody/tr[7]/td/select"));
        Select s = new Select(multiSelect);
        s.deselectAll();
        s.selectByIndex(0);
        s.selectByIndex(1);
    }

    @Test
    void homeWork2() throws IOException {
        driver.get("https://www.trademe.co.nz");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement searchInput = driver.findElement(By.id("searchString"));
        searchInput.sendKeys("milk");
        WebElement searchBtn = driver.findElement(By.cssSelector("button[value=\"Search\"]"));

        searchBtn.click();

        WebElement result = driver.findElement(By.className("search-results-text"));
        String found_text = result.getText();

        assertEquals("Search results for 'milk'", found_text);

        // сделать снимок страницы
        var my_screenShotTaker = (TakesScreenshot) driver; // создадим переориентируем на TakesScreenshot интерфейс

        File file_in_main_memory = my_screenShotTaker.getScreenshotAs(OutputType.FILE); // Сделать скриншот (файл в памяти)
        FileHandler.copy(file_in_main_memory, new File("whereTo.png")); // скопировать содержимое из файла памяти в файл на нашем диске

        // Нажать browser Back
        driver.navigate().back(); // Interface WebDriver.Navigation
    }

    @Test
    void homeWork3() {
        driver.get("https://duckduckgo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement search = driver.findElement(By.id("search_form_input_homepage"));
        search.sendKeys("maven");
        driver.findElement(By.id("search_button_homepage")).click();

        WebElement searchOnTop = driver.findElement(By.id("search_form_input"));
        String found_text = searchOnTop.getAttribute("value");
        assertEquals("maven", found_text);

        // Verify that link exists
        List <WebElement> links = driver.findElements(By.linkText("Apache Maven"));
        assertTrue(links.size() != 0);
    }
}
