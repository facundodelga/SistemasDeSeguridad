package modelo;

import java.util.ArrayList;

import excepciones.FacturaNoEncontradaException;
import persona.Persona;

public class ArregloFacturas extends ArrayList<Factura>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ArregloFacturas() {
		super();
    }
    
	/**
	 * Busca una factura en la lista dado un Id válido.
	 * @param id el Id de la factura que se busca.
	 * @return la factura correspondiente al Id buscado.
	 * @throws Exception si el Id buscado no existe.
	 */
    public Factura buscaPorId(int id)  throws FacturaNoEncontradaException{
		Factura f = null;
		for(int i = 0; i<this.size();i++) {
			if(id == this.get(i).getNumFactura());
			f = this.get(i);
		}
		if(f == null)
			throw new FacturaNoEncontradaException(id);
		
		return f;
    }
    

	/**
	 * Borra una factura de la lista dandole un Id valido.
	 * 
	 * @param id El id de la factura a borrar.
	 * @throws Exception si el id que se proporciona no existe en la lista.
	 */
    public void borraPorId(int id)  throws FacturaNoEncontradaException{
		int i =0;
	
		while(i < this.size() && id == this.get(i).getNumFactura()) {
			i++;
		}
		
		if( id == this.get(i).getNumFactura()) {
			this.remove(i);
		}else {
			throw new FacturaNoEncontradaException(id);
		}
	
    }
    
    /**
	 * Devuelve Facturas filtrando por Persona.
	 * @param f2 la Persona a filtrar.
	 * @return el ArrayList de Facturas correspondiente a la Persona buscada.
     * @throws FacturaNoEncontradaException 
	 * @throws Exception si el DNI buscado no existe.
	 */
    public Factura buscaPorPersona(Persona p) throws FacturaNoEncontradaException{
	
		Factura f = null;
		
		for(int i = 0; i<this.size();i++) {
			if(f.getPersona().getDni().equals(this.get(i).getPersona().getDni())) ;
				f = this.get(i);
		}
		
		if(f == null)
			throw new FacturaNoEncontradaException();
		
		return f;
    }
    
}
