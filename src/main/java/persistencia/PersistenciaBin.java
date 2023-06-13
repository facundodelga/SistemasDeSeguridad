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
     * Abre un archivo de entrada para lectura de objetos.
     * 
     * @param nombre el nombre del archivo de entrada
     * @throws IOException si ocurre un error de entrada/salida al abrir el archivo
     */
    public void abrirInput(String nombre) throws IOException {
        fileinput = new FileInputStream(nombre);
        objectinput = new ObjectInputStream(fileinput);

    }

    /**
     * Abre un archivo de salida para escritura de objetos.
     * 
     * @param nombre el nombre del archivo de salida
     * @throws IOException si ocurre un error de entrada/salida al abrir el archivo
     */
    public void abrirOutput(String nombre) throws IOException {
        fileoutput = new FileOutputStream(nombre);
        objectoutput = new ObjectOutputStream(fileoutput);

    }

    public void cerrarOutput() throws IOException {
        if (objectoutput != null)
            objectoutput.close();

    }

    public void cerrarInput() throws IOException {
        if (objectinput != null)
            objectinput.close();

    }


    public void escribir(Serializable p) throws IOException {
        if (objectoutput != null)
            objectoutput.writeObject(p);
    }

    public Serializable leer() throws IOException, ClassNotFoundException {
        Serializable p = null;
        if (objectinput != null)
            p = (Serializable) objectinput.readObject();
        return p;
    }
}