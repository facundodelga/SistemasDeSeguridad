package persistencia;

import java.io.IOException;
import java.io.Serializable;

//PATRON DAO
public interface iPersistencia{

    void abrirInput(String nombre) throws IOException;

    void abrirOutput(String nombre) throws IOException;

    void cerrarOutput() throws IOException;

    void cerrarInput() throws IOException;

    void escribir(Serializable objeto) throws IOException;

    Serializable leer() throws IOException, ClassNotFoundException;

}
