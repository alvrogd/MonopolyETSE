package aplicacionGUI.menuGUI.registroGUI;

import aplicacion.Aplicacion;
import aplicacion.salidaPantalla.Consola;
import aplicacion.salidaPantalla.ConsolaNormal;
import aplicacionGUI.menuGUI.entrada.EntradaGUI;
import monopoly.Juego;

import java.util.Scanner;

public class ConsolaInterfaz{

    private static RegistroGUI registroGUI;
    private static EntradaGUI entradaGUI;
    private static boolean seHaLeido;

    public static void setRegistroGUI(RegistroGUI registro){
        registroGUI = registro;
    }

    public static EntradaGUI getEntradaGUI() {
        return entradaGUI;
    }

    public static void setEntradaGUI(EntradaGUI entradaGUI) {
        ConsolaInterfaz.entradaGUI = entradaGUI;
    }

    public static boolean isSeHaLeido() {
        return seHaLeido;
    }

    public static void setSeHaLeido(boolean seHaLeido) {
        ConsolaInterfaz.seHaLeido = seHaLeido;
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

    public static Integer leer(String descripcion, boolean integer) {
        if(getEntradaGUI().isPulsadoEnter() && getEntradaGUI().isActivo()){
            if(Aplicacion.consola.isInteger(getEntradaGUI().getBuffer())){
                return Integer.parseInt(getEntradaGUI().getBuffer());
            }
        }
        return null;
    }
}
