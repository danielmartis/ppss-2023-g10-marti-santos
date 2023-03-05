package ppss;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservaTest {
    ReservaStub rs = new ReservaStub();
    static OperacionStub os = new OperacionStub();



    @BeforeEach
    public void setValida(){
        os.setValida(true);
    }
    @Test
    public void reservaC1(){
        Factoria.setServicio(os);
        ppss.excepciones.ReservaException re =assertThrows(ppss.excepciones.ReservaException.class, ()-> rs.realizaReserva("xxxx", "xxxx", "Luis", new String[]{"11111"}));
        assertEquals("ERROR de permisos; ", re.getMessage());
    }
    @Test
    public void reservaC2(){
        Factoria.setServicio(os);
        assertDoesNotThrow(()-> rs.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111","22222"}), "Exception thrown.");
    }

    @Test
    public void reservaC3(){
        Factoria.setServicio(os);
        ppss.excepciones.ReservaException re =assertThrows(ppss.excepciones.ReservaException.class, ()-> rs.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111","33333","44444"}));
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", re.getMessage());
    }
    @Test
    public void reservaC4(){
        Factoria.setServicio(os);
        ppss.excepciones.ReservaException re =assertThrows(ppss.excepciones.ReservaException.class, ()-> rs.realizaReserva("ppss", "ppss", "Pepe", new String[]{"11111"}));
        assertEquals("SOCIO invalido; ", re.getMessage());
    }

    @Test
    public void reservaC5(){
        Factoria.setServicio(os);
        os.setValida(false);
        ppss.excepciones.ReservaException re =assertThrows(ppss.excepciones.ReservaException.class, ()-> rs.realizaReserva("ppss", "ppss", "Luis", new String[]{"11111"}));
        assertEquals("CONEXION invalida; ", re.getMessage());
    }
}
