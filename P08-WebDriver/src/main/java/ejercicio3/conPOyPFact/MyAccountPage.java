package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.By.cssSelector;

public class MyAccountPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"nav\"]/ol/li[3]/a") WebElement menu;
    @FindBy(xpath = "//*[@id=\"nav\"]/ol/li[3]/ul/li[4]/a") WebElement sho;

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public ShoesPage getShoesPage(){
        Actions builder = new Actions(driver);
        builder.moveToElement(menu);
        builder.perform();
        sho.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }
}
