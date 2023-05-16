package Ejercicio2.conPO;

import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    public WebDriver driver;

    public MyAccountPage(WebDriver wd){
        driver = wd;
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
