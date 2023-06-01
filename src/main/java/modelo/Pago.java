package modelo;

import java.util.GregorianCalendar;

/**
 * La clase Pago representa un medio de pago que contiene un valor monetario.
 * Implementa la interfaz MedioPago.
 */
public class Pago implements MedioPago,Cloneable{
    private double valor;
    private GregorianCalendar fechaDePago;
    private boolean recargoMoroso = false;
    private MedioPago mp;
    
    
    public Pago(double valor, GregorianCalendar fechaDePago) {
		super();
		this.valor = valor;
		this.fechaDePago = fechaDePago;
	}

	public Pago(double valor, GregorianCalendar fechaDePago, boolean recargoMoroso) {
		super();
		this.valor = valor;
		this.fechaDePago = fechaDePago;
		this.recargoMoroso = recargoMoroso;
	}

	/**
     * Crea un objeto Pago con el valor proporcionado.
     * @param valor el valor del pago.
     */
    public Pago(double valor) {
    	assert valor > 0 : "El campo valor debe ser mayor que 0";
        this.valor = valor;
    }

    /**
     * Establece el valor del pago.
     * @param valor el nuevo valor del pago.
     */
    public void setValor(double valor) {
    	assert valor > 0 : "El campo valor debe ser mayor que 0";
        this.valor = valor;
    }

    /**
     * Devuelve el valor del pago.
     * @return el valor del pago.
     */
    @Override
    public double getValor() {
        return this.valor;
    }
    
    /**
     * Crea y devuelve una copia superficial de esta instancia de Pago.
     *
     * @return una referencia a la copia clonada de esta instancia.
     * @throws CloneNotSupportedException si la instancia de Pago no es clonable.
     */
    @Override
	public Object clone()throws CloneNotSupportedException{
		try {
			Pago nObj=(Pago)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Pago, FALLO="+e.toString());
		}
	}
}
