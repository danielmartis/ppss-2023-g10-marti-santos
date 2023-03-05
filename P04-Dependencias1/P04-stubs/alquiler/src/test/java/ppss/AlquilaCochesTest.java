package ppss;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AlquilaCochesTest {

    AlquilaCochesStub acs = new AlquilaCochesStub();
    CalendarioStub cs = new CalendarioStub();
    ServicioStub ss = new ServicioStub();
    LocalDate da;
    int dias;
    Ticket ti;
    TipoCoche tc;
    @Test
    public void alquilaCochesC1() {
        tc = TipoCoche.TURISMO;
        da = LocalDate.parse("2023-05-18");
        dias = 10;

        ti = assertDoesNotThrow(() -> acs.calculaPrecio(tc, da, dias), "Excepcion lanzada");
        assertEquals(75,ti.getPrecio_final());

    }
    @Test
    public void alquilaCochesC2() {
        tc = TipoCoche.CARAVANA;
        da = LocalDate.parse("2023-06-19");
        dias = 7;

        ti = assertDoesNotThrow(() -> acs.calculaPrecio(tc, da, dias), "Excepcion lanzada");
        assertEquals(62.5,ti.getPrecio_final());

    }

    @Test
    public void alquilaCochesC3() {
        tc = TipoCoche.CARAVANA;
        da = LocalDate.parse("2023-04-17");
        dias = 8;

        MensajeException exception = assertThrows(MensajeException.class, ()-> acs.calculaPrecio(tc,da,dias));
        assertEquals("Error en dia: 2023-04-18; Error en dia: 2023-04-21; Error en dia: 2023-04-22; ", exception.getMessage());
    }


}
