package ppss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import java.io.FileReader;
import java.io.IOException;

public class ReservaTest {

    @Test
    public void reservaC1(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        FactoriaBOs fbm = ctrl.mock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock(ctrl);
        IOperacionBO iom = ctrl.mock(IOperacionBO.class);

        EasyMock.expect(rm.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),EasyMock.anyObject())).andReturn(false);

        ctrl.replay();
        ReservaException re = assertThrows(ReservaException.class, () -> rm.realizaReserva("xxxx", "xxxx", "Pepe", new String[]{"33333"}));
        assertEquals("ERROR de permisos; ", re.getMessage());
        ctrl.verify();
    }

    @Test
    public void reservaC2(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        FactoriaBOs fbm = ctrl.mock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock(ctrl);
        IOperacionBO iom = ctrl.mock(IOperacionBO.class);

        EasyMock.expect(rm.compruebaPermisos("ppss","ppss" ,Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(rm.getFactoria()).andReturn(fbm);
        EasyMock.expect(fbm.getOperacionBO()).andReturn(iom);
        assertDoesNotThrow(() -> iom.operacionReserva("Pepe", "22222"));
        assertDoesNotThrow(() -> iom.operacionReserva("Pepe", "33333"));
        EasyMock.expectLastCall();

        ctrl.replay();
        /*try {
            //rm.compruebaPermisos("ppss", "ppss", Usuario.BIBLIOTECARIO);
            //rm.getFactoria();
            //fbm.getOperacionBO();
            //iom.operacionReserva("Pepe", "22222");
            //iom.operacionReserva("Pepe", "33333");
        }catch(IsbnInvalidoException e){

        }catch(JDBCException e){

        }catch(SocioInvalidoException e){

        }*/
        assertDoesNotThrow(() -> rm.realizaReserva("ppss", "ppss", "Pepe", new String[]{"22222","33333"}));
        ctrl.verify();
    }
}
