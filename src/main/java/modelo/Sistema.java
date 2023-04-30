package modelo;

import java.util.ArrayList;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.BotonAntipanico;
import contrataciones.Camara;
import contrataciones.Contratacion;
import contrataciones.MovilAcompañamiento;
import contrataciones.iContratable;
import contrataciones.iServicio;
import excepciones.ContratacionNoEncontradaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioNoPerteneceAPersona;
import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import excepciones.PersonaNoEncontradaPorNombreException;
import excepciones.TipoDeContratableIncorrectoException;
import excepciones.TipoDePersonaIncorrectoException;
import excepciones.TipoDePromocionIncorrectoException;
import excepciones.TipoDeServicioIncorrectoException;
import persona.Domicilio;
import persona.Persona;
import persona.PersonaFisica;
import persona.PersonaJuridica;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class Sistema {
	private static Sistema instancia = null;
	private ArregloFacturas facturas;
	private ArregloPersonas personas;

	private Sistema() {
		super();
		this.facturas = new ArregloFacturas();
		this.personas = new ArregloPersonas();
	}

	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}

	// FACTURA
	public int crearFactura(Persona p) {
		Factura f = new Factura(p);
		facturas.add(f);

		return f.getNumFactura();
	}

	public void crearFactura(Persona p, ArrayList<Contratacion> c) {
		Factura f = new Factura(p, c);
		facturas.add(f);
	}

	public void crearFactura(Persona p, Contratacion contr)
			throws ContratacionYaRegistradaException, DomicilioYaRegistradoException {
		ArrayList<Contratacion> c = new ArrayList<Contratacion>();
		c.add(contr);
		this.crearFactura(p, c);
	}

	/**
	 * 
	 * @param id
	 * @throws FacturaNoEncontradaException
	 */
	public void eliminarFactura(int id) throws FacturaNoEncontradaException {
		try {
			this.facturas.borraPorId(id);
		} catch (FacturaNoEncontradaException e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param id
	 * @param mp
	 * @return
	 * @throws FacturaNoEncontradaException
	 */
	public double pagarFactura(int id, String mp) throws FacturaNoEncontradaException {
		Factura f;
		double total;
		try {
			f = this.facturas.buscaPorId(id);
			total = f.totalModificadorMP(mp);
		} catch (FacturaNoEncontradaException e) {
			throw e;
		}
		return total;
	}

	public double pagarFactura(Factura f, String mp) throws FacturaNoEncontradaException {
		return f.totalModificadorMP(mp);
	}

	public Factura buscarFacturaPorPersona(String dni)
			throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		Persona p = personas.buscaPorDni(dni);
		return facturas.buscaPorPersona(p);
	}

	public Factura buscarFacturaPorId(int id) throws FacturaNoEncontradaException {
		return facturas.buscaPorId(id);
	}

	// CONTRATACIONES
	/**
	 * 
	 * @param dni
	 * @param dom
	 * @param serv
	 * @param promo
	 * @return
	 * @throws DomicilioYaRegistradoException
	 * @throws DomicilioNoEncontradoException
	 * @throws ContratacionYaRegistradaException
	 * @throws PersonaNoEncontradaException
	 */
	public Contratacion crearContratacion(String dni, Domicilio dom, iServicio serv, iPromocion promo)
			throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException,
			PersonaNoEncontradaException {
		Contratacion contr = null;
		Persona p1;
		Factura f;
		try {
			p1 = personas.buscaPorDni(dni);// la persona existe

			contr = new Contratacion(dni, dom, serv, promo);
			try {
				f = facturas.buscaPorPersona(p1);// la factura existe
				f.agregarContratacion(contr);

			} catch (FacturaNoEncontradaException e) {// si no se encuentra la factura, la creo

				this.crearFactura(p1, contr);
			}
		} catch (PersonaNoEncontradaException e) {
			throw e;
		}
		return contr;
	}

	// ADICIONALES

	public void contratarAdicional(Contratacion c, iContratable a) {
		c.agregarContratable(a);
	}

	// puede que no sea del todo necesaria, o que sea preferible usar solo el metodo
	// anterior
	public void contratarAdicional(String dni, Domicilio d, iContratable a) throws PersonaNoEncontradaException,
			DomicilioNoEncontradoException, ContratacionNoEncontradaException, DomicilioNoPerteneceAPersona {
		Persona p1, p2;
		Factura f;
		int i = 0;
		p1 = personas.buscaPorDni(dni);// la persona existe
		p2 = personas.buscaPorDomicilio(d);
		if (p1.equals(p2)) {// el domicilio pertenece a la persona esperada
			try {
				f = facturas.buscaPorPersona(p1);
				while (i < f.getContrataciones().size() && !d.equals(f.getContrataciones().get(i).getDomicilio())) {
					i++;
				}
				f.getContrataciones().get(i).agregarContratable(a);

			} catch (FacturaNoEncontradaException e) {
				throw new ContratacionNoEncontradaException();
			}
		} else {
			throw new DomicilioNoPerteneceAPersona(p1, d);
		}
	}

	// PERSONA

	// creación de persona tipo Factory
	public Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException {
		Persona nuevaP = null;
		if (tipo.equalsIgnoreCase("JURIDICA"))
			nuevaP = new PersonaJuridica(nombre, dni);
		else if (tipo.equalsIgnoreCase("FISICA"))
			nuevaP = new PersonaFisica(nombre, dni);
		else
			throw new TipoDePersonaIncorrectoException(tipo);

		this.personas.add(nuevaP);

		return nuevaP;
	}

	// DOMICILIO

	// creacion de domicilios

	public Domicilio crearDomicilio(String calle, int num) {
		Domicilio dom = new Domicilio(calle, num);
		return dom;
	}

	public void asignarNuevoDomicilio(String dni, String calle, int num)
			throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		Domicilio dom = this.crearDomicilio(calle, num);
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}

	public void asignarNuevoDomicilio(String dni, Domicilio dom)
			throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}

	// PROMOCIONES
	public iPromocion obtenerPromocion(String promo) throws TipoDePromocionIncorrectoException {
		iPromocion pr = null;
		if (promo.equalsIgnoreCase("DORADA"))
			pr = new PromoDorada();
		else if (promo.equalsIgnoreCase("PLATINO"))
			pr = new PromoPlatino();
		else if (promo.equalsIgnoreCase("SINPROMO"))
			pr = new SinPromo();
		else
			throw new TipoDePromocionIncorrectoException(promo);
		return pr;
	}

	// SERVICIOS
	public iServicio obtenerServicio(String serv) throws TipoDeServicioIncorrectoException {
		iServicio sr = null;
		if (serv.equalsIgnoreCase("VIVIENDA"))
			sr = new AlarmaVivienda();
		else if (serv.equalsIgnoreCase("COMERCIO"))
			sr = new AlarmaComercio();
		else
			throw new TipoDeServicioIncorrectoException(serv);
		return sr;
	}

	public iContratable obtenerContratable(String cont) throws TipoDeContratableIncorrectoException {
		iContratable cr = null;
		if (cont.equalsIgnoreCase("BOTON"))
			cr = new BotonAntipanico();
		else if (cont.equalsIgnoreCase("CAMARA"))
			cr = new Camara();
		else if (cont.equalsIgnoreCase("MOVIL"))
			cr = new MovilAcompañamiento();
		else
			throw new TipoDeContratableIncorrectoException(cont);
		return cr;
	}

	// public void AgregaFactura(Factura f) {
	// if(existePersona(f))
	// facturas.add(f);
	// }

	// private boolean existePersona(Factura f) {
	// //this.facturas.buscaPorPersona(f);

	// return false;
	// }

	public Factura clonaFacturaPorId(int id) throws FacturaNoEncontradaException, CloneNotSupportedException {
		Object facturaClone;

		facturaClone = this.facturas.clonaFactura(id);

		return (Factura) facturaClone;

	}

	public Persona clonaPersonaPorNombre(String nombre) throws PersonaNoEncontradaPorNombreException, CloneNotSupportedException {
		Object personaClone;

		personaClone = this.personas.clonaPersona(nombre);

		return (Persona) personaClone;
	}

}
