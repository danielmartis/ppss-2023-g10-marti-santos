package ppss.ejercicio1;
public class GestorLlamadaStub extends ppss.ejercicio1.GestorLlamadas {
    public int hora;

    public void setHora(int h){
        hora = h;
    }
    @Override
    public int getHoraActual(){
        return hora;
    }
}
