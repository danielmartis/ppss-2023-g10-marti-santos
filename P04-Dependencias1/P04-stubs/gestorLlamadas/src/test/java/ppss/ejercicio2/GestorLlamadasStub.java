package ppss.ejercicio2;

public class GestorLlamadasStub extends GestorLlamadas{
    CalendarioStub c;
    public void setHora(int h){
        c.setHora(h);
    }

    public void setCalendario(CalendarioStub ca){
        c = ca;
    }
    @Override
    public Calendario getCalendario(){
        /*c = new CalendarioStub();
        return c;*/
        return c;
    }
}
