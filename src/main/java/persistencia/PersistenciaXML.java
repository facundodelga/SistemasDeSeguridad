package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PersistenciaXML implements iPersistencia{
    private XMLEncoder xmlEncoder;
    private XMLDecoder xmlDecoder;
    private FileOutputStream fileOutput;
    private FileInputStream fileInput;


    @Override
    public void abrirInput(String nombre) throws IOException {
        fileInput = new FileInputStream(nombre);
        xmlDecoder = new XMLDecoder(fileInput);
    }

    @Override
    public void abrirOutput(String nombre) throws IOException {
        fileOutput = new FileOutputStream(nombre);
        xmlEncoder = new XMLEncoder(fileOutput);
    }

    @Override
    public void cerrarOutput() throws IOException {
        xmlEncoder.close();
    }

    @Override
    public void cerrarInput() throws IOException {
        if(xmlDecoder!=null)
            xmlDecoder.close();
    }

    @Override
    public void escribir(Serializable objeto) throws IOException {
        xmlEncoder.writeObject(objeto);
    }

    @Override
    public Serializable leer() throws IOException, ClassNotFoundException {
        Serializable objeto = null;
        if(xmlDecoder != null)
            objeto = (Serializable) xmlDecoder.readObject();
        return objeto;
    }

}
