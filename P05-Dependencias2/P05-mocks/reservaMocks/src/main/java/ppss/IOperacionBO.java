package ppss;

public interface IOperacionBO {
    public void operacionReserva(String s, String i) throws IsbnInvalidoException, JDBCException, SocioInvalidoException;
}
