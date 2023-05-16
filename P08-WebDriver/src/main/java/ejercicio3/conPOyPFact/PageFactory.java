package ejercicio3.conPOyPFact;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class PageFactory {
    public static Object initElements(WebDriver wd, Class c) {
        try {
            Class mc = Class.forName(c.getName());
            Object ob = mc.getDeclaredConstructor(WebDriver.class).newInstance(wd);
            return ob;
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e){
            System.out.println(e);
        }
        return null;
    }
}

