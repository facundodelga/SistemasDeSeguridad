package servicioDeSeguridad;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.BotonAntipanico;
import contrataciones.Camara;
import contrataciones.Contratacion;
import contrataciones.MovilAcompañamiento;
import contrataciones.iContratable;
import contrataciones.iServicio;
import factory.MedioPagoFactory;
import modelo.Factura;
import modelo.MedioPago;
import persona.Domicilio;
import persona.Persona;
import persona.PersonaFisica;
import persona.PersonaJuridica;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;
import utils.DoubleUtils;

public class PruebaAislada {

	public static Domicilio dom1 = new Domicilio("Calle1", 1111);
	public static Domicilio dom2 = new Domicilio("Calle2", 2222);
	public static Domicilio dom3 = new Domicilio("Calle3", 3333);

	public static Persona personaFisica = new PersonaFisica("P Fisica", "123");
	public static Persona personaJuridica = new PersonaJuridica("P Juridica", "234");

	public static iServicio servicioComercio = new AlarmaComercio();
	public static iServicio servicioVivienda = new AlarmaVivienda();

	public static iContratable contratableAntiPanico = new BotonAntipanico();
	public static iContratable contratableCamara = new Camara();
	public static iContratable contratableMovil = new MovilAcompañamiento();

	public static iPromocion sinPromocion = new SinPromo();
	public static iPromocion promoDorada = new PromoDorada();
	public static iPromocion promoPlatino = new PromoPlatino();
	

	public static void main(String[] args) throws Exception {
		testDomicilio();
		testPersona();
		testServicios();
		testContratables();
		testContratacion();
		testFactura();
	}

	private static void testDomicilio() {
		System.out.println("PRUEBA INICIANDO: Domicilio");
		System.out.println("CLONANDO DOMICILIO");

		Domicilio copiaDom1;

		try {
			copiaDom1 = (Domicilio) dom1.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar domicilio");
		}

		if (!dom1.equals(copiaDom1)) {
			throw new Error("ERROR DE PRUEBA: Domicilio no se clono correctamente");
		}

		System.out.println("PRUEBA COMPLETADA: Domicilio\n");
	}

	private static void testPersona() {
		System.out.println("PRUEBA INICIANDO: Persona");

		System.out.println("CLONANDO PERSONA FISICA");
		Persona copiaPersona;
		try {
			copiaPersona = (Persona) personaFisica.clone();
			System.out.println(copiaPersona);
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar persona fisica");
		}

		if (!personaFisica.equals(copiaPersona)) {
			throw new Error("ERROR DE PRUEBA: Persona fisica no se clono correctamente");
		}

		System.out.println("CLONANDO PERSONA JURIDICA");
		try {
			copiaPersona = (Persona) personaJuridica.clone();
			throw new Error("ERROR DE PRUEBA: No hubo fallo al clonar persona juridica");
		} catch (Exception e) {
		}

		System.out.println("AGREGANDO DOMICILIOS");
		try {
			personaFisica.agregarDomicilio(dom1);
			personaFisica.agregarDomicilio(dom2);
			personaFisica.agregarDomicilio(dom3);
			personaJuridica.agregarDomicilio(dom1);
			personaJuridica.agregarDomicilio(dom2);
			personaJuridica.agregarDomicilio(dom3);
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo agregar domicilios");
		}

		System.out.println("AGREGANDO DOMICILIOS DUPLICADOS");
		try {
			personaFisica.agregarDomicilio(dom1);
			personaFisica.agregarDomicilio(dom2);
			personaFisica.agregarDomicilio(dom3);
			personaJuridica.agregarDomicilio(dom1);
			personaJuridica.agregarDomicilio(dom2);
			personaJuridica.agregarDomicilio(dom3);

			throw new Error("ERROR DE PRUEBA: No hubo fallo al agregar domicilios duplicados");
		} catch (Exception e) {
		}

		System.out.println("PRUEBA COMPLETADA: Persona\n");
	}

	private static void testServicios() {
		System.out.println("PRUEBA INICIANDO: Servicios");

		System.out.println("CLONANDO SERVICIO COMERCIO");
		iServicio copiaServicio;
		try {
			copiaServicio = (iServicio) servicioComercio.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar servicio de alarma comercio");
		}

		if (!servicioComercio.equals(copiaServicio)) {
			throw new Error("ERROR DE PRUEBA: Servicio de alarma comercio no se clono correctamente");
		}

		System.out.println("CLONANDO SERVICIO VIVIENDA");
		try {
			copiaServicio = (iServicio) servicioVivienda.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar servicio de alarma vivienda");
		}

		if (!servicioVivienda.equals(copiaServicio)) {
			throw new Error("ERROR DE PRUEBA: Servicio de alarma vivienda no se clono correctamente");
		}

		System.out.println("PRUEBA COMPLETADA: Servicios\n");
	}

	private static void testContratables() {
		System.out.println("PRUEBA INICIANDO: Contratables");

		System.out.println("CLONANDO BOTON ANTIPANICO");
		iContratable copiaContratable;
		try {
			copiaContratable = (iContratable) contratableAntiPanico.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar boton antipanico");
		}

		if (!contratableAntiPanico.equals(copiaContratable)) {
			throw new Error("ERROR DE PRUEBA: Boton antipanico no se clono correctamente");
		}

		System.out.println("CLONANDO CAMARA");
		try {
			copiaContratable = (iContratable) contratableCamara.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar camara");
		}

		if (!contratableCamara.equals(copiaContratable)) {
			throw new Error("ERROR DE PRUEBA: Camara no se clono correctamente");
		}

		System.out.println("CLONANDO MOVIL");
		try {
			copiaContratable = (iContratable) contratableMovil.clone();
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo clonar movil acompañamiento");
		}

		if (!contratableMovil.equals(copiaContratable)) {
			throw new Error("ERROR DE PRUEBA: Movil acompañamiento no se clono correctamente");
		}

		System.out.println("PRUEBA COMPLETADA: Contratables\n");
	}

	private static void testContratacion() {
		System.out.println("PRUEBA INICIANDO: Contratacion");

		Contratacion contratacion = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(0),
				servicioComercio, sinPromocion);

		System.out.println("AGREGANDO CONTRATABLES");

		contratacion.agregarContratable(contratableAntiPanico);
		contratacion.agregarContratable(contratableCamara);
		contratacion.agregarContratable(contratableCamara);
		contratacion.agregarContratable(contratableMovil);
		contratacion.agregarContratable(contratableMovil);
		
		if (contratacion.cantContratables() != 5) {
			throw new Error("ERROR DE PRUEBA: No se agregaron contratables correctamente");
		}
		
		System.out.println("ELIMINANDO CONTRATABLES");

		try {
			contratacion.eliminarContratable(contratableAntiPanico);
			contratacion.eliminarContratable(contratableCamara);
			contratacion.eliminarContratable(contratableMovil);
		} catch (Exception e) {
		}
		
		if (contratacion.cantContratables() != 2) {
			throw new Error("ERROR DE PRUEBA: No se eliminaron contratables correctamente");
		}

		System.out.println("PRUEBA COMPLETADA: Contratables\n");
	}

	private static void testFactura() {
		System.out.println("PRUEBA INICIANDO: Factura");
		
		Factura factura = personaFisica.crearFactura();
		
		System.out.println("PRUEBA FACTURA: 3 CONTRATACIONES, P FISICA"
				+ "\n* ALARMA VIVIENDA, 2 AP, 2 CAM, 1 MOVIL, PROMO PLATINO"
				+ "\n* ALARMA COMERCIO, 1 MOVIL, PROMO DORADA"
				+ "\n* ALARMA COMERCIO, SIN PROMO"
				+ "\n"
		);
		
		Contratacion contratacion1 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(0),
				servicioVivienda, promoPlatino);
		Contratacion contratacion2 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(1),
				servicioComercio, promoDorada);
		Contratacion contratacion3 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(2),
				servicioComercio, sinPromocion);
		
		contratacion1.agregarContratable(contratableAntiPanico);
		contratacion1.agregarContratable(contratableAntiPanico);
		contratacion1.agregarContratable(contratableCamara);
		contratacion1.agregarContratable(contratableCamara);
		contratacion1.agregarContratable(contratableMovil);
		
		contratacion2.agregarContratable(contratableMovil);
		
		try {
			factura.agregarContratacion(contratacion1);
			factura.agregarContratacion(contratacion2);
			factura.agregarContratacion(contratacion3);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		MedioPago cheque = MedioPagoFactory.getMedioPago("CHEQUE", factura);
		
		System.out.println(factura.detalle(cheque));
		
		if (!DoubleUtils.equals(factura.calcularTotal(), 48450)) {
			throw new Error("ERROR DE PRUEBA: El precio de factura esta mal calculado");
		}
	
		System.out.println("PRUEBA COMPLETADA: Factura\n"); 
		
	}
}
