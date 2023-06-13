package simulacion;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Observable{
    protected ArrayList<Observer> ojos = new ArrayList<>();

    /**
     * Avisa a los observadores registrados enviándoles una actualización con el argumento especificado.
     *
     * @param arg el argumento a enviar a los observadores.
     * @throws IllegalAccessException si ocurre una excepción al acceder a los observadores.
     */
    public void avisarObservador(Object arg) throws IllegalAccessException {
        for(Observer o : this.ojos){
            o.update(this , arg);
        }
    }
    
    /**
     * Agrega un observador a la lista de observadores.
     *
     * @param observer el observador a agregar.
     */
    public void agregarObservador(Observer observer){
        this.ojos.add(observer);
    }
    
    /**
     * Elimina un observador de la lista de observadores.
     *
     * @param observer el observador a eliminar.
     */
    public void eliminarObservador(Observer observer){
        this.ojos.remove(observer);
    }


}
