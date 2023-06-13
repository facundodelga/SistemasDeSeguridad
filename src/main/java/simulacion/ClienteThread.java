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
    
    /**
     * Implementación del método run de la interfaz Runnable.
     * Este método se ejecuta cuando se inicia el hilo de la persona jurídica.
     * En este método, la persona jurídica se mantiene activa y realiza repetidamente las siguientes acciones:
     * 1. Pide un técnico al servicio técnico.
     * 2. Duerme durante 10 segundos.
     * 3. Verifica si sigue activa. Si no lo está, finaliza el ciclo y termina la ejecución.
     *
     * Si ocurre una excepción de tipo IllegalAccessException al pedir el técnico, se imprime la traza de la excepción.
     * Si ocurre una excepción de tipo InterruptedException al dormir el hilo, se imprime la traza de la excepción.
     */
    @Override
    public void run() {
	this.activo = true;
        while(activo) {
            try {
		servicioTecnico.pedirTecnico(this);
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    }
            try {
		Thread.sleep(10000);
	    } catch (InterruptedException e) {
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