package ppss;

import java.time.LocalDate;
import java.time.Month;
public class CalendarioStub extends Calendario{
    boolean es;

    public void setFestivo(boolean e){
        es = e;
    }
    @Override
    public boolean es_festivo(LocalDate d) throws CalendarioException {
        if(d.getMonth() == Month.JUNE && (d.getDayOfMonth() == 20 || d.getDayOfMonth() == 24)){
            return true;
        }
        else if(d.getMonth() == Month.APRIL && (d.getDayOfMonth() == 18 || d.getDayOfMonth() == 21 || d.getDayOfMonth() == 22)){
            throw new CalendarioException("Error en dia: " + d.toString() + ";");
        }
        return es;
    }
}
