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

    public synchronized void pedirTecnico(ClienteThread c){

        while(this.tecnicosDisponibles == 0){
            try {
        	System.out.println("thread cliente esperando");
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
