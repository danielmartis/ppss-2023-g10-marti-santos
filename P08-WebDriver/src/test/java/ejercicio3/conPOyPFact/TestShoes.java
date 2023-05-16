package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.TestInstance.Lifecycle;
@TestInstance(Lifecycle.PER_CLASS)
public class TestShoes {
    public WebDriver driver = new ChromeDriver();
    MyAccountPage ma;
    ShoesPage sp;
    @BeforeAll
    public void saveCookies(){
        Cookies.storeCookiesToFile("dasd@alu.ua.es","123456");
    }

    @BeforeEach
    public void onSetup(){
        Cookies.loadCookiesFromFile(driver);
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        ma = (MyAccountPage) PageFactory.initElements(driver, MyAccountPage.class);
        System.out.println(ma);

    }
    @Test
    public void compareShoes(){
        Assertions.assertEquals("My Account", ma.getTitle());
        sp = ma.getShoesPage();



    }

    @AfterEach
    public void end(){
        driver.close();
    }


}
