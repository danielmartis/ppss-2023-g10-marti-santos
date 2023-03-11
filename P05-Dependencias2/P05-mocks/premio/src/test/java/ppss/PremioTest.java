package ppss;

import org.junit.jupiter.api.Test;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import static org.junit.jupiter.api.Assertions.*;

public class PremioTest {
    String res;
    @Test
    public void premioC1(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        ClienteWebService cm = ctrl.mock(ClienteWebService.class);
        Premio pm = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);

        EasyMock.expect(pm.generaNumero()).andStubReturn(0.07f);
        assertDoesNotThrow(() -> EasyMock.expect(cm.obtenerPremio()).andStubReturn("entrada final Champions"));
        ctrl.replay();
        pm.setCliente(cm);
        res = pm.compruebaPremio();
        ctrl.verify();
        assertEquals("Premiado con entrada final Champions", res);
    }

    @Test
    public void premioC2(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        ClienteWebService cm = ctrl.mock(ClienteWebService.class);
        Premio pm = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);
        pm.setCliente(cm);
        EasyMock.expect(pm.generaNumero()).andStubReturn(0.03f);
        assertDoesNotThrow(()-> EasyMock.expect(cm.obtenerPremio()).andStubThrow(new ClienteWebServiceException("No se ha podido obtener el premio")));


        //EasyMock.expect(cm.obtenerPremio()).andStubThrow(new ClienteWebServiceException("No se ha podido obtener el premio")));

        //EasyMock.expectLastCall().andStubThrow(new ClienteWebServiceException("No se ha podido obtener el premio"));


        ctrl.replay();

        res = pm.compruebaPremio();
        ctrl.verify();
        assertEquals("No se ha podido obtener el premio", res);
    }

    @Test
    public void premioC3(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        ClienteWebService cm = ctrl.mock(ClienteWebService.class);
        Premio pm = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").mock(ctrl);

        EasyMock.expect(pm.generaNumero()).andStubReturn(0.3f);
        ctrl.replay();
        pm.setCliente(cm);
        res = pm.compruebaPremio();
        ctrl.verify();
        assertEquals("Sin premio", res);
    }
}
