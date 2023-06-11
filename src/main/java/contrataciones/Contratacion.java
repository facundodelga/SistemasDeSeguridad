package contrataciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import excepciones.ContratableNoEncontradoException;
import persona.Domicilio;
import promociones.iPromocion;
import utils.DoubleUtils;

public class Contratacion implements Serializable,Cloneable {
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
		assert dni != null && !dni.isBlank(): "El campo DNI no puede estar vacio";
		assert domicilio != null : "El campo Domicilio debe estar instanciado";
		assert servicio != null : "El campo Servicio debe estar instanciado";
		assert promo != null : "El campo IPromocion debe estar instanciado";

		this.id = generadorId++;
		this.dni = dni;
		this.domicilio = domicilio;
		this.servicio = servicio;
		this.promo = promo;
		this.contratados = new ArrayList<iContratable>();
	}

	public Contratacion(int id, String dni, Domicilio domicilio, iServicio servicio, ArrayList<iContratable> contratados, iPromocion promo) {
		this.id = id;
		this.dni = dni;
		this.domicilio = domicilio;
		this.servicio = servicio;
		this.contratados = contratados;
		this.promo = promo;
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
		assert cont != null : "El campo IContratable debe estar instanciado";
		this.contratados.add(cont);
	}

	// CONTRATABLE

	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null. Método que elimina una
	 * contratacion existente de la colección de contrataciones. OPCIONAL! Lanza
	 * excepción cuando la contratacion no se encuentra en la lista.
	 * 
	 * @param dom Parámetro de tipo Contratable, es un contratable que pertenecia a
	 *            la contratación, pero que se desea retirar
	 * @throws ContratableNoEncontradoException
	 */
	public void eliminarContratable(iContratable cont) throws ContratableNoEncontradoException{
		assert cont != null : "El campo IContratable debe estar instanciado";
		
		if (this.existeContratable(cont))
			this.contratados.remove(cont);
		else
			throw new ContratableNoEncontradoException(this.id, cont);
	}
	
	/**
	 * Crea y devuelve una copia profunda de esta instancia de Contratacion.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de Contratacion no es clonable.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			int i;
			Contratacion nObj = (Contratacion) super.clone();
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
	

	/**
	 * Devuelve una cadena de texto con el detalle de la contratación.
	 * @return una cadena de texto con el detalle de la contratación.
	 */
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contratacion other = (Contratacion) obj;
		return id == other.id;
	}
	

	
}
