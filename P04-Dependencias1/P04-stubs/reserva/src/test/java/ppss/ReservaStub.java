package ppss;

public class ReservaStub extends Reserva{
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        if(login.equals("xxxx") && password.equals("xxxx")){
            return false;
        }
        else{
            return true;
        }
    }
}
