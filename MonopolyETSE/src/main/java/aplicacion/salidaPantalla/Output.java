package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;

import java.util.ArrayList;
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

        //todo varios appends

        impresion.append("─╮\n│ │ ");
        impresion.append(jugador.getNombre());
        impresion.append(" │ ");

        impresion.append(TipoColor.VerdeANSI.getFondo());
        impresion.append((int)jugador.getFortuna());
        impresion.append("K €");
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

        System.out.println("  ╰──> Acción: ");

    }

    private static void imprimirRecuadro(ArrayList<String> mensaje, String tipo, TipoColor color, int ancho, int alto){
        StringBuilder impresion = new StringBuilder();

        int lineas = mensaje.size();

        int max = 0;

        int caracteresContar = 0;

        String opcion;

        if(ancho < 0)
            ancho = 0;
        if(alto < 0)
            alto = 0;

        if(mensaje == null){
            System.err.println("Mensajes no inicializados.");
            return;
        }

        for(int i = 0; i < lineas; i++){
            int aux = mensaje.get(i).length();
            if(max < aux)
                max = mensaje.get(i).length();
        }

        switch(tipo){
            case "sugerencia":
                opcion = "[S] SUGERENCIA: ";
                break;

            case "error":
                opcion = "[!] ERROR: ";
                break;

            default:
                opcion = "[*] ";
                break;
        }

        caracteresContar += opcion.length();

        impresion.append(color.getFondo());
        impresion.append("╔");

        for(int i = 0; i < max+2*ancho+caracteresContar; i++){
            impresion.append("═");
        }

        impresion.append("╗\n");

        for(int i = 0; i < alto; i++){
            impresion.append("║");
            for(int j = 0; j < max+2*ancho+caracteresContar; j++){
                impresion.append(" ");
            }
            impresion.append("║\n");
        }

        for(int i = 0; i < lineas; i++) {

            impresion.append("║");

            for(int j = 0; j < ancho; j++)
                impresion.append(" ");

                if (i == 0) {

                    impresion.append(color.getFondo());
                    impresion.append(TipoColor.Negrita.getFondo());
                    impresion.append(opcion);
                    impresion.append(TipoColor.resetAnsi.getFondo());
                    impresion.append(color.getFondo());
                    impresion.append(mensaje.get(i));

                    for (int j = 0; j < ancho; j++) {
                        impresion.append(" ");
                    }

                } else {

                    impresion.append(mensaje.get(i));

                    int longMensaje = mensaje.get(i).length();

                    for (int j = 0; j < max - longMensaje + caracteresContar + ancho; j++) {
                        impresion.append(" ");
                    }
                }

            impresion.append("║\n");
        }

        for(int i = 0; i < alto; i++){
            impresion.append("║");
            for(int j = 0; j < max+2*ancho+caracteresContar; j++){
                impresion.append(" ");
            }
            impresion.append("║\n");
        }
        impresion.append("╚");

        for(int i = 0; i < max+2*ancho+caracteresContar; i++){
            impresion.append("═");
        }

        impresion.append("╝\n");

        System.out.println(impresion);
    }

    public static void errorComando(String error){
        ArrayList<String> errores = new ArrayList<>();
        errores.add(error);
        imprimirRecuadro(errores, "error", TipoColor.RojoANSI, 3, 1);
    }

    public static void errorComando(ArrayList<String> error){
        imprimirRecuadro(error, "error", TipoColor.RojoANSI, 3, 1);
    }

    public static void sugerencia(String sugerencia){
        ArrayList<String> sugerencias = new ArrayList<>();
        sugerencias.add(sugerencia);
        imprimirRecuadro(sugerencias, "sugerencia", TipoColor.VerdeANSI, 3, 1);
    }

    public static void sugerencia(ArrayList<String> sugerencia){
        imprimirRecuadro(sugerencia, "sugerencia", TipoColor.VerdeANSI, 3, 1);
    }


}
