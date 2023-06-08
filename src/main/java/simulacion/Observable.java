package simulacion;

public class Observable {
    private Observer ojo = new ObserverSimulacion();

    protected void avisarObservador(){
        this.ojo.update();
    }
}
