package contrataciones;

import modelo.MedioPago;
import modelo.MedioPagoDecorator;

public class Cheque extends MedioPagoDecorator {

	public Cheque() {
		super();
	}
	
	public Cheque(MedioPago mp) {
		super.setEncapsulado(mp);
	}

	@Override
	public String descripcion() {
		return "Pago con cheque";
	}

	@Override
	public double getValor(){
		return encapsulado.getValor() * 1.1;
	}

}
