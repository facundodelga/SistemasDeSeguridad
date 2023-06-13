package simulacion;

import java.io.Serializable;
import java.util.ArrayList;

public class ServicioTecnico extends Observable implements Serializable {
    //ServicioTecnico es el recurso compartido se podria hacer Singleton xq solo se va a instanciar 1 vez

    /**
     * 
     */

    private int tecnicosDisponibles = 0;
    private ArrayList<String> pedidos = new ArrayList<>();

    public ServicioTecnico() {
    }

    public ServicioTecnico(int tecnicosDisponibles, ArrayList<String> pedidos) {
        this.tecnicosDisponibles = tecnicosDisponibles;
        this.pedidos = pedidos;
    }
    
    /**
     * Solicita un técnico para recibir servicio técnico.
     *
     * @param c el hilo del cliente que realiza la solicitud.
     * @throws IllegalAccessException si se produce un error al notificar al observador.
     */
    public synchronized void pedirTecnico(ClienteThread c) throws IllegalAccessException{

        while(this.tecnicosDisponibles == 0){
            try {
        	this.avisarObservador( " **  " + c.getNombre() + " Está esperando para recibir Servicio Tecnico **");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pedidos.add(c.getNombre());

        try {
            this.avisarObservador(c.getNombre() + " ha pedido un Tecnico");
            c.setActivo(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        this.tecnicosDisponibles--;

        notifyAll();
    }

    /**
     * Brinda servicio técnico a un técnico.
     *
     * @param t el técnico al que se le brinda el servicio técnico.
     * @throws IllegalAccessException si se produce un error al notificar al observador.
     */
    public synchronized void brindarServicioTecnico(Tecnico t){

        while(this.pedidos.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            this.avisarObservador(t.getNombre() + " se encargó del pedido de " + pedidos.get(pedidos.size() - 1));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        pedidos.remove(pedidos.size() - 1);

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

    public ArrayList<String> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<String> pedidos) {
        this.pedidos = pedidos;
    }


} 
