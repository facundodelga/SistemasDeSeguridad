package modelo;

import java.util.ArrayList;

import excepciones.FacturaNoEncontradaException;
import persona.Persona;

public class ArregloFacturas extends ArrayList<Factura>{
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
			if(id == this.get(i).getNumFactura())
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
	 * @param p la Persona a filtrar.
	 * @return el ArrayList de Facturas correspondiente a la Persona buscada.
     * @throws FacturaNoEncontradaException 
	 * @throws Exception si el DNI buscado no existe.
	 */
    public ArrayList<Factura> buscaPorPersona(Persona p) throws FacturaNoEncontradaException{
		Factura f = null;
		ArrayList<Factura>facs=new ArrayList<Factura>();
		
		for(int i = 0; i<this.size();i++) {
			if(p.getDni().equals(this.get(i).getPersona().getDni()))
				f = this.get(i);
				facs.add(f);
		}
		if(f == null)
			throw new FacturaNoEncontradaException();
		return facs;
    }

    /**
     * Crea una copia clonada de una factura basada en su identificador.
     *
     * @param id el identificador de la factura que se desea clonar.
     * @return una referencia a la factura clonada.
     * @throws FacturaNoEncontradaException si no se encuentra ninguna factura con el identificador proporcionado.
     * @throws CloneNotSupportedException si la factura no es clonable.
     */
    
    public Object clonaFactura(int id)throws FacturaNoEncontradaException, CloneNotSupportedException {
    	Object facturaClonada;
    	try {
    		facturaClonada=this.buscaPorId(id).clone();
    		return facturaClonada;
    	}
    	catch(FacturaNoEncontradaException e) {
    		throw new FacturaNoEncontradaException();
    	}
    	catch(CloneNotSupportedException e) {
    		throw new CloneNotSupportedException(e.toString());
    	}
    }
}
