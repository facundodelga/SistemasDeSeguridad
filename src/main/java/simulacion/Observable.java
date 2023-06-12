package simulacion;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Observable{
    protected ArrayList<Observer> ojos = new ArrayList<>();

    public void avisarObservador(Object arg) throws IllegalAccessException {
        for(Observer o : this.ojos){
            o.update(this , arg);
        }
    }
    public void agregarObservador(Observer observer){
        this.ojos.add(observer);
    }
    public void eliminarObservador(Observer observer){
        this.ojos.remove(observer);
    }


}
