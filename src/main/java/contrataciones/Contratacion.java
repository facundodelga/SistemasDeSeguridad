package contrataciones;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.ContratableNoEncontradoException;
import persona.Domicilio;
import promociones.iPromocion;
import utils.DoubleUtils;

public class Contratacion implements Cloneable {
	private static int generadorId = 0;
	private int id;
	private String dni;
	private Domicilio domicilio;
	private iServicio servicio;
	private ArrayList<iContratable> contratados;
	private iPromocion promo;

	/**
	 * <b>PRE:</b> El parámetro id debe ser mayor a 0. El parámetro dni debe ser
	 * distinto de null y no vacio. El parámetro domicilio debe ser distinto de
	 * null. El parámetro servicio debe ser distinto de null.
	 * 
	 * @param dni       Parámetro de tipo String, es el dni de la persona que
	 *                  realizó la contratación
	 * @param domicilio Parámetro de tipo Domicilio, es el domicilio para el cual se
	 *                  contrató el servicio.
	 * @param servicio  Parámetro de tipo Servicio, es el tipo de servicio
	 *                  contratado
	 */
	public Contratacion(String dni, Domicilio domicilio, iServicio servicio, iPromocion promo) {
		super();
		this.id = generadorId++;
		this.dni = dni;
		this.domicilio = domicilio;
		this.servicio = servicio;
		this.promo = promo;
		this.contratados = new ArrayList<iContratable>();
	}

	public double getTarifa() {
		return this.getTarifa(this.promo);
	}

	public double getTarifa(iPromocion promo) {
		return this.servicio.getTarifa(promo);
	}

	// getters
	public int getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	/*
	 * como hacemos la validacion de un domicilio aca adentro si no tenemos al
	 * objeto persona? tenemos que buscarlo por dni en el arreglo desde el sistema o
	 * desde la factura?
	 * 
	 */
	public Domicilio getDomicilio() {
		return domicilio;
	}

	public iServicio getServicio() {
		return servicio;
	}

	public Iterator<iContratable> getIterator() {
		return this.contratados.iterator();
	}

	public int cantContratables() {
		return this.contratados.size();
	}

	public iContratable getContratable(int i) {
		return this.contratados.get(i);
	}

	public boolean existeContratable(iContratable cont) {
		return this.contratados.contains(cont);
	}

	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null. Método que inserta un
	 * nuevo elemento contratable en la colección de contratables. Lanza excepción
	 * cuando la contratacion ya esta registrada.
	 * 
	 * @param con Parámetro de tipo Contratable, es una nueva contratacion
	 */
	public void agregarContratable(iContratable cont) {
//		if(!this.existeContratable(cont)) //Deberia poder agregarse contratables aunque ya exista, seria agregar un contratable mas
		this.contratados.add(cont);
	}// creo que no debería lanzar excepciones, siempre deberia poder agregarse un
		// contratable

	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null. Método que elimina una
	 * contratacion existente de la colección de contrataciones. OPCIONAL! Lanza
	 * excepción cuando la contratacion no se encuentra en la lista.
	 * 
	 * @param dom Parámetro de tipo Contratable, es un contratable que pertenecia a
	 *            la contratación, pero que se desea retirar
	 * @throws ContratableNoEncontradoException
	 */
	public void eliminarContratable(iContratable cont) throws ContratableNoEncontradoException // throws
																								// ContratableNoEncontrado
	{
		if (this.existeContratable(cont))
			this.contratados.remove(cont);
		else
			throw new ContratableNoEncontradoException(this.id, cont);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			int i;
			Contratacion nObj = (Contratacion) super.clone();
			// nObj.contratados=(ArrayList<iContratable>)this.contratados.clone();
			for (i = 0; i < this.contratados.size(); i++) {
				nObj.contratados.add((iContratable) this.contratados.get(i).clone());
			}
			nObj.dni = this.dni;
			nObj.domicilio = (Domicilio) this.domicilio.clone();
			nObj.promo = this.promo;
			nObj.servicio = (iServicio) this.servicio.clone();
			return nObj;
		} catch (CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar , FALLO=" + e.toString());
		}
	}

	public String detalle() {
		String res = "* Domicilio: " + domicilio + " | " + "Servicio: " + servicio.descripcion();

		if (!promo.descripcion().isEmpty()) {
			res += " | Promo: " + promo.descripcion();
		}

		res += " | Precio Servicio: $" + DoubleUtils.format(servicio.getTarifa(promo));

		double precioTotal = servicio.getTarifa(promo);

		for (iContratable contratable : contratados) {
			precioTotal += contratable.getTarifa();
			res += "\n*** " + contratable.descripcion() + " | Precio base: $" + DoubleUtils.format(contratable.getTarifa());
		}

		res += "\n* Precio total: $" + DoubleUtils.format(precioTotal);

		return res;
	}
}
