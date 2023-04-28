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

		Domicilio dom =new Domicilio("Luro", 1500);

		Contratacion contC= new Contratacion("42157",dom,servC,pB);
		Contratacion contCD= new Contratacion("42157",dom,servC,pD);
		Contratacion contCP= new Contratacion("42157",dom,servC,pP);
		
		Contratacion contV= new Contratacion("42157",dom,servV,pB);
		Contratacion contVD= new Contratacion("42157",dom,servV,pD);
		Contratacion contVP= new Contratacion("42157",dom,servV,pP);

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
		Persona p2 = new PersonaJuridica("ududud", "484209");

		System.out.println(s.creaFactura(p));
		
		s.creaFactura(p2,c);
		
		System.out.println(s.pagarFactura(1, "EFECTIVO"));
		
	}

}
