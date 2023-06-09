package simulacion;

public class ObserverSimulacion implements Observer{
    private ServicioTecnico st;

    public ObserverSimulacion(ServicioTecnico st) {
        this.st = st;
        this.st.agregarObservador(this);
    }

    @Override
    public void update(Observable observable, Object mensaje) throws IllegalAccessException {
        if(observable != this.st){
            throw new IllegalAccessException();
        }
        //enviar mensaje al log
    }
}
