package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionStub implements IOperacionBO{
    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException,
                                                                   JDBCException,
                                                                   SocioInvalidoException
    {

    }
}
