import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//public class CalculatorTest extends SeleniumSetup {
//    @Test
//    void AddingNumbers(){
//        driver.get(APP_URL);
//        driver.findElement(By.id("tab-calc")).click();
//        driver.findElement(By.id("calc-8")).click();
//        driver.findElement(By.id("calc-add")).click();
//        driver.findElement(By.id("calc-5")).click();
//        driver.findElement(By.id("calc-eq")).click();
//        Assertions.assertEquals("13",driver.findElement(By.id("calc-display")).getText());
//
//    }
//}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CalculatorTest extends SeleniumSetup {

    private void openCalculator() {
        driver.get(APP_URL);
        driver.findElement(By.id("tab-calc")).click();
    }

    private void click(String id) {
        driver.findElement(By.id(id)).click();
    }

    private String getDisplayText() {
        return driver.findElement(By.id("calc-display")).getText();
    }

    private String getHistoryText() {
        return driver.findElement(By.id("calc-history")).getText();
    }

    @Test
    void shouldAddTwoNumbers() {
        openCalculator();

        click("calc-8");
        click("calc-add");
        click("calc-5");
        click("calc-eq");

        Assertions.assertEquals("13", getDisplayText());
    }

    @Test
    void shouldSubtractTwoNumbers() {
        openCalculator();

        click("calc-8");
        click("calc-sub");
        click("calc-5");
        click("calc-eq");

        Assertions.assertEquals("3", getDisplayText());
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        openCalculator();

        click("calc-8");
        click("calc-mul");
        click("calc-5");
        click("calc-eq");

        Assertions.assertEquals("40", getDisplayText());
    }

    @Test
    void shouldDivideTwoNumbers() {
        openCalculator();

        click("calc-8");
        click("calc-div");
        click("calc-4");
        click("calc-eq");

        Assertions.assertEquals("2", getDisplayText());
    }

    @Test
    void shouldShowErrorWhenDividingByZero() {
        openCalculator();

        click("calc-8");
        click("calc-div");
        click("calc-0");
        click("calc-eq");

        Assertions.assertEquals("Błąd", getDisplayText());
    }

    @Test
    void shouldClearDisplay() {
        openCalculator();

        click("calc-8");
        click("calc-clr");

        Assertions.assertEquals("0", getDisplayText());
    }

    @Test
    void shouldChangeSign() {
        openCalculator();

        click("calc-8");
        click("calc-sign");

        Assertions.assertEquals("-8", getDisplayText());
    }

    @Test
    void shouldCalculatePercent() {
        openCalculator();

        click("calc-8");
        click("calc-mod");

        Assertions.assertEquals("0.08", getDisplayText());
    }

    @Test
    void shouldHandleDecimalNumbers() {
        openCalculator();

        click("calc-1");
        click("calc-dot");
        click("calc-5");
        click("calc-add");
        click("calc-2");
        click("calc-dot");
        click("calc-5");
        click("calc-eq");

        Assertions.assertEquals("4", getDisplayText());
    }

    @Test
    void shouldShowLastOperationInHistory() {
        openCalculator();

        click("calc-8");
        click("calc-add");
        click("calc-5");
        click("calc-eq");

        Assertions.assertEquals("8 + 5 = 13", getHistoryText());
    }
}
// testy matematycze, dzielenie, mnozenie przez 0, usuwanie, ostatnia operacja pod kalkulatorem, dziwne przypadki, rozne przypadki
