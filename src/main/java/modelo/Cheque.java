package modelo;

public class Cheque extends MedioPagoDecorator {

	public Cheque(MedioPago encapsulado) {
		super.setEncapsulado(encapsulado);
	}

	@Override
	public String descripcion() {
		return "Pago con cheque";
	}

	@Override
	public double calcularTotal() {
		return encapsulado.calcularTotal() * 1.1;
	}

}
