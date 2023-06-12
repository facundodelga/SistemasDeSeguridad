package simulacion;

import java.io.Serializable;

public class Tecnico extends Thread implements Serializable {
    private String nombre;
    private ServicioTecnico servicioTecnico;
    private boolean rompe = true;
    
    public Tecnico(String nombre, ServicioTecnico st){
        this.nombre = nombre;
        this.servicioTecnico = st;
        st.sumarTecnicoDisponible();
    }

    /**
     *
     */
    @Override
    public void run() {
        while(true){ //basicamente chequear si se necesita un servicio tecnico
            servicioTecnico.brindarServicioTecnico(this);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!rompe)
        	break;
        }
    }
    
    public void rompe() {
	rompe = false;
    }
    
    public String getNombre() {
        return nombre;
    }
}
