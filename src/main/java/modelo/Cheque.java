package modelo;

public class Cheque extends MedioPagoDecorator {

	public Cheque() {
		super();
	}
	
	public Cheque(MedioPago encapsulado) {
		super.setEncapsulado(encapsulado);
	}

	@Override
	public String descripcion() {
		return "Pago con cheque";
	}

	@Override
	public double getValor() {
		return encapsulado.getValor() * 1.1;
	}

}
