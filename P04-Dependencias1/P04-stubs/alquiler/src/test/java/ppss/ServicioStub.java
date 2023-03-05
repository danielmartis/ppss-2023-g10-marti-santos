package ppss;

public class ServicioStub implements IService{
    @Override
    public float consultaPrecio(TipoCoche tc) {
        return 10;
    }
}
