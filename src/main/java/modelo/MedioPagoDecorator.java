package modelo;

public abstract class MedioPagoDecorator implements MedioPago {
	protected MedioPago encapsulado;
	
	public MedioPagoDecorator() {
		super();
	}
	
	public void setEncapsulado(MedioPago encapsulado){
		this.encapsulado = encapsulado;
	}

	public abstract String descripcion();
}
