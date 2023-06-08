package simulacion;

public class ClienteThread extends Observable implements Runnable{

    private String nombre;

    public ClienteThread(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {

    }
}
