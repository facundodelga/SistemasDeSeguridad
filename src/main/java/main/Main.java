package main;
import controlador.Controlador;
import modelo.Sistema;
import vista.VistaSistemaDeSeguridad;

import simulacion.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    ServicioTecnico st = new ServicioTecnico();
	    new Controlador(st);
	}
	
}
