package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;

public class Output {

    public static void imprimirEntradaComandos(Jugador jugador, int largo){

        String impresion = "╭";
        int tamNombre = jugador.getNombre().length();
        Integer fortuna = (int)jugador.getFortuna();
        Integer digitosFortuna = fortuna.toString().length();

        for(int i = 0; i < largo; i++){

            if(i == 1 || i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion += "┬";
            else
                impresion += "─";

        }

        impresion += "\n";
        impresion += "│ │ ";
        impresion += jugador.getNombre();
        impresion += " │ ";
        impresion += (int)jugador.getFortuna();
        impresion += "K $";
        impresion += " │";
        impresion += ": \n";
        impresion += "╰";

        for(int i = 0; i < largo; i++){

            if(i == 1 || i == tamNombre+4 || i == tamNombre + 10 + digitosFortuna)
                impresion += "┴";
            else
                impresion += "─";

        }

        System.out.println(impresion);
    }

}
