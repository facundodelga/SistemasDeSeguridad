package simulacion;

import java.io.Serializable;

public class ClienteThread extends Thread implements Serializable{

    private String nombre;
    private boolean activo;
    private ServicioTecnico servicioTecnico;

    public ClienteThread(String nombre,ServicioTecnico st){
        super(nombre);
	this.nombre = nombre;
        this.servicioTecnico = st;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
	this.activo = true;
        while(activo) {
            try {
		servicioTecnico.pedirTecnico(this);
	    } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
            try {
		Thread.sleep(10000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
            if(!activo)
        	break;
        }
        
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}