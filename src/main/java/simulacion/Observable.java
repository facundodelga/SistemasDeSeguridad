package simulacion;

public class Observable {
    protected Observer ojo = new ObserverSimulacion();

    protected void avisarObservador(String mensaje){
        this.ojo.update(mensaje);
    }
}
