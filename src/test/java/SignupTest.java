import com.framework_Page.BaseClass;
import com.framework_Page.Pages.SignupPage;
import com.framework_Page.Utilities.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignupTest extends BaseClass {

SignupPage signupPage;
BaseClass baseClass;
String email = TestDataUtils.generateEmail();
String password = TestDataUtils.generatePassword();

@BeforeTest
    public void setup() throws IOException {
    setUp();
    signupPage= new SignupPage(driver);
}

@AfterTest
public void tear_down() {
    tearDown();
}

@Test
    public void VerifyLoginPage() {

    signupPage.enterLoginEmail(email);
    signupPage.enterLoginPassword(password);
    signupPage.clickSubmitButton();

    }


@Test
       public void VerifySignupPage() {

       signupPage.enterEmail(email);
       signupPage.enterPassword(password);
       signupPage.clickSignupButton();
       Assert.assertEquals(signupPage.checkDetailedFormDisplayed(),"Add User");
       signupPage.enterFirstName();
       signupPage.enterLastName();
       signupPage.enterEmail(email);
       signupPage.enterPassword(password);
       signupPage.enterSubmitButton();
       }













}
