package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;

import java.util.Scanner;

public class Output {

    public static void imprimirCabeceraJugador(Jugador jugador){

        StringBuilder impresion = new StringBuilder();

        impresion.append("╭");

        Scanner entrada = new Scanner(System.in);
        String comando;
        int tamNombre = jugador.getNombre().length();
        Integer fortuna = (int)jugador.getFortuna();
        Integer digitosFortuna = fortuna.toString().length();

        for(int i = 0; i <= tamNombre + 10 + digitosFortuna; i++){

            if(i == 1 || i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion.append("┬");
            else
                impresion.append("─");

        }


        impresion.append("─╮");
        impresion.append("\n");
        impresion.append("│ │ ");
        impresion.append(jugador.getNombre());
        impresion.append(" │ ");
        impresion.append(TipoColor.VerdeANSI.getFondo());
        impresion.append((int)jugador.getFortuna());
        impresion.append("K $");
        impresion.append(TipoColor.resetAnsi.getFondo());
        impresion.append(" │ │");
        impresion.append("\n");
        impresion.append("╰");

        for(int i = 0; i <= tamNombre + 10 + digitosFortuna; i++){

            if(i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion.append("┴");

            else if(i == 1)
                impresion.append("┼");

            else
                impresion.append("─");

        }

        impresion.append("─╯");

        System.out.println(impresion);
    }

    public static void imprimirEntradaComando(){

        Scanner entrada = new Scanner(System.in);

        System.out.println("  ╰──> Comando: ");

    }

}
