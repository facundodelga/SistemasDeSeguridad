package excepciones;

public class NoSePudoClonarPersona extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoSePudoClonarPersona(String causa) {
		super(causa);
	}
}
