package ppss.ejercicio2;

public class CalendarioStub extends Calendario{
    int hora;
    public void setHora(int h){
        hora = h;
    }

    @Override
    public int getHoraActual() {
        return hora;
    }
}
