package persistencia;

import java.util.ArrayList;

public class ContratacionDTO {
    private int id;
    private String dni;
    private String domicilio;
    private String servicio;
    private ArrayList<ContratableDTO> contratados;
    private String promo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public ArrayList<ContratableDTO> getContratados() {
        return contratados;
    }

    public void setContratados(ArrayList<ContratableDTO> contratados) {
        this.contratados = contratados;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
}
