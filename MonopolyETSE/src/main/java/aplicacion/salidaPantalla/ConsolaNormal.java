package aplicacion.salidaPantalla;

import java.util.Scanner;

public class ConsolaNormal implements Consola{

    public ConsolaNormal(){}

    @Override
    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }

    @Override
    public String leer(String descripcion){
        Scanner entrada = new Scanner(System.in);
        Output.imprimirEntradaComando(descripcion);
        return(entrada.nextLine());
    }

}
