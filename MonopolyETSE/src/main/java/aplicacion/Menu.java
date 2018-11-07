package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;

import java.util.Scanner;

public class Menu {

    public Menu(Aplicacion app){

        Scanner entrada = new Scanner(System.in);

        Output.mensaje("Bienvenido a MonopolyETSE", "",
                "Unas reglas iniciales:",
                "    -> ¿Cómo crear los jugadores?",
                "          (*) Comando: crear jugador <nombre> <tipo_Avatar>",
                "          (*) De 2 a 6 jugadores.",
                "          (*) Tipos de avatares disponibles:",
                "               - Coche",
                "               - Esfinge",
                "               - Sombrero",
                "               - Pelota",
                "",
                "    -> ¿Cómo empezar a jugar?",
                "          (*) Comando: iniciar juego",
                "",
                "    -> Para obtener información de los comandos introduzca: ayuda");

        Output.imprimirEntradaComando();
        app.introducirComando(entrada.nextLine());

        while(true){

            if(app.getJuego().isIniciado()){
                System.out.println(TableroASCII.pintaTablero(app.getJuego().getTablero()));
                Output.imprimirCabeceraJugador(app.getJuego().getTurno());
            }

            Output.imprimirEntradaComando();
            app.introducirComando(entrada.nextLine());

        }

    }

}
