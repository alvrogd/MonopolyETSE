package aplicacionGUI.menuGUI.registroGUI;

import aplicacion.salidaPantalla.Consola;

public class ConsolaInterfaz{

    private static RegistroGUI registroGUI;

    public static void setRegistroGUI(RegistroGUI registro){
        registroGUI = registro;
    }

    public static RegistroGUI getRegistroGUI() {
        return registroGUI;
    }

    public static void imprimir(String mensaje) {
        if(getRegistroGUI() != null)
            getRegistroGUI().anadirContenido(mensaje);
    }

    public static String leer(String descripcion) {
        return null;
    }

    public static int leer(String descripcion, boolean integer) {
        return 0;
    }
}
