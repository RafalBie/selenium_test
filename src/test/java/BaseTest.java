
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class BaseTest extends SeleniumSetup {
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test
    void name() {
        driver.get(APP_URL);
        loginPage.login("admin", "admin123");
        String message = loginPage.getLoginMessageText();
//        WebElement element = driver.findElement(By.id("login-username"));
//        element.sendKeys("admin");
//        driver.findElement(By.id("login-password")).sendKeys("admin123");
//        driver.findElement(By.id("login-submit")).click();
//        String text = driver.findElement((By.id("login-message"))).getText();
        Assertions.assertEquals("Zalogowano jako: admin", message);

    }
    @Test
    void shouldShowErrorMessageforWrongPassword() {
        driver.get(APP_URL);
        loginPage.login("admin", "admin1234");
        String message = loginPage.getLoginMessageText();
        Assertions.assertEquals("Nieprawidłowy login lub hasło.", message);
    }
    @Test
    void shouldShowErrorMessageforWrongUsername() {
        driver.get(APP_URL);
        loginPage.login("admin", "admin1234");
        String message = loginPage.getLoginMessageText();
        Assertions.assertEquals(loginPage.getLoginErrorMessageText(), message);
    }
    @Test
    void shouldNotBeEmptyInput() {
        driver.get(APP_URL);
        loginPage.login("", "admin123");
        String message = loginPage.getLoginMessageText();
        Assertions.assertEquals(loginPage.getLoginEmptyMessageText(), message);
    }

}
