package ppss;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;


public class GestorLlamadaTest {
    double result = 0;
    @Test
    public void gestorLlamadasC1(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        C cm = ctrl.mock(C.class);
        GestorLlamadas glm = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        EasyMock.expect(glm.getCalendario()).andReturn(cm);
        EasyMock.expect(cm.getHoraActual()).andReturn(10);
        ctrl.replay();
        result = glm.calculaConsumo(22);
        ctrl.verify();
        assertEquals(457.6, result);
    }

    @Test
    public void gestorLlamadasC2(){
        IMocksControl ctrl = EasyMock.createStrictControl();
        C cm = ctrl.mock(C.class);
        GestorLlamadas glm = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").mock(ctrl);
        EasyMock.expect(glm.getCalendario()).andReturn(cm);
        EasyMock.expect(cm.getHoraActual()).andReturn(21);
        ctrl.replay();
        result = glm.calculaConsumo(13);
        ctrl.verify();
        assertEquals(136.5, result);
    }
}
