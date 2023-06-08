package modelo;

public class Tarjeta extends MedioPagoDecorator {

	
	public Tarjeta(MedioPago mp) {
		super.setEncapsulado(mp);
	}

	@Override
	public String descripcion() {
		return "Pago con tarjeta";
	}

	@Override
	public double calcularTotal() {
		return encapsulado.calcularTotal() * 1.05;
	}

}
