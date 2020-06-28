package l.page_object;

/* 1. Readability 2. Maintenance when selector is changed 3. Reducing code duplication*/

import comon.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LoginPage extends Base {
    private static String URL = "https://the-internet.herokuapp.com/login"; // static тк будет вызван в статик методе open страницу
    private static String title = "The Internet";

    // I. Elements. // только тут поменяем есди изменится локатор
    @FindBy(id = "username") // DOM locator
    private WebElement username_elem; // variable

    @FindBy(id = "password")
    private WebElement password_elem;

    @FindBy(tagName = "button")
    private WebElement login_button_elem;

    @FindBy(id = "flash")
    private WebElement confirm_logout_msg_elem;

    @FindBy(id = "flash-messages")
    private WebElement error_msg_elem;

    // II. Constructor.
    public LoginPage(WebDriver driver) {
        this.driver = driver; // this.driver <= доступен из Base
    }

    // III. Methods. (Services/ Actions)
    public static LoginPage open(WebDriver driver) {
        driver.get(URL);
        assertEquals(title, driver.getTitle());
        return PageFactory.initElements(driver, LoginPage.class); // proxy object, здесь впервые вызывается LoginPage constructor
    }

    public void submitLogin(String user, String pass) {
        username_elem.sendKeys(user);
        password_elem.sendKeys(pass);
        clickElement(login_button_elem);
    }

    public HomePage validLogin(String user, String pass) {
        submitLogin(user, pass);
        return PageFactory.initElements(driver, HomePage.class);  // должна открыться home page
    }

    public LoginPage invalidLogin(String user, String pass) {
        submitLogin(user, pass);
        return this;  // вернется созданный в open методе LoginPage obj
    }

    public String getLogoutConfirmation() {
        return confirm_logout_msg_elem.getText();
    }

    public String getErrorMessage() {
        return error_msg_elem.getText();
    }

}
