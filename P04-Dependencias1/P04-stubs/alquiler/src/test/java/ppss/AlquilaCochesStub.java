package ppss;

public class AlquilaCochesStub extends AlquilaCoches{
    @Override
    public Calendario getCalendario(){
        calendario = new CalendarioStub();
        return calendario;
    }
    @Override
    public IService getServicio(){
        return new ServicioStub();
    }
}
