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
	public double getValor() {
		return encapsulado.getValor() * 0.8;
	}

}
