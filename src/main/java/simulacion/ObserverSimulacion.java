package simulacion;

public class ObserverSimulacion implements Observer{
    private ServicioTecnico st;

    public ObserverSimulacion(ServicioTecnico st) {
        this.st = st;
        this.st.agregarObservador(this);
    }
    /**
     * Actualiza el observador cuando hay cambios en el Observable.
     *
     * @param observable el Observable que notificó el cambio.
     * @param mensaje    el mensaje asociado al cambio.
     * @throws IllegalAccessException si el Observable no es el esperado.
     */
    @Override
    public void update(Observable observable, Object mensaje) throws IllegalAccessException {
        if(observable != this.st){
            throw new IllegalAccessException();
        }
    }

}
