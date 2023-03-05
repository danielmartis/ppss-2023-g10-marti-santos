package ppss.ejercicio1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("ej1")
public class GestorLlamadasTest {
    GestorLlamadaStub gls = new GestorLlamadaStub();
    @Test
    public void gestorLlamadaC1() {
        gls.setHora(15);
        double precio = gls.calculaConsumo(10);
        assertEquals(precio, 208);
    }

    @Test
    public void gestorLlamadaC2() {
        gls.setHora(22);
        double precio = gls.calculaConsumo(10);
        assertEquals(precio, 105);
    }
}
