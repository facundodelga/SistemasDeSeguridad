package persona;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

public class MorosoEstado implements IEstado {
	Persona p;

	public MorosoEstado(Persona p) {
		super();
		this.p = p;
	}

	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);
		f.setTotalBonificado(f.getTotalBonificado()*1.3);
		f.setPagoRealizado(true);
	}

	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}

	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}

}
