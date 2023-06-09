package persistencia;

import modelo.ArregloFacturas;
import modelo.ArregloPersonas;
import modelo.Sistema;
import simulacion.ClienteThread;
import simulacion.ServicioTecnico;
import simulacion.Tecnico;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaDTO implements Serializable {
    /**
     * 
     */

    private int ultimaFactura;
    private int ultimaContratacion;
    private ArregloFacturas facturas;
    private ArregloPersonas personas;
    private int mes;
    private ArrayList<Tecnico> tecnicos;
    private ServicioTecnico servicioTecnico;
    private ArrayList<ClienteThread> clientesHilo;

    public int getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(int ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }
    
    
    /**
	 * @return the ultimaContratacion
	 */
	public int getUltimaContratacion() {
		return ultimaContratacion;
	}

	/**
	 * @param ultimaContratacion the ultimaContratacion to set
	 */
	public void setUltimaContratacion(int ultimaContratacion) {
		this.ultimaContratacion = ultimaContratacion;
	}

	public ArregloFacturas getFacturas() {
        return facturas;
    }

    public void setFacturas(ArregloFacturas facturas) {
        this.facturas = facturas;
    }

    public ArregloPersonas getPersonas() {
        return personas;
    }

    public void setPersonas(ArregloPersonas personas) {
        this.personas = personas;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public ArrayList<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public ServicioTecnico getServicioTecnico() {
        return servicioTecnico;
    }

    public void setServicioTecnico(ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
    }

    public ArrayList<ClienteThread> getClientesHilo() {
	return clientesHilo;
    }

    public void setClientesHilo(ArrayList<ClienteThread> clientesHilo) {
	this.clientesHilo = clientesHilo;
    }
}
