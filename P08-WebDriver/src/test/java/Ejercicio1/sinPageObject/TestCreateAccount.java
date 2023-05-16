package Ejercicio1.sinPageObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TestCreateAccount {
    public WebDriver driver = new ChromeDriver();
    @BeforeEach
    public void onSetup(){
        driver.get("http://demo-store.seleniumacademy.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Tag("OnlyOnce")
    @Test
    public void createAccount(){
        Assertions.assertEquals("Madison Island",driver.getTitle() );

        WebElement acc = driver.findElement(By.cssSelector(".skip-account > span:nth-child(2)"));
        acc.click();
        WebElement log = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
        log.click();
        Assertions.assertEquals("Customer Login", driver.getTitle());
        WebElement create = driver.findElement(By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a > span > span"));
        create.click();

        Assertions.assertEquals("Create New Customer Account", driver.getTitle());
        WebElement name = driver.findElement(By.cssSelector("#firstname"));
        name.sendKeys("D");
        WebElement middle = driver.findElement(By.cssSelector("#middlename"));
        middle.sendKeys("M");
        WebElement last = driver.findElement(By.cssSelector("#lastname"));
        last.sendKeys("S");
        WebElement em = driver.findElement(By.cssSelector("#email_address"));
        em.sendKeys("dasd3@alu.ua.es");
        WebElement pass = driver.findElement(By.cssSelector("#password"));
        pass.sendKeys("123456");

        WebElement form = driver.findElement(By.xpath("//*[@id=\"form-validate\"]"));
        form.submit();

        WebElement ad = driver.findElement(By.cssSelector("#advice-required-entry-confirmation"));
        String validate = ad.getText();
        Assertions.assertEquals("This is a required field.",validate);

        WebElement conf = driver.findElement(By.cssSelector("#confirmation"));
        conf.sendKeys("123456");
        form.submit();

        //Assertions.assertEquals("My Account", driver.getTitle());
    }

    @AfterEach
    public void end(){
        driver.close();
    }
}