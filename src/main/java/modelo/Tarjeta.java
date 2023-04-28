package modelo;

public class Tarjeta extends MedioPagoDecorator {

	public Tarjeta() {
		
	}
	
	public Tarjeta(MedioPago mp) {
		super.setEncapsulado(mp);
	}

	@Override
	public String descripcion() {
		return "Pago con tarjeta";
	}

	@Override
	public double getValor() {
		return encapsulado.getValor() * 1.05;
	}

}
