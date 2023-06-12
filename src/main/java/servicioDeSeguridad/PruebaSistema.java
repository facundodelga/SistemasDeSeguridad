package servicioDeSeguridad;

import java.util.ArrayList;
import java.util.Arrays;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.BotonAntipanico;
import contrataciones.Camara;
import contrataciones.Contratacion;
import contrataciones.MovilAcompañamiento;
import contrataciones.iContratable;
import contrataciones.iServicio;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.MedioPago;
import modelo.Sistema;
import persona.Domicilio;
import persona.Persona;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class PruebaSistema {
/*	public static Sistema sistema = Sistema.getInstancia();

	public static Domicilio dom1 = new Domicilio("Calle1", 1111);
	public static Domicilio dom2 = new Domicilio("Calle2", 2222);
	public static Domicilio dom3 = new Domicilio("Calle3", 3333);

	public static Persona personaFisica;
	public static Persona personaJuridica;

	public static iServicio servicioComercio = new AlarmaComercio();
	public static iServicio servicioVivienda = new AlarmaVivienda();

	public static iContratable contratableAntiPanico = new BotonAntipanico();
	public static iContratable contratableCamara = new Camara();
	public static iContratable contratableMovil = new MovilAcompañamiento();

	public static iPromocion sinPromocion = new SinPromo();
	public static iPromocion promoDorada = new PromoDorada();
	public static iPromocion promoPlatino = new PromoPlatino();

	public static void main(String[] args) throws Exception {
		testPersona();

		testFacturas();

	}

	public static void initVariables() {

	}

	private static void testPersona() {
		System.out.println("PRUEBA INICIANDO: Persona");

		System.out.println("AGREGANDO PERSONA AL SISTEMA");

		try {
			personaFisica = sistema.crearPersona("P Fisica", "123", "FISICA");
			personaJuridica = sistema.crearPersona("P Juridica", "234", "JURIDICA");
		} catch (Exception e) {
			throw new Error("ERROR DE PRUEBA: No se pudo agregar personas al sistema");
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

		System.out.println("PRUEBA COMPLETADA: Persona\n");
	}

	private static void testFacturas() throws FacturaNoEncontradaException, CloneNotSupportedException, DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		System.out.println("PRUEBA INICIANDO: Facturas");

		Factura factura1 = personaFisica.crearFactura();

		System.out.println("PRUEBA FACTURA1: 2 CONTRATACIONES, P FISICA"
				+ "\n* ALARMA VIVIENDA, 2 AP, 2 CAM, 1 MOVIL, PROMO PLATINO"
				+ "\n* ALARMA COMERCIO, 1 MOVIL, PROMO DORADA" + "\n* ALARMA COMERCIO, SIN PROMO" + "\n");

		System.out.println("PRUEBA FACTURA2: 1 CONTRATACION, P JURIDICA" + "\n* ALARMA VIVIENDA, PROMO PLATINO");

		Contratacion contratacion1 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(0),
				servicioVivienda, promoPlatino);
		Contratacion contratacion2 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(1),
				servicioComercio, promoDorada);
		Contratacion contratacion3 = new Contratacion(personaFisica.getDni(), personaFisica.getDomicilio(2),
				servicioComercio, sinPromocion);
		
		ArrayList<Contratacion> contrataciones = new ArrayList<>(Arrays.asList(contratacion1, contratacion2,contratacion3));

		contratacion1.agregarContratable(contratableAntiPanico);
		contratacion1.agregarContratable(contratableAntiPanico);
		contratacion1.agregarContratable(contratableCamara);
		contratacion1.agregarContratable(contratableCamara);
		contratacion1.agregarContratable(contratableMovil);

		contratacion2.agregarContratable(contratableMovil);

		
		
		sistema.crearFactura(personaFisica, contrataciones);
		sistema.crearContratacion(personaFisica.getDni(), personaFisica.getDomicilio(1), servicioComercio, promoPlatino);

		System.out.println(sistema.detalleFacturas());
		System.out.println(sistema.detalleFacturas("TARJETA"));
		System.out.println(sistema.detalleFacturas("CHEQUE"));
		System.out.println(sistema.detalleFacturas("EFECTIVO"));

		System.out.println("PRUEBA COMPLETADA: Facturas\n");
	}
	*/
}
