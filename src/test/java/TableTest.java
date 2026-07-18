import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

//public class TableTest extends SeleniumSetup {
//    @Test
//    public void testTable() {
//        driver.get(APP_URL);
//       driver.findElement(By.id("tab-table")).click();
//
//        driver.findElement(By.id("table-filter")).sendKeys("Now");
//        List<WebElement> rows = driver.findElements(By.cssSelector("#table-body tr"));
//        List<String> fullNames = List.of("Anna Nowak");
//        List<String> records = rows.stream().map(webElement -> webElement.findElements(By.cssSelector("td")).get(1).getText()).toList();
//        System.out.println(records);
//        for ( String fullName : fullNames ) {
//            Assertions.assertTrue(records.contains(fullName));
//        }
//        Assertions.assertEquals(1,rows.size());
//        Assertions.assertTrue(rows.get(0).getText().contains("Now"));
//        Assertions.assertEquals(
//                "Wyświetlane wiersze: 1 / 8",
//                driver.findElement(By.id("table-count")).getText()
//        );
//
//    }
//}
public class TableTest extends SeleniumSetup {

    private void openTableTab() {
        driver.get(APP_URL);
        driver.findElement(By.id("tab-table")).click();
    }

    private void filterTable(String text) {
        driver.findElement(By.id("table-filter")).sendKeys(text);
    }

    private List<WebElement> getRows() {
        return driver.findElements(By.cssSelector("#table-body tr"));
    }

    private List<String> getNamesFromRows() {
        return getRows().stream()
                .map(row -> row.findElements(By.cssSelector("td")).get(1).getText())
                .toList();
    }

    private String getTableCountText() {
        return driver.findElement(By.id("table-count")).getText();
    }

    private void sortBy(String columnName) {
        driver.findElement(By.cssSelector("th[data-col='" + columnName + "']")).click();
    }
    @Test
    public void shouldFilterTableByNameFragment() {
        openTableTab();

        filterTable("Now");

        List<WebElement> rows = getRows();
        List<String> names = getNamesFromRows();

        Assertions.assertEquals(1, rows.size());
        Assertions.assertTrue(names.contains("Anna Nowak"));
        Assertions.assertEquals("Wyświetlane wiersze: 1 / 8", getTableCountText());
    }
    @Test
    public void shouldFilterTableByEmailFragment() {
        openTableTab();

        filterTable("rafal@example.com");

        List<String> names = getNamesFromRows();

        Assertions.assertEquals(1, getRows().size());
        Assertions.assertTrue(names.contains("Rafał Kowalczyk"));
        Assertions.assertEquals("Wyświetlane wiersze: 1 / 8", getTableCountText());
    }
    @Test
    public void shouldShowZeroRowsWhenFilterDoesNotMatch() {
        openTableTab();

        filterTable("brak-takiej-osoby");

        Assertions.assertEquals(0, getRows().size());
        Assertions.assertEquals("Wyświetlane wiersze: 0 / 8", getTableCountText());
    }
    @Test
    public void shouldShowAllRowsByDefault() {
        openTableTab();

        Assertions.assertEquals(8, getRows().size());
        Assertions.assertEquals("Wyświetlane wiersze: 8 / 8", getTableCountText());
    }
    @Test
    public void shouldSortTableByNameAscending() {
        openTableTab();

        sortBy("name");

        List<String> names = getNamesFromRows();

        Assertions.assertEquals("Anna Nowak", names.get(0));
        Assertions.assertEquals("Ewelina Krawczyk", names.get(1));
        Assertions.assertEquals("Katarzyna Maj", names.get(2));
    }
    @Test
    public void shouldSortTableByNameDescendingAfterSecondClick() {
        openTableTab();

        sortBy("name");
        sortBy("name");

        List<String> names = getNamesFromRows();

        Assertions.assertEquals("Tomasz Lewandowski", names.get(0));
    }
}
