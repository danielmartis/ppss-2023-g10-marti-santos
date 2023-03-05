package ppss;

import ppss.IOperacionBO;
import ppss.Operacion;

public class Factoria {
    private static IOperacionBO op = null;
    public static IOperacionBO create(){
        if (op != null){
            return op;
        }
        else{
            return new Operacion();
        }
    }

    static void setServicio(IOperacionBO io){
        op = io;
    }
}
