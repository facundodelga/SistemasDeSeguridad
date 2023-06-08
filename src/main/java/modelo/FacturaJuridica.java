package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import persona.Persona;

public class FacturaJuridica extends Factura {

	public FacturaJuridica(Persona persona, ArrayList<Contratacion> c) {
		super(persona, c);
	}

	public FacturaJuridica(Persona persona) {
		super(persona);
	}

	/**
	 * Método que calcula el total a pagar por una persona jurídica por la factura.
	 * @return total a pagar por la factura, siendo una persona de tipo juridica
	 */
	@Override
	public double calcularTotal() {
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
		this.setTotalOriginal(total);
		return total;
	
	}
	
	@Override
	public String descripcion() {
		return "Factura Juridica";
	}

}
