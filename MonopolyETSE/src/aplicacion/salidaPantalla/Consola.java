package aplicacion.salidaPantalla;

public interface Consola {

    void imprimir(String mensaje);

    String leer(String descripcion);

    int leer(String descripcion, boolean integer);

}
