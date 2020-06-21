package h.tables;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tables {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void createFile() throws FileNotFoundException {

        var writer_to_expected = new PrintWriter(new File("expected.txt"));

        List<WebElement> rows = driver.findElements(By.cssSelector("#table2 tbody tr"));
        for(WebElement row : rows){
            WebElement column = row.findElement(By.cssSelector(".dues"));
            writer_to_expected.println(column.getText()); // запишем каждое значение
//            System.out.println(column.getText());
        }
        writer_to_expected.close();
    }

    @Test
    void getFromFile() throws FileNotFoundException {

        var scanner_from_expected = new Scanner(new File("expected.txt"));

        List<String> expected = new ArrayList<String>();

        while (scanner_from_expected.hasNextLine()){
            expected.add(scanner_from_expected.nextLine());
        }
        System.out.println(expected);

        List<WebElement> rows = driver.findElements(By.cssSelector("#table2 tbody tr"));
        for(int i = 0; i < expected.size(); i ++){
            WebElement column = rows.get(i).findElement(By.cssSelector(".dues"));
            assertEquals(expected.get(i), column.getText());
        }
    }

    @Test
    void testWithArray(){
        //List<String> expected = new ArrayList<String>(); // пустой объект интерфейса List
        String[] data = {"$50.00", "$51.00", "$100.00", "$50.00"}; // фиксированный массиив
        //expected = Arrays.asList(data); // wrap an existing array just to have access to List methods(ex:get()), except those that would change the size

        List<WebElement> columns = driver.findElements(By.cssSelector("#table2 tr>td:nth-child(4)"));
        for(int i = 0; i < data.length; i ++){
            assertEquals(data[i], columns.get(i).getText());
        }
    }
}

