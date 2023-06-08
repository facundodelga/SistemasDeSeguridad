package simulacion;

public class Tecnico implements Runnable {
    private String nombre;
    private ServicioTecnico servicioTecnico;
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
        }
    }

    public String getNombre() {
        return nombre;
    }
}
