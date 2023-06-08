package simulacion;

public class ServicioTecnico{
    //ServicioTecnico es el recurso compartido
    private int tecnicosDisponibles = 0;
    private int pedidosTecnico = 0;
    public synchronized void pedirTecnico(ClienteThread c){
        while(tecnicosDisponibles == 0){
            try {
                c.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        c.avisarObservador();
        this.pedidosTecnico++;
        this.tecnicosDisponibles--;
        notifyAll();
    }

    public synchronized void brindarServicioTecnico(Tecnico t){
        while(pedidosTecnico == 0){
            try {
                t.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.pedidosTecnico--;
        t.avisarObservador();
        this.tecnicosDisponibles++;
        notifyAll();
    }

    public void sumarTecnicoDisponible(){
        this.tecnicosDisponibles++;
    }

    public int getTecnicosDisponibles() {
        return tecnicosDisponibles;
    }

    public void setTecnicosDisponibles(int tecnicosDisponibles) {
        this.tecnicosDisponibles = tecnicosDisponibles;
    }

    public int getPedidosTecnico() {
        return pedidosTecnico;
    }

    public void setPedidosTecnico(int pedidosTecnico) {
        this.pedidosTecnico = pedidosTecnico;
    }
}
