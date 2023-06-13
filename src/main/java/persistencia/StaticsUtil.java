package persistencia;

import contrataciones.Contratacion;
import modelo.Factura;
import modelo.Sistema;

public class StaticsUtil {
	
	
	/**
	 * Convierte un objeto de la clase Sistema a un objeto de la clase SistemaDTO.
	 * 
	 * @param s el objeto de la clase Sistema a convertir
	 * @return el objeto de la clase SistemaDTO resultante de la conversión
	 */
    public static SistemaDTO SistemaASistemaDTO(Sistema s) {
	SistemaDTO sistemaDTO = new SistemaDTO();

	sistemaDTO.setUltimaFactura(Factura.getUltFactura());
	sistemaDTO.setUltimaContratacion(Contratacion.getGeneradorId());
	sistemaDTO.setFacturas(s.getFacturas());
	sistemaDTO.setPersonas(s.getPersonas());
	sistemaDTO.setMes(s.getMes());
	sistemaDTO.setServicioTecnico(s.getServicioTecnico());
	sistemaDTO.setTecnicos(s.getTecnicos());
	sistemaDTO.setClientesHilo(s.getClientesHilo());

	return sistemaDTO;
    }

    /**
     * Establece los valores estáticos del sistema basados en los datos proporcionados por un objeto de la clase SistemaDTO.
     * 
     * @param dto el objeto de la clase SistemaDTO que contiene los valores a establecer
     */
    public static void setEstaticosDelSistema(SistemaDTO dto) {
	Factura.setUltFactura(dto.getUltimaFactura());
	Contratacion.setGeneradorId(dto.getUltimaContratacion());

	Sistema.getInstancia().setFacturas(dto.getFacturas());
	Sistema.getInstancia().setPersonas(dto.getPersonas());
	Sistema.getInstancia().setMes(dto.getMes());
	Sistema.getInstancia().setServicioTecnico(dto.getServicioTecnico());
	Sistema.getInstancia().setTecnicos(dto.getTecnicos());
	Sistema.getInstancia().setClientesHilo(dto.getClientesHilo());
    }
}
