package persistencia;

import java.io.Serializable;
import java.util.ArrayList;


public class FacturaFisicaDTO implements Serializable {
    private int numFactura;
    private PersonaFisicaDTO persona;
    private ArrayList<ContratacionDTO> contrataciones;
    private int mes;

    public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public PersonaFisicaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaFisicaDTO persona) {
        this.persona = persona;
    }

    public ArrayList<ContratacionDTO> getContrataciones() {
        return contrataciones;
    }

    public void setContrataciones(ArrayList<ContratacionDTO> contrataciones) {
        this.contrataciones = contrataciones;
    }

}
