package l.page_object;

import comon.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePage extends Base {
    private String title = "The Internet";

    // I. Selectors (class variables).
    @FindBy(id = "flash")
    private WebElement confirm_login_msg_elem;

    @FindBy(css = ".button.secondary.radius")
    private WebElement logout_button_elem;

    // II. Constructor
    public HomePage(WebDriver driver){
        this.driver = driver; // this.driver <= доступен из Base
        assertEquals(title, driver.getTitle());// только одеу проверру разрешается иметь в page object- что мы на той странице где надо - ее можно разместить в конструкторе

    }

    // III. Page Services (class Methods/ Actions)
    public LoginPage logout(){
        clickElement(logout_button_elem);
        return PageFactory.initElements(driver, LoginPage.class); // должен вернуть нас на LoginPage
    }

    public String getLoginConfirmation() {
        return confirm_login_msg_elem.getText();
    }
}
