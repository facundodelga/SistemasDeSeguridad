package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import contrataciones.Contratacion;
import contrataciones.iContratable;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioYaRegistradoException;
import persona.Persona;

public class Factura implements Cloneable{
	private static int ultFactura = 0;
	private int numFactura;
	private Persona persona;
	private ArrayList<Contratacion> contrataciones;
	private Pago pago;

	/**
	 * <b>PRE:</b> el parámetro persona debe ser distinto de null. El parámetro mpago debe ser distinto de null
	 * @param persona parámetro de tipo Persona, es el individuo que debe pagar la factura instanciada
	 * @param mpago parámetro de tipo MedioPago, es el medio de pago que se utilizará para pagar la factura instanciada
	 */
	public Factura(Persona persona) {
		super();
		this.numFactura = ultFactura++;
		this.persona = persona;
		this.contrataciones = new ArrayList<Contratacion>();

		this.pago = new Pago(totalOriginal());
	}

	public Factura(Persona persona,ArrayList<Contratacion> c) {
		super();
		this.numFactura = ultFactura++;
		this.persona = persona;
		this.contrataciones = c;
		this.pago = new Pago(totalOriginal());
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
		boolean existe = true;
		int i = 0;
		while(i < contrataciones.size() && !con.getDomicilio().equals(contrataciones.get(i).getDomicilio()) && !con.getDni().equals(contrataciones.get(i).getDni())) {
			i++;
		}
		if(i == contrataciones.size()){
			existe = false;
		}
		return existe;
	}
	
	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que inserta una contratacion nueva en la colección de contrataciones de la factura. Lanza excepción cuando la contratacion ya esta registrado. 
	 * @param con Parámetro de tipo Contratacion, es una nueva contratacion de la factura instanciada
	 * @throws ContratacionYaRegistradaException, DomicilioYaRegistradoException 
	 */
	public void agregarContratacion(Contratacion con) throws ContratacionYaRegistradaException, ContratacionYaRegistradaException, DomicilioYaRegistradoException {
		if(!this.existeContratacion(con)){
			this.contrataciones.add(con);
			this.persona.agregarDomicilio(con.getDomicilio());
		}else 
			throw new ContratacionYaRegistradaException(con,this.persona);
	}
	
	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que elimina una contratacion existente de la colección de contrataciones de la persona. OPCIONAL! Lanza excepción cuando la contratacion no se encuentra en la lista. 
	 * @param dom Parámetro de tipo Domicilio, es una contratacion que pertenecia a la factura, pero que se desea retirar
	 */
	public void eliminarContratacion(Contratacion con) {
		if(!this.existeContratacion(con))
			this.contrataciones.remove(con);
		//else exception ContratacionNoEncontradaException
	}

	/**
	 * Método que calcula el total a pagar por una persona física por la factura.
	 * @return total a pagar por la factura, siendo una persona de tipo física
	 */
	public double calcularBonificacionFisica() {
		double total = 0;
		for (Contratacion contratacion : contrataciones) {
			total += contratacion.getTarifa();

			Iterator<iContratable> it = contratacion.getIterator();
			while (it.hasNext()) 
				total += it.next().getTarifa();
		}
		return total;
	}
	
	/**
	 * Método que calcula el total a pagar por una persona jurídica por la factura.
	 * @return total a pagar por la factura, siendo una persona de tipo juridica
	 */
	public double calcularBonificacionJuridica() {
		double total = 0;
		int cont = 1;

		for (Contratacion contratacion : contrataciones) {
			double mod = 1;
			if (cont >= 3)
				mod = 0.5;

			total += contratacion.getTarifa();
			Iterator<iContratable> it = contratacion.getIterator();
			while (it.hasNext())
				total += it.next().getTarifa() * mod;
			cont++;
		}
		return total;
	}

	/**
	 * Método que calcula el total a pagar por la factura sin descuentos por métodos de pago.
	 * @return total a pagar por la factura
	 */	
	public double totalOriginal() {
		return persona.calcularBonificacion(this);
	}

	/**
	 * Método que calcula el total a pagar por la factura con descuentos por métodos de pago.
	 * @return total a pagar por la factura
	 */	
	public double totalModificadorMP(String metodo) {
		//MedioPago p = this.pago;
		this.pago=new Pago(totalOriginal());
		MedioPago p = this.pago;
		
		if(metodo.compareToIgnoreCase("CHEQUE") == 0)
			p = new Cheque(p);

		if(metodo.compareToIgnoreCase("EFECTIVO") == 0)
			p = new Efectivo(p);

		if(metodo.compareToIgnoreCase("TARJETA") == 0)
			p = new Tarjeta(p);
		
		
		return p.getValor();
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
			nObj.pago=(Pago)this.pago.clone();
			nObj.persona=(Persona)persona.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Factura, FALLO="+e.toString());
		}
	}

	public Pago getPago() {
		return pago;
	}

	
	public String detalleFactura() {
		return detalleFactura("");
	}
	
	public String detalle(String metodoPago) {
		String res = "N° Factura: " + numFactura + " | " + "Abonado: " + persona + " | Contrataciones:";

		for (Contratacion contratacion : contrataciones) {
			res += "\n\n" + contratacion.detalle();
		}
		
		double totOrig = totalOriginal();
		double totFinal;
		
		if (!metodoPago.isEmpty()) {
			totFinal = totalModificadorMP(metodoPago);
		}
		else {
			totFinal = totOrig;
		}
		
		res += "\n\nTotal Factura: $" + DoubleUtils.format(totOrig);
		
		if (totOrig != totFinal) {
			res += "\nTotal Factura Final (c/ metodo de pago " + metodoPago + " ): $" + DoubleUtils.format(totFinal);
		}
		return res;
	}
	
	
}
