package com.framework_Page.Pages;

import com.framework_Page.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage  {

    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

By signUpButton = By.xpath("//button[@id='signup']");
By textAddUser= By.xpath("//h1[normalize-space()='Add User']");
By firstName=By.xpath("//input[@id='firstName']");
By lastName=By.xpath("//input[@id='lastName']");
By entereEmailId=By.xpath("//input[@id='email']");
By enterPasswordField=By.xpath("//input[@id='password']");
By submitButton=By.xpath("//button[@id='submit']");
By loginEmail=By.xpath("//input[@id='email']");
By loginPassword=By.xpath("//input[@id='password']");
By clickSubmitButton=By.xpath("//button[@id='submit']");




      public void enterLoginEmail(String email){
        driver.findElement(loginEmail).sendKeys(email);
      }

      public void enterLoginPassword(String password){
        driver.findElement(loginPassword).sendKeys(password);
      }

      public void clickSubmitButton(){
          driver.findElement(submitButton).click();
      }


      public void clickSignupButton() {
          driver.findElement(signUpButton).click();
      }

      public String checkDetailedFormDisplayed() {
          return driver.findElement(textAddUser).getText();
      }

      public void enterFirstName() {
          wait.until(ExpectedConditions.visibilityOfElementLocated(textAddUser));
          driver.findElement(firstName).sendKeys("Ragini");
      }

      public void enterLastName() {
          driver.findElement(lastName).sendKeys("Kulkarni");
      }

      public void enterEmail(String email) {
          driver.findElement(this.entereEmailId).sendKeys(email);
      }

      public void enterPassword(String password) {
          driver.findElement(enterPasswordField).sendKeys(password);
      }

      public void enterSubmitButton() {
          driver.findElement(submitButton).click();
      }


}

