package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;

import java.util.Scanner;

public class Output {

    public static void imprimirCabeceraJugador(Jugador jugador){

        String impresion = "╭";
        Scanner entrada = new Scanner(System.in);
        String comando;
        int tamNombre = jugador.getNombre().length();
        Integer fortuna = (int)jugador.getFortuna();
        Integer digitosFortuna = fortuna.toString().length();

        for(int i = 0; i <= tamNombre + 10 + digitosFortuna; i++){

            if(i == 1 || i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion += "┬";
            else
                impresion += "─";

        }


        impresion += "─╮";
        impresion += "\n";
        impresion += "│ │ ";
        impresion += jugador.getNombre();
        impresion += " │ ";
        impresion += (int)jugador.getFortuna();
        impresion += "K $";
        impresion += " │ │";
        impresion += "\n";

        impresion += "╰";

        for(int i = 0; i <= tamNombre + 10 + digitosFortuna; i++){

            if(i == 1 || i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion += "┴";
            else
                impresion += "─";

        }

        impresion += "─╯";

        System.out.println(impresion);
    }

}
