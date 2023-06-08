package modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import contrataciones.Contratacion;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioYaRegistradoException;
import persona.Persona;
import utils.DoubleUtils;

public abstract class Factura implements MedioPago,Cloneable{
	private static int ultFactura = 0;
	private int numFactura;
	private Persona persona;
	protected ArrayList<Contratacion> contrataciones;
	private double totalOriginal;
	private double totalBonificado;
	private boolean pagoRealizado;
	private GregorianCalendar fecha;

	/**
	 * <b>PRE:</b> el parámetro persona debe ser distinto de null. El parámetro mpago debe ser distinto de null
	 * @param persona parámetro de tipo Persona, es el individuo que debe pagar la factura instanciada
	 * @param mpago parámetro de tipo MedioPago, es el medio de pago que se utilizará para pagar la factura instanciada
	 */
	public Factura(Persona persona) {
		super();
		assert persona != null : "El campo Persona debe estar instanciado";
		this.numFactura = ultFactura++;
		this.persona = persona;
		this.contrataciones = new ArrayList<Contratacion>();
		this.setPagoRealizado(false);
		this.fecha = (GregorianCalendar) GregorianCalendar.getInstance();
	}

	public Factura(Persona persona,ArrayList<Contratacion> c) {
		super();
		assert persona != null : "El campo Persona debe estar instanciado";
        assert c != null : "El campo ArrayList<Contratacion> debe estar instanciado";
		this.numFactura = ultFactura++;
		this.persona = persona;
		this.contrataciones = c;
		this.setPagoRealizado(false);
		this.fecha = (GregorianCalendar) GregorianCalendar.getInstance();
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
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que inserta una contratacion nueva en la colección de contrataciones de la factura. Lanza excepción cuando la contratacion ya esta registrado. 
	 * @param con Parámetro de tipo Contratacion, es una nueva contratacion de la factura instanciada
	 * @throws ContratacionYaRegistradaException, DomicilioYaRegistradoException 
	 */
	
	public void agregarContratacion(Contratacion con) throws ContratacionYaRegistradaException,ContratacionYaRegistradaException, DomicilioYaRegistradoException {
		assert con != null : "El campo Contratacion debe estar instanciado";
		if (!this.existeContratacion(con)) {

			boolean domicilioYaRegistrado = false;
			Iterator<Contratacion> it = this.contrataciones.iterator();

			while (it.hasNext() && !domicilioYaRegistrado) {
				Contratacion contratacion = it.next();
				if (contratacion.getDomicilio().equals(con.getDomicilio())) {
					domicilioYaRegistrado = true;
				}
			}	
			if (domicilioYaRegistrado) {
				throw new DomicilioYaRegistradoException(con.getDni(), con.getDomicilio());
			}
			this.contrataciones.add(con);
		} else
			throw new ContratacionYaRegistradaException(con, this.persona);
	}
	
	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que elimina una contratacion existente de la colección de contrataciones de la persona. OPCIONAL! Lanza excepción cuando la contratacion no se encuentra en la lista. 
	 * @param dom Parámetro de tipo Domicilio, es una contratacion que pertenecia a la factura, pero que se desea retirar
	 */
	public void eliminarContratacion(Contratacion con) {
		assert con != null : "El campo Contratacion debe estar instanciado";
		if(!this.existeContratacion(con))
			this.contrataciones.remove(con);
	}

	/**
	 * <b>PRE:</b> el parámetro debe ser distinto de null
	 * Método abstracto de permite calcular una bonificación sobre el importe de una factura dependiendo del tipo de persona
	 * @param <MetodoPago>
	 * @return total a pagar por una factura, dependiendo de el tipo de la instancia persona.
	 */
	public double calcularBonificacion(MedioPago mp) {
		this.totalBonificado= mp.calcularTotal();
		return this.totalBonificado;
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
	

	public double getTotalOriginal() {
		return totalOriginal;
	}

	public double getTotalBonificado() {
		return totalBonificado;
	}

	public void setTotalOriginal(double totalOriginal) {
		this.totalOriginal = totalOriginal;
	}

	public void setTotalBonificado(double totalBonificado) {
		this.totalBonificado = totalBonificado;
	}
		
	public String detalle() {
		return detalle(null);
	}

	public String descripcion() {
		return "Factura";
	}
	public String detalle(MedioPago mp) {
		String res = "N° Factura: " + numFactura + " | " + "Abonado: " + persona + " | Contrataciones:";

		for (Contratacion contratacion : contrataciones) {
			res += "\n\n" + contratacion.detalle();
		}
		
		this.calcularTotal();
		
		if (mp != null) {
			this.totalBonificado = this.calcularBonificacion(mp);
		}
		else {
			this.totalBonificado = this.totalOriginal;
		}
		
		res += "\n\nTotal Factura: $" + DoubleUtils.format(this.totalOriginal);
		
		if (this.totalBonificado != this.totalOriginal) {
			res += "\nTotal Factura Final (c/ metodo de pago " + mp.descripcion() + " ): $" + DoubleUtils.format(this.totalBonificado);
		}
		return res;
	}

	public boolean isPagoRealizado() {
		return pagoRealizado;
	}

	public void setPagoRealizado(boolean pagoRealizado) {
		this.pagoRealizado = pagoRealizado;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}	
}
