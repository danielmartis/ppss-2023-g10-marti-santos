package ejercicio3.conPOyPFact;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

@TestInstance(Lifecycle.PER_CLASS)
public class TestShoes {
    public WebDriver driver;
    MyAccountPage ma;
    ShoesPage sp;

    Products pro;
    @BeforeAll
    public void saveCookies(){
        Cookies.storeCookiesToFile("dasd@alu.ua.es","123456");
    }

    @BeforeEach
    public void onSetup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        chromeOptions.setHeadless(headless);
        driver = new ChromeDriver(chromeOptions);
        Cookies.loadCookiesFromFile(driver);
        driver.manage().window().maximize();
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ma = PageFactory.initElements(driver, MyAccountPage.class);

    }
    @Test
    public void compareShoes(){
        Assertions.assertEquals("My Account", ma.getTitle());
        sp = ma.getShoesPage();

        Assertions.assertEquals("Shoes - Accessories", sp.getTitle());
        sp.selectShoeToCompare(5);
        sp.selectShoeToCompare(6);
        pro = sp.compare();
        Assertions.assertEquals("Products Comparison List - Magento Commerce", driver.getTitle());
        pro.close();
        Assertions.assertEquals("Shoes - Accessories", driver.getTitle());
        sp.clear();
        String mensaje = sp.getMensaje();
        Assertions.assertEquals("The comparison list was cleared.", mensaje);


    }

    @AfterEach
    public void end(){
        driver.close();
    }


}
