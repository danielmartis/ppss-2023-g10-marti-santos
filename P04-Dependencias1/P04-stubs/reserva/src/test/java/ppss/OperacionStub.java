package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class OperacionStub implements IOperacionBO{

    private boolean valida = true;

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException,
                                                                   JDBCException,
                                                                   SocioInvalidoException
    {


        if(!socio.equals("Luis")){
            throw new SocioInvalidoException("SOCIO invalido");
        }
        if(valida == true){
            if(isbn.equals("33333") || isbn.equals("44444")){
                throw new ppss.excepciones.IsbnInvalidoException("ISBN invalido:" + isbn);
            }
        }
        else{
            throw new ppss.excepciones.JDBCException("CONEXCION invalida; ");
        }
    }

    public void setValida(boolean v){
        valida = v;
    }

}
