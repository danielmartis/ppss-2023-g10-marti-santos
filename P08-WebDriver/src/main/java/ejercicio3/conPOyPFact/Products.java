package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;


public class Products {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[3]/button/span/span")
    WebElement close;
    public Products(WebDriver wd){
        driver = wd;
    }

    public void close(){
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);
        close.click();
        driver.switchTo().window(handleIds[0]);



    }
}
