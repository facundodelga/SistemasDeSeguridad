package modelo;

/**
 * La clase Pago representa un medio de pago que contiene un valor monetario.
 * Implementa la interfaz MedioPago.
 */
public class Pago implements MedioPago {
    private double valor;
    
    /**
     * Crea un objeto Pago con el valor proporcionado.
     * @param valor el valor del pago.
     */
    public Pago(double valor) {
        this.valor = valor;
    }

    /**
     * Establece el valor del pago.
     * @param valor el nuevo valor del pago.
     */
    public void setValor(double valor) {
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
}
