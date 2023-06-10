package simulacion;

public interface Observer {
    void update(Observable o,Object mensaje) throws IllegalAccessException;
}
