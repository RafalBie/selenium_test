import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {
    private final String firstNameFormField = "form-firstname";
    private final String lastNameFormFiled = "form-lastname";
    private final String emailFormField = "form-email";
    private final String ageFormField = "form-age";
    private final String genderFormFieldMan = "form-gender-m";
    private final String genderFormFieldWoman = "form-gender-f";
    private final String genderFormFieldOther = "form-gender-o";
    private final String roleFormField = "form-role";
    private final String termsFormField = "form-terms";
    private final String submitFormField = "form-submit";
    private final String resetFormField = "form-reset";
    private WebDriver driver;
    public FormPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getFirstNameField() {
        return driver.findElement(By.id(firstNameFormField));
    }
    public WebElement getLastNameField() {
        return driver.findElement(By.id(lastNameFormFiled));
    }
    public WebElement getEmailFormField() {
        return driver.findElement(By.id(emailFormField));
    }
    public WebElement getAgeFormField() {
        return driver.findElement(By.id(ageFormField));
    }
    public WebElement getGenderFormFieldMan() {
        return driver.findElement(By.id(genderFormFieldMan));
    }
    public WebElement getGenderFormFieldWoman() {
        return driver.findElement(By.id(genderFormFieldWoman));
    }
    public WebElement getGenderFormFieldOther() {
        return driver.findElement(By.id(genderFormFieldOther));
    }
    public WebElement getRoleFormField() {
        return driver.findElement(By.id(roleFormField));
    }
    public WebElement getTermsFormField() {
        return driver.findElement(By.id(termsFormField));
    }
    public WebElement getSubmitFormField() {
        return driver.findElement(By.id(submitFormField));
    }
    public WebElement getResetFormField() {
        return driver.findElement(By.id(resetFormField));
    }
    public void submitForm() {
        getSubmitFormField().click();
    }
    public void withEmail(String email) {
        getEmailFormField().getText();
    }
    public void enterAge(String age) {
        getAgeFormField().sendKeys(age);
    }
    public int getAgeValue(){
        String ageText = getAgeFormField().getAttribute("value");
        return Integer.parseInt(ageText);
    }

    public void filiedAllFields(FormData formData) {
        getFirstNameField().sendKeys(formData.getFirstname());
        getLastNameField().sendKeys(formData.getLastname());
        getEmailFormField().sendKeys(formData.getEmail());
        getAgeFormField().sendKeys(formData.getAge());
        getRoleFormField().sendKeys(formData.getRole());
        if (formData.getGender() == Gender.MAN) {
            getGenderFormFieldMan().click();
        }
        if (formData.getGender() == Gender.WOMAN) {
            getGenderFormFieldWoman().click();
        }
        if (formData.getGender() == Gender.OTHER) {
            getGenderFormFieldOther().click();
        }
        if (formData.isTermsAccepted()) {
            getTermsFormField().click();
        };
        if(formData.isSubmit())
        {getSubmitFormField().click();
        }
        if(formData.isReset())
        {getResetFormField().click();}




    }
}

