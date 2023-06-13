package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import contrataciones.Contratacion;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioYaRegistradoException;
import persona.Persona;
import utils.DoubleUtils;

public abstract class Factura implements MedioPago,Cloneable, Serializable {
	private static int ultFactura = 0;
	private int numFactura;
	private Persona persona;
	protected ArrayList<Contratacion> contrataciones;
	private double totalOriginal;
	private double totalBonificado;
	private boolean pagoRealizado;
	private int mes;

	/**
	 * <b>PRE:</b> el parámetro persona debe ser distinto de null. El parámetro mpago debe ser distinto de null
	 * @param persona parámetro de tipo Persona, es el individuo que debe pagar la factura instanciada
	 * @param mpago parámetro de tipo MedioPago, es el medio de pago que se utilizará para pagar la factura instanciada
	 */
	public Factura(Persona persona, int mes) {
		super();
		assert persona != null : "El campo Persona debe estar instanciado";
		this.numFactura = ultFactura++;
		this.persona = persona;
		this.contrataciones = persona.getContrataciones();
		this.setPagoRealizado(false);
		this.mes = mes;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public Persona getPersona() {
		return persona;
	}

	public ArrayList<Contratacion> getContrataciones() {
		return contrataciones;
	}
	
	/**
	 * Verifica si existe una contratación específica en la lista de contrataciones.
	 *
	 * @param con la contratación que se desea buscar.
	 * @return true si la contratación existe en la lista, false en caso contrario.
	 */
	public boolean existeContratacion(Contratacion con) {
		assert con != null : "El campo Contratacion debe estar instanciado";
		return contrataciones.contains(con);
	}

	/**
	 * <b>PRE:</b> el parámetro debe ser distinto de null
	 * Método abstracto de permite calcular una bonificación sobre el importe de una factura dependiendo del tipo de persona
	 * @param <MetodoPago>
	 * @return total a pagar por una factura, dependiendo de el tipo de la instancia persona.
	 */
	public double calcularBonificacion(MedioPago mp) {
		//this.totalBonificado= mp.calcularTotal();
		//return this.totalBonificado;
		return mp.calcularTotal();
	}

	/**
	 * Método que calcula el total a pagar por la factura sin descuentos por métodos de pago.
	 * @return total a pagar por la factura
	 */	
/*	public double totalOriginal() {
		this.totalOriginal=this.calcularTotal();
		return this.totalOriginal;
	}*/

	public abstract double calcularTotal();
	
	public void pagarFactura(MedioPago medio) {
		persona.pagarFactura(this, medio);
	}
	/**
	 * Crea y devuelve una copia profunda de esta instancia de Factura.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de Factura no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		int i;
		try {
			Factura nObj=(Factura)super.clone();
			for(i=0;i<this.contrataciones.size();i++) {
				nObj.contrataciones.add( (Contratacion) this.contrataciones.get(i).clone());
			}
			//nObj.pago=(Pago)this.pago.clone();
			nObj.persona=(Persona)persona.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Factura, FALLO="+e.toString());
		}
	}	
		
	public String detalle() {
		return detalle("");
	}

	public String descripcion() {
		return "Factura";
	}
	public String detalle(String medio) {
		MedioPago mp = Sistema.getInstancia().getMedioPago(medio, this);
		
		String res = "N° Factura: " + numFactura + " | " + "Abonado: " + persona + " | Estado de pago: ";
		if(this.pagoRealizado)
			res+="realizado";
		else
			res+="por pagar";
			
		res+=" | Contrataciones:";
		
		for (Contratacion contratacion : contrataciones) {
			res += "\n\n" + contratacion.detalle();
		}
		
		if (mp != null) {
			this.totalBonificado = this.calcularBonificacion(mp);
		}
		else {
			this.totalBonificado = this.totalOriginal;
		}
		if(this.isPagoRealizado())
			res += "\n\nTotal Factura: $" + DoubleUtils.format(this.totalOriginal);
		
		if (this.totalBonificado != this.totalOriginal) {
			res += "\nTotal Factura Final (c/ metodo de pago " + mp.descripcion() + " ): $" + DoubleUtils.format(this.totalBonificado);
		}
		
		return res;
	}


	public double getTotalOriginal() {
		return totalOriginal;
	}

	public double getTotalBonificado() {
		return totalBonificado;
	}

	public boolean isPagoRealizado() {
		return pagoRealizado;
	}

	public int getMes() {
		return mes;
	}

	public static void setUltFactura(int ultFactura) {
		Factura.ultFactura = ultFactura;
	}

	public void setNumFactura(int numFactura) {
		this.numFactura = numFactura;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setContrataciones(ArrayList<Contratacion> contrataciones) {
		this.contrataciones = contrataciones;
	}

	public void setTotalOriginal(double totalOriginal) {
		this.totalOriginal = totalOriginal;
	}

	public void setTotalBonificado(double totalBonificado) {
		this.totalBonificado = totalBonificado;
	}

	public void setPagoRealizado(boolean pagoRealizado) {
		this.pagoRealizado = pagoRealizado;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public static int getUltFactura() {
		return ultFactura;
	}
}
