package simulacion;

import java.io.Serializable;

public class Tecnico extends Thread implements Serializable {
    private String nombre;
    private ServicioTecnico servicioTecnico;
    private boolean activo = true;
    
    public Tecnico(String nombre, ServicioTecnico st){
        super(nombre);
	this.nombre = nombre;
        this.servicioTecnico = st;
        st.sumarTecnicoDisponible();
    }


    /**
     * Ejecuta el hilo del cliente.
     * El hilo se ejecuta en un bucle mientras esté activo.
     * En cada iteración, el cliente solicita un servicio técnico al servicio técnico y espera durante un tiempo determinado.
     * Si el hilo se desactiva, se interrumpe el bucle y se finaliza la ejecución del hilo.
     */
    public void run() {
        while(activo){ //basicamente chequear si se necesita un servicio tecnico
            servicioTecnico.brindarServicioTecnico(this);
            //System.out.println("tecnico esperando");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!activo)
        	break;
        }
    }
    
   
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }
}
