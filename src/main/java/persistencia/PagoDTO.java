package persistencia;

import java.util.GregorianCalendar;

public class PagoDTO {
    private double valor;
    private GregorianCalendar fechaDePago;
    private boolean recargoMoroso;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public GregorianCalendar getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(GregorianCalendar fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public boolean isRecargoMoroso() {
        return recargoMoroso;
    }

    public void setRecargoMoroso(boolean recargoMoroso) {
        this.recargoMoroso = recargoMoroso;
    }
}
