package persistencia;

import java.util.ArrayList;

public class ServicioTecnicoDTO {
    private int tecnicosDisponibles;
    private ArrayList<String> pedidos;

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
