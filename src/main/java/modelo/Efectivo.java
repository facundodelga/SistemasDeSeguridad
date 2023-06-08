package modelo;

public class Efectivo extends MedioPagoDecorator {

	public Efectivo(MedioPago mp) {
		super.setEncapsulado(mp);
	}

	@Override
	public String descripcion() {
		return "Pago en efectivo";
	}

	@Override
	public double calcularTotal() {
		return encapsulado.calcularTotal() * 0.8;
	}


}
