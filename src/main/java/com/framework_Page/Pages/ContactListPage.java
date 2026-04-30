package com.framework_Page.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactListPage {

    WebDriver driver;
    WebDriverWait wait;

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

By contactListTitle = By.xpath("//p[normalize-space()='Click on any contact to view the Contact Details']");
By addNewContactButton = By.xpath("//button[@id='add-contact']");
By addContactHeading= By.xpath("//h1[normalize-space()='Add Contact']");
By contactPageFirstName = By.xpath("//input[@id='firstName']");
By contactPageLastName = By.xpath("//input[@id='lastName']");
By contactPageEmail = By.xpath("//input[@id='email']");
By contactPagePhone = By.xpath("//input[@id='phone']");
By contactPageAddress = By.xpath("//input[@id='street1']");
By contactPageCity = By.xpath("//input[@id='city']");
By contactPagePostcode = By.xpath("//input[@id='postalCode']");
By contactPageCountry = By.xpath("//input[@id='country']");
By contactPageDOB = By.xpath("//input[@id='birthdate']");
By contactPageStateOfProvince=  By.xpath("//input[@id='stateProvince']");
By contactPageSubmitButton = By.xpath("//button[@id='submit']");
By contactPageLogout = By.xpath("//button[@id='logout']");
By contactPageTable= By.xpath("//table[@id='myTable']");






    public  String contactListTitleDisplayed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactListTitle));
        return driver.findElement(contactListTitle).getText();
    }

    public void clickAddNewContactButton(){
        driver.findElement(addNewContactButton).click();
    }

    public String checkAddContactHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(addContactHeading));
       return driver.findElement(addContactHeading).getText();
    }

    public void enterContractListPageFirstName(String firstName){
        driver.findElement(contactPageFirstName).sendKeys(firstName);
    }

    public void enterContractListPageLastName(String lastName){
        driver.findElement(contactPageLastName).sendKeys(lastName);
    }

    public void enterContractListPageEmail(String email){
        driver.findElement(contactPageEmail).sendKeys(email);
    }

    public void enterContractListPagePhone(String phone){
        driver.findElement(contactPagePhone).sendKeys(phone);
    }

    public void enterContractListPageAddress(){
        driver.findElement(contactPageAddress).sendKeys("Pune");
    }

    public void enterContractListPageCity(){
        driver.findElement(contactPageCity).sendKeys("Pune");
    }

    public void enterContractListPagePostcode(){
        driver.findElement(contactPagePostcode).sendKeys("411058");
    }

    public void enterContractListPageCountry(){
        driver.findElement(contactPageCountry).sendKeys("India");
    }

    public void enterContractListPageDOB(String dob){
        driver.findElement(contactPageDOB).sendKeys(dob);
    }

    public void enterContractListPageStateOfProvince(){
        driver.findElement(contactPageStateOfProvince).sendKeys("Maharashtra");
    }

    public void clickcontactListPageSubmitButton(){
        driver.findElement(contactPageSubmitButton).click();
    }



    public boolean isContactPresent(String expectedName, String expectedEmail){
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPageTable));
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='myTable']"));

        for(WebElement row : rows){

            String rowText = row.getText();

            if(rowText.contains(expectedName) && rowText.contains(expectedEmail)){
                return true;
            }
        }
        return false;
    }

    public void clickOnLogoutButton(){
        driver.findElement(contactPageLogout).click();
    }









}
