package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div.register-or-login > span.text-link.theme")
    WebElement toRegistrationPage;

    @FindBy (xpath = "//form/div[1]/label/input")
    public WebElement mailInput;

    @FindBy (xpath = "//form/div[2]/label/input")
    public WebElement passwordInput;

    @FindBy(xpath = "//form/button")
    public WebElement submitBtn;

    @FindBy(xpath = "//form/div[4]")
    public  WebElement passwordErrorMassage;

    @FindBy(linkText = "סכום")
    WebElement amountDropDown;

    public void goToRegistration(){
        clickElement(toRegistrationPage);
    }

    public void setMail(String email) {
        typeText(mailInput,email);
    }

    public void setPassword(String password) {
        typeText(passwordInput,password);
    }

    public void clickSubmit(){
        clickElement(submitBtn);
    }

    public void doLogin(String email, String password){
        setMail(email);
        setPassword(password);
        clickSubmit();
        wait.until(ExpectedConditions.elementToBeClickable(amountDropDown));
    }


}
