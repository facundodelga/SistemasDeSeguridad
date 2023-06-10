package persistencia;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonaFisicaDTO implements Serializable {
    private String nombre;
    private String dni;
    private ArrayList<String> domicilios;
    private ArrayList<ContratacionDTO> contratacion;
    private String estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ArrayList<String> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(ArrayList<String> domicilios) {
        this.domicilios = domicilios;
    }

    public ArrayList<ContratacionDTO> getContratacion() {
        return contratacion;
    }

    public void setContratacion(ArrayList<ContratacionDTO> contratacion) {
        this.contratacion = contratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
