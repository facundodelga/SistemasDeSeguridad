package persistencia;

import modelo.ArregloFacturas;
import modelo.Factura;
import modelo.Sistema;
import persona.*;

import java.util.ArrayList;

public class UtilDTO {
    public static Sistema SistemaDTOASistema(SistemaDTO sDto){
        Sistema s = new Sistema(sDto.getFacturas(),);

    }

    public static ArregloFacturas arregloFacturasDTOAArregloFacturas(ArrayList<FacturaFisicaDTO> Dto){
        ArregloFacturas arregloFacturas = new ArregloFacturas();

        for(FacturaFisicaDTO fDto : Dto){
            arregloFacturas.add( facturaDtoAFactura(fDto) );
        }
    }

    public static Factura facturaDtoAFactura(FacturaFisicaDTO Dto){
        Factura f = new Factura(
                personaDtoAPersona(Dto.getPersona()),
                );
        return f;
    }

    public static PersonaFisica personaDtoAPersona(PersonaFisicaDTO dto){
        PersonaFisica p = new PersonaFisica(dto.getNombre(), dto.getDni(),dto.getDomicilios(),estadoDTOAEstado(dto));

        return p;
    }

    public static IEstado estadoDTOAEstado(PersonaFisicaDTO personaFDTO){
        IEstado estadoPersona;
        if(personaFDTO.getEstado().equalsIgnoreCase("moroso")){
            estadoPersona = new MorosoEstado();
        }
        if(personaFDTO.getEstado().equalsIgnoreCase("sin contratacion")){
            estadoPersona = new SinContratacionEstado();
        }
        if(personaFDTO.getEstado().equalsIgnoreCase("con contratacion")){
            estadoPersona = new ConContratacionEstado();
        }

        return estadoPersona;
    }

    public static ArrayList<Domicilio> ArregloDomicliosDTOAArregloDomicilio(ArrayList<String> dto){
        ArrayList<Domicilio> d = new ArrayList<>();
        for(String domicilio : dto){
            d.add(DomicilioDTOADomicilio(domicilio));
        }

        return d;
    }

    public static Domicilio DomicilioDTOADomicilio(String dto){
        String[] partes = dto.split("\\s(?=\\D)");

        String nombreCalle = partes[0];
        int numero = Integer.parseInt(partes[1]);

        return new Domicilio(nombreCalle,numero);
    }
}
