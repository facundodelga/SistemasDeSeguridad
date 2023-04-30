package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.DomicilioNoEncontradoException;
import excepciones.PersonaNoEncontradaException;
import persona.Domicilio;
import persona.Persona;

public class ArregloPersonas extends ArrayList<Persona>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ArregloPersonas() {
		super();
    }
    
	/**
	 * Busca una factura en la lista dado un Id válido.
	 * @param id el Id de la factura que se busca.
	 * @return la factura correspondiente al Id buscado.
	 * @throws Exception si el Id buscado no existe.
	 */
    public Persona buscaPorDni(String dni)  throws PersonaNoEncontradaException{
		Persona p = null;
		for(int i = 0; i<this.size();i++) {
			if(dni == this.get(i).getDni());
			p = this.get(i);
		}
		if(p == null)
			throw new PersonaNoEncontradaException(dni);
		
		return p;
    }
    

	/**
	 * Borra una factura de la lista dandole un Id valido.
	 * 
	 * @param id El id de la factura a borrar.
	 * @throws Exception si el id que se proporciona no existe en la lista.
	 */
    public void borraPorDni(String dni)  throws Exception{
		int i =0;
		while(i < this.size() && dni==this.get(i).getDni()) {
			i++;
		}
		
		if( dni == this.get(i).getDni()) {
			this.remove(i);
		}else {
			throw new PersonaNoEncontradaException(dni);
		}	
    }
    
    public Persona buscaPorDomicilio(Domicilio dom) throws DomicilioNoEncontradoException {
    	Persona p = null;
    	int i;
    	for(i=0;i<this.size();i++) {
    		Iterator<Domicilio> itDom=this.get(i).getIterator();
    		while(itDom.hasNext()) {
    			Domicilio d=itDom.next();
    			if(d.equals(dom)) {
    				p=this.get(i);
    			}
    		}    		
    	}
    	if(p==null) {
    		throw new DomicilioNoEncontradoException(dom);
    	}
    	return p;
    }

}
