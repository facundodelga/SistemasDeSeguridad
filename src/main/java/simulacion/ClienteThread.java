package simulacion;

import java.util.Random;

public class ClienteThread implements Runnable{

    private String nombre;
    private ServicioTecnico servicioTecnico;

    public ClienteThread(String nombre,ServicioTecnico st){
        this.nombre = nombre;
        this.servicioTecnico = st;

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        Random r = new Random();
        int condicion = 1;
        while(condicion != 0) {
            condicion = r.nextInt()/10;
        }
        servicioTecnico.pedirTecnico(this);
    }
}
