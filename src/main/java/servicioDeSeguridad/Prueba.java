package servicioDeSeguridad;

import java.util.ArrayList;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.Contratacion;
import contrataciones.iServicio;
import modelo.*;
import persona.Domicilio;
import persona.Persona;
import persona.PersonaJuridica;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class Prueba {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Sistema s = Sistema.getInstancia();

		iServicio servV = new AlarmaVivienda();
		iServicio servC = new AlarmaComercio();

		iPromocion pD = new PromoDorada();
		iPromocion pP = new PromoPlatino();
		iPromocion pB = new SinPromo();

		Domicilio dom1 =new Domicilio("Luro", 1500);
		Domicilio dom2 =new Domicilio("Luro", 1552);
		Domicilio dom3 =new Domicilio("Rivadavia", 1320);

		Contratacion contC= new Contratacion("42157",dom1,servC,pB);
		Contratacion contCD= new Contratacion("42157",dom1,servC,pD);
		Contratacion contCP= new Contratacion("42157",dom1,servC,pP);
		
		Contratacion contV= new Contratacion("42157",dom2,servV,pB);
		Contratacion contVD= new Contratacion("42157",dom3,servV,pD);
		Contratacion contVP= new Contratacion("42157",dom2,servV,pP);

		ArrayList<Contratacion> c = new ArrayList<Contratacion>();

		c.add(contC);
		c.add(contCD);
		c.add(contCP);

		System.out.println("Comercio");
		System.out.println(contCD.getTarifa());
		System.out.println(contCP.getTarifa());
		System.out.println(contC.getTarifa());
		
		System.out.println("Vivienda");
		System.out.println(contVD.getTarifa());
		System.out.println(contVP.getTarifa());
		System.out.println(contV.getTarifa());

		Persona p = new PersonaJuridica("Pedro", "483984209");
		p.agregarDomicilio(dom1);
		p.agregarDomicilio(dom2);
		
		Persona p2 = new PersonaJuridica("ududud", "484209");
		p2.agregarDomicilio(dom3);
		
		System.out.println(s.crearFactura(p));
		
		s.crearFactura(p2,c);
		
		System.out.println(s.pagarFactura(1, "EFECTIVO"));
	/* //pruebas de funciones nuevas
		ArregloPersonas personas = new ArregloPersonas();
		personas.add(p2);
		personas.add(p);
		System.out.println(personas.buscaPorDomicilio(dom3));
		System.out.println(p2);
		System.out.println(p);
		*/
	}

}
