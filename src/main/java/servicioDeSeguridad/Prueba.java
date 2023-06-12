package servicioDeSeguridad;

import java.util.ArrayList;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import contrataciones.iServicio;
import modelo.Factura;
import modelo.Sistema;
import persona.Domicilio;
import persona.Persona;
import promociones.iPromocion;

public class Prueba {

	public static void main(String[] args) throws Exception {

//		PruebaAislada.main(args);
//		PruebaSistema.main(args);
/*
		Sistema s = Sistema.getInstancia();
		
		iPromocion pD= s.obtenerPromocion("Dorada");
		iPromocion pP= s.obtenerPromocion("PLATINO");
		iServicio aV = s.obtenerServicio("Vivienda");
		iContratable a1 = s.obtenerContratable("BOTON");
		iContratable a2 = s.obtenerContratable("CAMARA");
		iContratable a3 = s.obtenerContratable("MOVIL");
		
		
		Persona p = s.crearPersona("Mariela", "40256578", "Fisica");
		Domicilio d1 = s.crearDomicilio("Luro", 1500);
		Domicilio d2 = s.crearDomicilio("San Martin", 1500);

		s.asignarNuevoDomicilio("40256578", d1);
		s.asignarNuevoDomicilio("40256578", d2);		
		
		Contratacion c1 = s.crearContratacion("10256578", d1, aV, pD);
		
		s.contratarAdicional(c1, a1);
		s.contratarAdicional(c1, a3);

		Contratacion c2 = s.crearContratacion("40256578", d2, aV, pP);
		
		s.contratarAdicional(c2, a2);
		s.contratarAdicional(c2, a3);
		 */
//		ArrayList<Factura> facturas = s.buscarFacturaPorPersona("40256578");
//
//		for (Factura f : facturas) {
//
//			System.out.println("DOS CONTRATACIONES: \n");	
//			System.out.println(f.detalle("cheque")+"\n");
//			
//			System.out.println("Antes de eliminar: \n");
//		
//			s.eliminarContratacion("40256578", d2);
//			
//			System.out.println("UNA CONTRATACION: \n");
//			System.out.println(f.detalle("cheque")+"\n");
//			
//			System.out.println("UNA CONTRATACION, PAGA FACTURA: \n");
//			s.pagarFactura("40256578", f.getNumFactura(), "cheque");
//			System.out.println(f.detalle("cheque")+"\n");
//	
//		}		
			
		
	}

}






















