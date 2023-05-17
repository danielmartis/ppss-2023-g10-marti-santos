package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;


public class Products {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[3]/button/span/span")
    WebElement close;

    String handleId;
    String handleIdFrom;
    public Products(WebDriver wd){
        driver = wd;
    }

    public ShoesPage close(){
        close.click();
        driver.switchTo().window(handleIdFrom);
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public void setHandleId(String h){
        handleId = h;
    }
    public void setHandleIdFrom(String h){
        handleIdFrom = h;
    }
}
