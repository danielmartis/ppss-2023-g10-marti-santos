package ppss;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.easymock.EasyMock;

public class ReservaStubTest {
    @Test
    public void reservaC1(){
        FactoriaBOs fbm = EasyMock.niceMock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock();
        IOperacionBO iom = EasyMock.niceMock(IOperacionBO.class);

        EasyMock.expect(rm.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),EasyMock.anyObject())).andReturn(false);

        EasyMock.replay(fbm);
        EasyMock.replay(iom);
        EasyMock.replay(rm);
        ReservaException re = assertThrows(ReservaException.class, () -> rm.realizaReserva("xxxx", "xxxx", "Pepe", new String[]{"33333"}));
        assertEquals("ERROR de permisos; ", re.getMessage());
        EasyMock.verify(fbm);
        EasyMock.verify(iom);
        EasyMock.verify(rm);
    }

    @Test
    public void reservaC2(){
        FactoriaBOs fbm = EasyMock.niceMock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").niceMock();
        IOperacionBO iom = EasyMock.niceMock(IOperacionBO.class);

        EasyMock.expect(rm.compruebaPermisos("ppss","ppss", Usuario.BIBLIOTECARIO)).andReturn(true);
        EasyMock.expect(rm.getFactoria()).andReturn(fbm);
        EasyMock.expect(fbm.getOperacionBO()).andReturn(iom);

        assertDoesNotThrow(() -> iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString()));
        EasyMock.replay(fbm);
        EasyMock.replay(iom);
        EasyMock.replay(rm);
        assertDoesNotThrow(()-> rm.realizaReserva("ppss", "ppss", "Pepe", new String[]{"22222", "33333"}));

        EasyMock.verify(fbm);
        EasyMock.verify(iom);
        EasyMock.verify(rm);
    }

    @Test
    public void reservaC3(){
        FactoriaBOs fbm = EasyMock.niceMock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock();
        IOperacionBO iom = EasyMock.niceMock(IOperacionBO.class);

        EasyMock.expect(rm.getFactoria()).andReturn(fbm);
        EasyMock.expect(fbm.getOperacionBO()).andReturn(iom);
        EasyMock.expect(rm.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),EasyMock.anyObject())).andStubReturn(true);


        assertDoesNotThrow(() -> {  iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
                                    EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
                                    iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
                                    iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
                                    EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());

        });

        EasyMock.replay(fbm);
        EasyMock.replay(iom);
        EasyMock.replay(rm);
        ReservaException re = assertThrows(ReservaException.class, () -> rm.realizaReserva("ppss", "ppss", "Pepe", new String[]{"11111","22222","55555"}));
        assertEquals("ISBN invalido:11111; ISBN invalido:55555; ", re.getMessage());
        EasyMock.verify(fbm);
        EasyMock.verify(iom);
        EasyMock.verify(rm);
    }

    @Test
    public void reservaC4(){
        FactoriaBOs fbm = EasyMock.niceMock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock();
        IOperacionBO iom = EasyMock.niceMock(IOperacionBO.class);

        EasyMock.expect(rm.getFactoria()).andReturn(fbm);
        EasyMock.expect(fbm.getOperacionBO()).andReturn(iom);
        EasyMock.expect(rm.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),EasyMock.anyObject())).andStubReturn(true);


        assertDoesNotThrow(() -> {  iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
            EasyMock.expectLastCall().andThrow(new SocioInvalidoException());

        });

        EasyMock.replay(fbm);
        EasyMock.replay(iom);
        EasyMock.replay(rm);
        ReservaException re = assertThrows(ReservaException.class, () -> rm.realizaReserva("ppss", "ppss", "Luis", new String[]{"22222"}));
        assertEquals("SOCIO invalido; ", re.getMessage());
        EasyMock.verify(fbm);
        EasyMock.verify(iom);
        EasyMock.verify(rm);
    }

    @Test
    public void reservaC5(){
        FactoriaBOs fbm = EasyMock.niceMock(FactoriaBOs.class);
        Reserva rm = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("getFactoria","compruebaPermisos").mock();
        IOperacionBO iom = EasyMock.niceMock(IOperacionBO.class);

        EasyMock.expect(rm.getFactoria()).andReturn(fbm);
        EasyMock.expect(fbm.getOperacionBO()).andReturn(iom);
        EasyMock.expect(rm.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),EasyMock.anyObject())).andStubReturn(true);


        assertDoesNotThrow(() -> {  iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
            EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
            iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
            iom.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
            EasyMock.expectLastCall().andThrow(new JDBCException());

        });

        EasyMock.replay(fbm);
        EasyMock.replay(iom);
        EasyMock.replay(rm);
        ReservaException re = assertThrows(ReservaException.class, () -> rm.realizaReserva("ppss", "ppss", "Pepe", new String[]{"11111","22222","55555"}));
        assertEquals("ISBN invalido:11111; CONEXION invalida; ", re.getMessage());
        EasyMock.verify(fbm);
        EasyMock.verify(iom);
        EasyMock.verify(rm);
    }
}
