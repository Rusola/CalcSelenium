package l.page_object;

import comon.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Base {
    private String title = "The Internet";

    // I. Elements.
    @FindBy(id = "flash")
    private WebElement confirm_login_msg_elem;

    @FindBy(css = ".button.secondary.radius")
    private WebElement logout_button_elem;

    // II. Constructor
    public HomePage(WebDriver driver){
        this.driver = driver; // this.driver <= доступен из Base

    }

    // III. Methods (Services/ Actions
    public LoginPage logout(){
        clickElement(logout_button_elem);
        return PageFactory.initElements(driver, LoginPage.class); // должен вернуть нас на LoginPage
    }

    public String getLoginConfirmation() {
        return confirm_login_msg_elem.getText();
    }
}
