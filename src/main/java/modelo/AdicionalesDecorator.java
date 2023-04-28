package modelo;

import contrataciones.iContratable;

//para que usamos esta clase?? el boton, el movil y la camara implementan
//iContratable, no extienden de la clase, creo que esto quedó descartado
public abstract class AdicionalesDecorator implements iContratable {
    iContratable contratable;
    public abstract double getValor();
    public abstract String getDescripcion();
}
