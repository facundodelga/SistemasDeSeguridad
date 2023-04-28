package modelo;

import java.util.ArrayList;

import contrataciones.Contratacion;
import persona.Persona;

public class Sistema {
	private static Sistema instancia  = null;
	private ArrayListFacturas facturas;

	
	private Sistema() {
		super();
		this.facturas=new ArrayListFacturas();
	}
	
	public static Sistema getInstancia(){
		if(instancia==null){
			instancia = new Sistema();
		}
		return instancia;
	}

	public int creaFactura(Persona p){
		Factura f = new Factura(p);
		facturas.add(f);

		return f.getNumFactura();
	}

	public void creaFactura(Persona p,ArrayList<Contratacion> c){
		Factura f = new Factura(p,c);
		facturas.add(f);
	}

	public void agregarContratacion(int id,Contratacion contratacion) throws Exception {
	    Factura f;
	    try{
			f = this.facturas.buscaPorId(id);
			f.agregarContratacion(contratacion);
	    }catch(Exception e) {
			throw e;
	    }
	    
	}
	
	public void eliminarFactura(int id) throws Exception {
		try{
			this.facturas.borraPorId(id);
		} catch (Exception e) {
			throw e;
		}

	}

	public double pagarFactura(int id,String mp) throws Exception {
	    Factura f;
		double total;
	    try{
			f = this.facturas.buscaPorId(id);
			total = f.totalModificadorMP(mp);
			
	    }catch(Exception e) {
			throw e;
	    }

		return total;
	}
	
	// public void AgregaFactura(Factura f) {
	// 	if(existePersona(f))
	// 		facturas.add(f);
	// }



	// private boolean existePersona(Factura f) {
	// 	//this.facturas.buscaPorPersona(f);

	// 	return false;
	// }
}
