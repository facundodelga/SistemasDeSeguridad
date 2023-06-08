package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import persona.Persona;

public class FacturaFisica extends Factura {

	public FacturaFisica(Persona persona) {
		super(persona);
	}

	public FacturaFisica(Persona persona, ArrayList<Contratacion> c) {
		super(persona, c);
	}
	/**
	 * Método que calcula el total a pagar por una persona física por la factura.
	 * @return total a pagar por la factura, siendo una persona de tipo física
	 */

	@Override
	public double calcularTotal() {
		double total = 0;
		for (Contratacion contratacion : contrataciones) {
			total += contratacion.getTarifa();

			Iterator<iContratable> it = contratacion.getIterator();
			while (it.hasNext()) 
				total += it.next().getTarifa();
		}
		this.setTotalOriginal(total);
		return total;
	}

	@Override
	public String descripcion() {
		return "Factura Juridica";
	}

}
