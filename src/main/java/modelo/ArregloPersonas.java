package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;

import excepciones.PersonaNoEncontradaPorNombreException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.NoSePudoClonarPersona;
import excepciones.PersonaNoEncontradaException;
import persona.Domicilio;
import persona.Persona;

public class ArregloPersonas extends ArrayList<Persona> implements Serializable {
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
			if(dni == this.get(i).getDni())
				p = this.get(i);
		}
		if(p == null)
			throw new PersonaNoEncontradaException(dni);
		
		return p;
    }
    
    public boolean existePersona(String dni) {
    	for (Persona persona : this) {
			if (persona.getDni().equalsIgnoreCase(dni)) {
				return true;
			}
		}
    	
    	return false;
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
    
    /**
     * Busca una persona por su domicilio en una colección de personas.
     *
     * @param dom el domicilio que se desea buscar.
     * @return una referencia a la persona encontrada.
     * @throws DomicilioNoEncontradoException si no se encuentra ninguna persona con el domicilio proporcionado.
     */ 
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
    
    /**
     * Busca una persona por su nombre en una colección de personas.
     *
     * @param nombre el nombre de la persona que se desea buscar.
     * @return una referencia a la persona encontrada.
     * @throws PersonaNoEncontradaPorNombreException si no se encuentra ninguna persona con el nombre proporcionado.
     */
    public Persona buscaPorNombre(String nombre)throws PersonaNoEncontradaPorNombreException{
    	Persona p=null;
    	int i=0,N=this.size();
    	
    	while(i<N && nombre==this.get(i).getNombre()) {
    		i++;
    	}
    	if(i>=N) {
    		throw new PersonaNoEncontradaPorNombreException(nombre);
    	}else
    		p=this.get(i);
    	return p;
    }
    
    
    /**
     * Crea una copia clonada de una persona basada en su nombre.
     *
     * @param nombre el nombre de la persona que se desea clonar.
     * @return una referencia a la persona clonada.
     * @throws PersonaNoEncontradaPorNombreException si no se encuentra ninguna persona con el nombre proporcionado.
     * @throws CloneNotSupportedException si la persona no es clonable.
     */
    public Object clonaPersona(String dni)throws PersonaNoEncontradaException, CloneNotSupportedException {
    	Object personaClonada;
    	try {
    		personaClonada=this.buscaPorDni(dni).clone();
    		return personaClonada;
    	}
    	catch(PersonaNoEncontradaException e) {
    		throw new PersonaNoEncontradaException(e.toString());
    	}
    	catch(CloneNotSupportedException e){
    		throw new CloneNotSupportedException("No se pudo clonar la persona solicitada debido a: "+e.toString());
    	}
    }
    
}
