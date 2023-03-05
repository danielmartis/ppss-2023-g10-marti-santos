package ppss.ejercicio2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Tag("ej2")
public class GestorLlamadasTest {
    GestorLlamadasStub gl = new GestorLlamadasStub();
    CalendarioStub cs = new CalendarioStub();
    @Test
    public void gestorLlamadasC1(){
        gl.setCalendario(cs);
        gl.setHora(15);
        double precio = gl.calculaConsumo(10);

        assertEquals(208,precio);
    }

    @Test
    public void gestorLlamadasC2(){
        gl.setCalendario(cs);
        gl.setHora(22);
        double precio = gl.calculaConsumo(10);

        assertEquals(105,precio);
    }
}
