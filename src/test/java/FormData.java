public class FormData {
    private  String firstname = "Rafal";
    private String lastname = "Kowalski";
    private  String email = "we@op.pl";
    private  Gender gender = Gender.MAN;
    private  String age = "31";
    private  String role = "Administrator";
    private  boolean termsForm = true;
    private  boolean submit = true;
    private  boolean reset = false;

    public FormData withFirstname(String firstname){
        this.firstname = firstname;
        return this;
    }
    public FormData withEmail(String email){
        this.email = email;
        return this;
    }
    public FormData withoutTerms(){
        this.termsForm = false;
        return this;
    }
    public FormData clean () {
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.gender = null;
        this.age = "";
        this.role = "";
        this.termsForm = false;
        this.submit = false;
        this.reset = false;
        return this;
    }

    public FormData() {

    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }
    public boolean isTermsAccepted() {
        return termsForm;
    }
    public boolean isSubmit() {
        return submit;
    }
    public boolean isReset() {
        return reset;
    }
}
