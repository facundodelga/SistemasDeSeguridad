package simulacion;

import java.util.ArrayList;

public class ServicioTecnico extends Observable {
    //ServicioTecnico es el recurso compartido se podria hacer Singleton xq solo se va a instanciar 1 vez

    private int tecnicosDisponibles = 0;
    private ArrayList<String> pedidos = new ArrayList<>();

    public synchronized void pedirTecnico(ClienteThread c){

        while(this.tecnicosDisponibles == 0){
            try {
                c.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pedidos.add(c.getNombre());

        try {
            this.avisarObservador(c.getNombre() + " ha pedido un Tecnico");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        this.tecnicosDisponibles--;

        notifyAll();
    }

    public synchronized void brindarServicioTecnico(Tecnico t){

        while(this.pedidos.isEmpty()){
            try {
                t.wait();
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
        notifyAll();
    }

    public int getTecnicosDisponibles() {
        return tecnicosDisponibles;
    }

    public void setTecnicosDisponibles(int tecnicosDisponibles) {
        this.tecnicosDisponibles = tecnicosDisponibles;
    }


}
