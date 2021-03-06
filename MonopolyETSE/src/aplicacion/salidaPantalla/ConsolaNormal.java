package aplicacion.salidaPantalla;

import aplicacionGUI.menuGUI.MenuGUI;
import aplicacionGUI.menuGUI.registroGUI.ConsolaInterfaz;
import aplicacionGUI.menuGUI.registroGUI.RegistroGUI;

import java.util.Scanner;

public class ConsolaNormal implements Consola{
    public ConsolaNormal(){}

    @Override
    public void imprimir(String mensaje){
        ConsolaInterfaz.imprimir(mensaje);
        System.out.println(mensaje);
    }

    @Override
    public String leer(String descripcion){
        Scanner entrada = new Scanner(System.in);
        Output.imprimirEntradaComando(descripcion);
        return(entrada.nextLine());
    }

    public boolean isInteger(String string){

        boolean resultado;
        try {

            Integer.parseInt(string);
            resultado = true;

        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    @Override
    public int leer(String descripcion, boolean integer){

        Scanner entrada = new Scanner(System.in);
        Output.imprimirEntradaComando(descripcion);
        return(entrada.nextInt());
    }
}
