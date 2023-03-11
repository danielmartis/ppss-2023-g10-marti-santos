package ppss;

import org.junit.jupiter.api.Test;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {
    FicheroTexto ft = new FicheroTexto();

    @Test
    public void ficheroTextoC1(){
        String fichero = "src/test/resources/ficheroC1.txt";
        IMocksControl ctrl = EasyMock.createStrictControl();
        FileReader frm = ctrl.mock(FileReader.class);
        FicheroTexto ftm = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("setFilereader").mock(ctrl);
        assertDoesNotThrow(() -> EasyMock.expect(ftm.setFilereader(fichero)).andStubReturn(frm));
        assertDoesNotThrow(() -> {EasyMock.expect(frm.read()).andReturn(97);
                                  EasyMock.expect(frm.read()).andReturn(98);
                                  EasyMock.expect(frm.read()).andThrow(new IOException("src/test/resources/ficheroC1.txt (Error al leer el archivo"));});

        ctrl.replay();
        FicheroException fe = assertThrows(FicheroException.class,() -> ftm.contarCaracteres("src/test/resources/ficheroC1.txt"));
        //System.out.println(fe.getMessage());
        assertEquals("src/test/resources/ficheroC1.txt (Error al leer el archivo)", fe.getMessage());
        ctrl.verify();
    }

    @Test
    public void ficheroTextoC2(){
        String fichero = "src/test/resources/ficheroC2.txt";
        IMocksControl ctrl = EasyMock.createStrictControl();
        FileReader frm = ctrl.mock(FileReader.class);
        FicheroTexto ftm = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("setFilereader").mock(ctrl);
        assertDoesNotThrow(() -> EasyMock.expect(ftm.setFilereader(fichero)).andStubReturn(frm));
        assertDoesNotThrow(() -> {EasyMock.expect(frm.read()).andReturn(97);
            EasyMock.expect(frm.read()).andReturn(98);
            EasyMock.expect(frm.read()).andReturn(99);
            EasyMock.expect(frm.read()).andReturn(-1);});
        assertDoesNotThrow(()-> {frm.close();
                                    EasyMock.expectLastCall().andThrow(new IOException());});


        ctrl.replay();
        FicheroException fe = assertThrows(FicheroException.class,() -> ftm.contarCaracteres("src/test/resources/ficheroC2.txt"));
        //System.out.println(fe.getMessage());
        assertEquals("src/test/resources/ficheroC2.txt (Error al cerrar el archivo)", fe.getMessage());
        ctrl.verify();

    }
}
