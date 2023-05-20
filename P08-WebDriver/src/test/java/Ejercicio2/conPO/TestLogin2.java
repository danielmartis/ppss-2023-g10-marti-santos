package Ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestLogin2 {

    public WebDriver driver;
    public CustomerLoginPage clp;

    public HomePage hp;
    public MyAccountPage map;
    @BeforeEach
    public void onSetup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        chromeOptions.setHeadless(headless);
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://demo-store.seleniumacademy.com");
        driver.manage().window().maximize();
        hp = new HomePage(driver);
        clp = null;
        map = null;

    }
    @Test
    public void test_Login_Correct(){
        Assertions.assertEquals("Madison Island", hp.getTitle());
        hp.pressAccount();
        clp = hp.pressLog();
        Assertions.assertEquals("Customer Login", clp.getTitle());
        map = clp.writeCorrectData("dasd@alu.ua.es", "123456");
        Assertions.assertEquals("My Account", map.getTitle());


    }
    @Test
    public void tets_Login_Incorrect(){
        Assertions.assertEquals("Madison Island", hp.getTitle());
        hp.pressAccount();
        clp = hp.pressLog();
        Assertions.assertEquals("Customer Login", clp.getTitle());
        clp = clp.writeIncorrectData("dasd@alu.ua.es", "1234567");

        Assertions.assertEquals("Invalid login or password.", clp.getError());
    }

    @AfterEach
    public void end(){
        driver.close();
    }
}
