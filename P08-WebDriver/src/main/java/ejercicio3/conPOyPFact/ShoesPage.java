package ejercicio3.conPOyPFact;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ShoesPage {
    WebDriver driver;

    String ventana;

    Products prod;

    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[5]/div/div[2]/ul/li[2]/a")
    WebElement wingtipShoe;
    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/ul/li[6]/div/div[2]/ul/li[2]/a")
    WebElement suedeShoe;

    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[3]/div/div[2]/div/button")
    WebElement compare;

    @FindBy(xpath = "/html/body/div/div[2]/div[2]/div/div[3]/div/div[2]/div/a")
    WebElement clear;

    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div[2]/div[2]/ul/li/ul/li")
    WebElement message;

    public ShoesPage (WebDriver wd){
        driver = wd;
    }

    public String getTitle(){
        return driver.getTitle();
    }
    public void selectShoeToCompare(int number){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch(number){
            case 5:
                jse.executeScript("arguments[0].scrollIntoView()", wingtipShoe);
                wingtipShoe.click();
                break;
            case 6:
                jse.executeScript("arguments[0].scrollIntoView()", wingtipShoe);
                suedeShoe.click();
                break;
        }
    }

    public Products compare(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", compare);
        compare.click();
        ventana = driver.getWindowHandle();
        Set<String> setIds = driver.getWindowHandles();
        String[] handleIds = setIds.toArray(new String[setIds.size()]);

        prod = PageFactory.initElements(driver, Products.class);
        prod.setHandleId(handleIds[1]);
        prod.setHandleIdFrom(handleIds[0]);
        driver.switchTo().window(handleIds[1]);
        return prod;
    }

    public void clear(){
        clear.click();
        Alert alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        alert.accept();
        //alert.dismiss();
    }

    public String getMensaje(){
        return message.getText();
    }
}
