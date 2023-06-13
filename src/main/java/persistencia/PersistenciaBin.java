package persistencia;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersistenciaBin implements iPersistencia {

    private FileOutputStream fileoutput;
    private FileInputStream fileinput;
    private ObjectOutputStream objectoutput;
    private ObjectInputStream objectinput;
    /**
     * Abre un archivo de entrada para lectura de objetos serializados.
     *
     * @param nombre El nombre del archivo de entrada.
     * @throws IOException Si ocurre un error al abrir el archivo.
     */
    public void abrirInput(String nombre) throws IOException {
        fileinput = new FileInputStream(nombre);
        objectinput = new ObjectInputStream(fileinput);

    }
    /**
     * Abre un archivo de salida para escritura de objetos serializados.
     *
     * @param nombre El nombre del archivo de salida.
     * @throws IOException Si ocurre un error al abrir el archivo.
     */
    public void abrirOutput(String nombre) throws IOException {
        fileoutput = new FileOutputStream(nombre);
        objectoutput = new ObjectOutputStream(fileoutput);

    }
    /**
     * Cierra el archivo de salida.
     *
     * @throws IOException Si ocurre un error al cerrar el archivo.
     */
    public void cerrarOutput() throws IOException {
        if (objectoutput != null)
            objectoutput.close();

    }
    /**
     * Cierra el archivo de entrada.
     *
     * @throws IOException Si ocurre un error al cerrar el archivo.
     */
    public void cerrarInput() throws IOException {
        if (objectinput != null)
            objectinput.close();

    }

    /**
     * Escribe un objeto serializable en el archivo de salida.
     *
     * @param p El objeto serializable a escribir.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribir(Serializable p) throws IOException {
        if (objectoutput != null)
            objectoutput.writeObject(p);
    }
    /**
     * Lee un objeto serializable del archivo de entrada.
     *
     * @return El objeto serializable leído.
     * @throws IOException            Si ocurre un error al leer del archivo.
     * @throws ClassNotFoundException Si la clase del objeto no se encuentra.
     */
    public Serializable leer() throws IOException, ClassNotFoundException {
        Serializable p = null;
        if (objectinput != null)
            p = (Serializable) objectinput.readObject();
        return p;
    }
}