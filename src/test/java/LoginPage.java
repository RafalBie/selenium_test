import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class LoginPage {
    private final String loginUserNameLocator = "login-username";
    private final String loginPasswordLocator = "login-password";
    private final String loginSubmitLocator = "login-submit";
    private final String loginMessageLocator = "login-message";
    private final String loginCorrectMessage = "Zalogowano jako: admin";
    private final String loginErrorMessage = "Nieprawidłowy login lub hasło.";
    private final String loginEmptyMessage = "Wypełnij wszystkie pola.";

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUserNameField() {
        return driver.findElement(By.id(loginUserNameLocator));
    }
    public WebElement getUserPasswordField() {
        return driver.findElement(By.id(loginPasswordLocator));
    }
    public WebElement getLoginSubmitButton() {
        return driver.findElement(By.id(loginSubmitLocator));
    }
    public WebElement getLoginMessageLocator() {
        return driver.findElement(By.id(loginMessageLocator));
    }
    public WebElement getLoginCorrectMessage() {
        return driver.findElement(By.id(loginCorrectMessage));
    }
    public void login(String username, String password) {
        getUserNameField().sendKeys(username);
        getUserPasswordField().sendKeys(password);
        getLoginSubmitButton().click();
    }
    public String getLoginMessageText() {
        return getLoginMessageLocator().getText();
    }
}

// zrefaktorowac  testy  z basetesty tutaj