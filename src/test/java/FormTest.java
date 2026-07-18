import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class FormTest extends SeleniumSetup {
    private FormPage formPage = new FormPage(driver);
    @Test
    public void shouldSubmitFormWithValidData(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
       FormData formData = new FormData();
        formPage.filiedAllFields(formData);
        formPage.submitForm();
//        driver.findElement(By.id("form-firstname")).sendKeys("Rafal");
//        driver.findElement(By.id("form-lastname")).sendKeys("Kowalski");
//        driver.findElement(By.id("form-email")).sendKeys("test@test.pl");
//        driver.findElement(By.id("form-age")).sendKeys("31");
//        driver.findElement(By.id("form-role")).sendKeys("Użytkownik");
//        driver.findElement(By.id("form-gender-m")).click();
//        driver.findElement(By.id("form-gender-m")).isSelected();
//        driver.findElement(By.id("form-terms")).click();
//        driver.findElement(By.id("form-terms")).isSelected();
//        driver.findElement(By.id("form-submit")).click();
        Assertions.assertEquals("Formularz wysłany! Witaj, Rafal Kowalski.", driver.findElement(By.id("form-message")).getText());
    }
    @Test
    public void shouldSubmitFormWithEmptyData(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
        formPage.submitForm();
//        driver.findElement(By.id("form-firstname")).sendKeys("Rafal");
//        driver.findElement(By.id("form-submit")).click();
        Assertions.assertEquals("Wypełnij wymagane pola (*)",driver.findElement(By.id("form-message")).getText());
    }
    @Test
    public void WrongEmailAddress(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
        FormData formData = new FormData().withEmail("test");

        formPage.filiedAllFields(formData);
        formPage.submitForm();
//        driver.findElement(By.id("form-firstname")).sendKeys("Rafal");
//        driver.findElement(By.id("form-lastname")).sendKeys("Kowalski");
//        driver.findElement(By.id("form-email")).sendKeys("test");
//        driver.findElement(By.id("form-age")).sendKeys("31");
//        driver.findElement(By.id("form-role")).sendKeys("Użytkownik");
//        driver.findElement(By.id("form-gender-m")).click();
//        driver.findElement(By.id("form-gender-m")).isSelected();
//        driver.findElement(By.id("form-terms")).click();
//        driver.findElement(By.id("form-terms")).isSelected();
//        driver.findElement(By.id("form-submit")).click();
        Assertions.assertEquals("Nieprawidłowy format e-mail.", driver.findElement(By.id("form-message")).getText());
    }
    @Test
    public void EmailValidation(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
        FormData formData = new FormData().clean().withEmail("raf@op.pl");
        formPage.filiedAllFields(formData);
        formPage.submitForm();
    }
    @Test
    public void AceptRegulamin(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
        FormData formData = new FormData().withoutTerms();
        formPage.filiedAllFields(formData);
        formPage.submitForm();
//        driver.findElement(By.id("form-firstname")).sendKeys("Rafal");
//        driver.findElement(By.id("form-lastname")).sendKeys("Kowalski");
//        driver.findElement(By.id("form-email")).sendKeys("test@op.pl");
//        driver.findElement(By.id("form-age")).sendKeys("31");
//        driver.findElement(By.id("form-role")).sendKeys("Użytkownik");
//        driver.findElement(By.id("form-gender-m")).click();
//        driver.findElement(By.id("form-gender-m")).isSelected();
//       driver.findElement(By.id("form-terms")).click();
//        driver.findElement(By.id("form-submit")).click();
        Assertions.assertEquals("Musisz zaakceptować regulamin.", driver.findElement(By.id("form-message")).getText());
    }
    @Test
    public void ProperAge(){
        driver.get(APP_URL);
        driver.findElement(By.id("tab-form")).click();
        driver.findElement(By.id("form-age")).sendKeys("31");
        String agetext = driver.findElement(By.id("form-age")).getAttribute("value");
        int age = Integer.parseInt(agetext);
        Assertions.assertTrue(age > 0 && age < 120 );
    }


}
