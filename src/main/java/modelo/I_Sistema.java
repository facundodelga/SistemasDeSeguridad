package modelo;

import java.util.ArrayList;

import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import persona.Persona;

public interface I_Sistema {
	//siguinete mes
	public void adelantarMes();
	
	//Busca persona por dni
	public ArrayList<Factura> buscarFacturaPorPersonaDNI(String dni) throws PersonaNoEncontradaException, FacturaNoEncontradaException;
	//
	
}
