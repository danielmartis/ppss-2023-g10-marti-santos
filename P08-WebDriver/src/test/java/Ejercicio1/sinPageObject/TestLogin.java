package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin {
    WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void onSetup(){
        driver.get("http://demo-store.seleniumacademy.com");
        driver.manage().window().maximize();

    }
    @Test
    public void loginOK(){
        Assertions.assertEquals("Madison Island",driver.getTitle());
        WebElement acc = driver.findElement(By.cssSelector(".skip-account > span:nth-child(2)"));
        acc.click();
        WebElement log = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
        log.click();
        WebElement name = driver.findElement(By.cssSelector("#email"));
        name.sendKeys("dasd@alu.ua.es");
        WebElement form = driver.findElement(By.xpath("//*[@id=\"login-form\"]"));
        form.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement ad = driver.findElement(By.xpath("//*[@id=\"advice-required-entry-pass\"]"));
        Assertions.assertEquals("This is a required field.", ad.getText());

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        pass.sendKeys("123456");
        form.submit();
        Assertions.assertEquals("My Account", driver.getTitle());



    }
    @Test
    public void loginFailed(){
        Assertions.assertEquals("Madison Island",driver.getTitle());
        WebElement acc = driver.findElement(By.cssSelector(".skip-account > span:nth-child(2)"));
        acc.click();
        WebElement log = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
        log.click();
        Assertions.assertEquals("Customer Login", driver.getTitle());
        WebElement name = driver.findElement(By.cssSelector("#email"));
        name.sendKeys("dasd@alu.ua.es");

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        pass.sendKeys("1234567");

        WebElement form = driver.findElement(By.xpath("//*[@id=\"login-form\"]"));
        form.submit();

        WebElement ad = driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul/li/ul/li"));
        Assertions.assertEquals("Invalid login or password.", ad.getText());


    }

    @AfterEach
    public void end(){
        //driver.close();
    }
}
