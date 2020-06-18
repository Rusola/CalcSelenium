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

public class HomeWork {
    private WebDriver driver;
    private String givenHeader = "Last Name";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Olga/projects/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://demo.automationtesting.in/WebTable.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void printColumnsNames(){
        List<WebElement> headers = driver.findElements(By.cssSelector(".ui-grid-header-cell-wrapper .ui-grid-header-cell"));
        int columnNumber = 0;
        for(int i = 0; i<headers.size(); i ++){
            String currentName = headers.get(i).getText().trim();
//            System.out.println("*" + headers.get(i).getText() + "*");
//            System.out.println("givenHeader:" + givenHeader);
            if(currentName.equals(givenHeader)){
                System.out.println("I am IN");
                columnNumber = i;
            }
        }
        System.out.println(columnNumber);
        String selector = ".ui-grid-row [role=\"gridcell\"]:nth-child(" + (columnNumber + 1) +")";
        System.out.println(selector);

        List<WebElement> cells = driver.findElements(By.cssSelector(selector));
        for(WebElement cell: cells){
            System.out.println(cell.getText());
        }
    }
}


