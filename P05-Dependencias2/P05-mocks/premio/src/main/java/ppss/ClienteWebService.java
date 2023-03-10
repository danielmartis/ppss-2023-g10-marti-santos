package ppss;

public class ClienteWebService {
    boolean can = true;
    public String obtenerPremio() throws ClienteWebServiceException{
        if(can){
            return "";
        }
        else {
            throw new ClienteWebServiceException("Not yet implemented");
        }
    }
}
