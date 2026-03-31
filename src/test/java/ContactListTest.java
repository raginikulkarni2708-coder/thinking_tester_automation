import com.framework_Page.BaseClass;
import com.framework_Page.Pages.ContactListPage;
import com.framework_Page.Pages.SignupPage;
import com.framework_Page.Utilities.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactListTest extends BaseClass {

    SignupPage signupPage;
    ContactListPage contactListPage;

    String email = TestDataUtils.generateEmail();
    String password = TestDataUtils.generatePassword();
    String firstName = TestDataUtils.generateFirstName();
    String lastName = TestDataUtils.generateLastName();
    String phone = TestDataUtils.generatePhoneNumber();
    String dob = TestDataUtils.generateDOB();

@BeforeMethod
    public void setup() throws Exception {

        setUp();
        signupPage = new SignupPage(driver);

        // Generate dynamic data
        //String email = TestDataUtils.generateEmail();
        //String password = TestDataUtils.generatePassword();

        // Perform signup steps
        signupPage.clickSignupButton();
        signupPage.enterFirstName();
        signupPage.enterLastName();
        signupPage.enterEmail(email);
        signupPage.enterPassword(password);
        signupPage.enterSubmitButton();

        // Now user lands on Contact List page
        contactListPage = new ContactListPage(driver);
    }

@AfterTest
    public void tear_down() {
        tearDown();
    }

@Test
    public void VerifyContactListPage() {
    contactListPage.contactListTitleDisplayed();
    Assert.assertEquals(contactListPage.contactListTitleDisplayed(), "Click on any contact to view the Contact Details");
    contactListPage.clickAddNewContactButton();
    contactListPage.checkAddContactHeading();
    Assert.assertEquals(contactListPage.checkAddContactHeading(), "Add Contact");
    contactListPage.enterContractListPageFirstName(firstName);
    contactListPage.enterContractListPageLastName(lastName);
    contactListPage.enterContractListPagePhone(phone);
    contactListPage.enterContractListPageDOB(dob);
    contactListPage.enterContractListPageEmail(email);
    contactListPage.enterContractListPageAddress();
    contactListPage.enterContractListPageCity();
    contactListPage.enterContractListPagePostcode();
    contactListPage.enterContractListPageStateOfProvince();
    contactListPage.clickcontactListPageSubmitButton();
    //contactListPage.checkContactListPageDisplayed();
    //Assert.assertEquals(contactListPage.contactListTitleDisplayed(), "Click on any contact to view the Contact Details");
    // Create full name (depends on UI)
    String fullName = firstName + " " + lastName;
    Assert.assertTrue(
            contactListPage.isContactPresent(fullName, email),
            "Contact not found in table"
    );
    contactListPage.clickOnLogoutButton();
}






}
