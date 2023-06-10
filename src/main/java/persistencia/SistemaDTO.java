package persistencia;


import java.io.Serializable;
import java.util.ArrayList;

public class SistemaDTO implements Serializable {
    private int generadorId;
    private int ultimaFactura;
    private static SistemaDTO instancia;
    private ArrayList<FacturaFisicaDTO> facturas;
    private ArrayList<PersonaFisicaDTO> personas;
    private ArrayList<TecnicoDTO> tecnicos;
    private ServicioTecnicoDTO servicioTecnico;

    public int getGeneradorId() {
        return generadorId;
    }

    public void setGeneradorId(int generadorId) {
        this.generadorId = generadorId;
    }

    public int getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(int ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public static SistemaDTO getInstancia() {
        return instancia;
    }

    public static void setInstancia(SistemaDTO instancia) {
        SistemaDTO.instancia = instancia;
    }

    public ArrayList<FacturaFisicaDTO> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<FacturaFisicaDTO> facturas) {
        this.facturas = facturas;
    }

    public ArrayList<PersonaFisicaDTO> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<PersonaFisicaDTO> personas) {
        this.personas = personas;
    }

    public ArrayList<TecnicoDTO> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<TecnicoDTO> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public ServicioTecnicoDTO getServicioTecnico() {
        return servicioTecnico;
    }

    public void setServicioTecnico(ServicioTecnicoDTO servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
    }
}
