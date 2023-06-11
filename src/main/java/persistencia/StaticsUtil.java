package persistencia;

import modelo.Factura;
import modelo.Sistema;

public class StaticsUtil {
    public static SistemaDTO SistemaASistemaDTO(Sistema s){
        SistemaDTO sistemaDTO = null;

        sistemaDTO.setUltimaFactura(Factura.getUltFactura());
        sistemaDTO.setFacturas(s.getFacturas());
        sistemaDTO.setPersonas(s.getPersonas());
        sistemaDTO.setMes(s.getMes());
        sistemaDTO.setServicioTecnico(s.getServicioTecnico());
        sistemaDTO.setTecnicos(s.getTecnicos());

        return sistemaDTO;
    }

    public static void setEstaticosDelSistema(SistemaDTO dto){
        Factura.setUltFactura(dto.getUltimaFactura());

        Sistema.getInstancia().setFacturas(dto.getFacturas());
        Sistema.getInstancia().setPersonas(dto.getPersonas());
        Sistema.getInstancia().setMes(dto.getMes());
        Sistema.getInstancia().setServicioTecnico(dto.getServicioTecnico());
        Sistema.getInstancia().setTecnicos(dto.getTecnicos());
    }
}
