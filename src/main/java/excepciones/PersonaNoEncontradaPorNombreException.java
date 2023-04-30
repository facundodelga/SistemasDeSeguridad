package excepciones;

public class PersonaNoEncontradaPorNombreException extends Exception {
	String nombre;
	private static final long serialVersionUID = 1L;
	
	public PersonaNoEncontradaPorNombreException(String nombre) {
		super("La persona buscada por nombre="+nombre+" no ah sido encontrada");
		this.nombre=nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
