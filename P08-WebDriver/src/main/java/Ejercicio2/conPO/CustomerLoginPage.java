package Ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    public WebDriver driver;
    public WebElement userText;
    public WebElement passText;
    public WebElement button;
    public WebElement advice;
    public CustomerLoginPage(WebDriver wd){
        driver = wd;
        userText = driver.findElement(By.cssSelector("#email"));
        passText = driver.findElement(By.cssSelector("#pass"));
        button = driver.findElement(By.cssSelector("#login-form"));
    }

    public MyAccountPage writeCorrectData(String em, String pas){
        userText.sendKeys(em);
        passText.sendKeys(pas);
        button.submit();
        return new MyAccountPage(driver);
    }

    public CustomerLoginPage writeIncorrectData(String em, String pas){
        userText.sendKeys(em);
        passText.sendKeys(pas);
        button.submit();
        return new CustomerLoginPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getError(){
        advice = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li"));
        return advice.getText();
    }




}
