import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class SeleniumSetup {
    protected static final String APP_URL;
    protected WebDriver driver;
    static {

        // Próbuje znaleźć plik automatycznie w katalogu projektu
        File f = new File("src/test/resources/selenium_demo_app.html");
        APP_URL = "file:///" + f.getAbsolutePath().replace("\\", "/");
    }

    public SeleniumSetup() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }


//    @AfterEach
//    void tearDown() {
//        driver.quit();
//
//    }
}
