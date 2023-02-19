package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

public class FicheroTest {
    String fichero;
    FicheroTexto ft;

    int resultado;
    @Test
    public void contarCaracteresC1() {
        fichero = "ficheroC1.txt";
        ft = new FicheroTexto();
        FicheroException fe = assertThrows(FicheroException.class, () -> ft.contarCaracteres(fichero));
        assertEquals("ficheroC1.txt (No existe el archivo o el directorio)", fe.getMessage());
    }

    @Test
    public void contarCaracteresC2() {
        String f = "/home/ppss/practicas/ppss-2023-g10-marti-santos/P02-Drivers/drivers/src/test/java/resources/ficheroCorrecto.txt";
        ft = new FicheroTexto();
        resultado = assertDoesNotThrow(() -> ft.contarCaracteres(f), "Excepcion lanzada");
        assertEquals(3, resultado);
    }

    @Test
    @Tag("excluido")
    public void contarCaracteresC3(){
        fail();
    }

    @Test
    @Tag("excluido")
    public void contarCaracteresC4(){
        fail();
    }
}
