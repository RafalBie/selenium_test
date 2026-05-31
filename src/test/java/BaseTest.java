import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class BaseTest {
    private LoginPage loginPage;
    protected static final String APP_URL;
    protected WebDriver driver;
    static {

        // Próbuje znaleźć plik automatycznie w katalogu projektu
        File f = new File("src/test/resources/selenium_demo_app.html");
        APP_URL = "file:///" + f.getAbsolutePath().replace("\\", "/");
    }
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    void name() {
        driver.get(APP_URL);
        loginPage.login("admin", "admin123");
//        WebElement element = driver.findElement(By.id("login-username"));
//        element.sendKeys("admin");
//        driver.findElement(By.id("login-password")).sendKeys("admin123");
//        driver.findElement(By.id("login-submit")).click();
        String text = driver.findElement((By.id("login-message"))).getText();
        Assertions.assertEquals("Zalogowano jako: admin", text);

    }
    @Test
    void shouldShowErrorMessageforWrongPassword() {
        driver.get(APP_URL);
        WebElement element = driver.findElement(By.id("login-username"));
        element.sendKeys("admin");
        driver.findElement(By.id("login-password")).sendKeys("123");
        driver.findElement(By.id("login-submit")).click();
        String text = driver.findElement((By.id("login-message"))).getText();
        Assertions.assertEquals("Nieprawidłowy login lub hasło.", text);
    }
    @Test
    void shouldShowErrorMessageforWrongUsername() {
        driver.get(APP_URL);
        WebElement element = driver.findElement(By.id("login-username"));
        element.sendKeys("admin123");
        driver.findElement(By.id("login-password")).sendKeys("123");
        driver.findElement(By.id("login-submit")).click();
        String text = driver.findElement((By.id("login-message"))).getText();
        Assertions.assertEquals("Nieprawidłowy login lub hasło.", text);
    }
    @Test
    void shouldNotBeEmptyInput() {
        driver.get(APP_URL);
        driver.findElement(By.id("login-submit")).click();
        String text = driver.findElement((By.id("login-message"))).getText();
        Assertions.assertEquals("Wypełnij wszystkie pola.", text);
    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }
}
