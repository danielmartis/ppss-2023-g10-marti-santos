package Ejercicio2.conPO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
    public WebDriver driver;
    public WebElement account;
    public WebElement log;
    public HomePage(WebDriver cd){
        driver = cd;
        account = driver.findElement(By.cssSelector(".skip-account > span:nth-child(2)"));
    }
    public void pressAccount(){
        account.click();
        log = driver.findElement(By.xpath("//*[@id=\"header-account\"]/div/ul/li[6]/a"));
    }

    public CustomerLoginPage pressLog(){
        log.click();
        return new CustomerLoginPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
