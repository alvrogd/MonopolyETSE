package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;

import java.util.Scanner;

public class Menu {

    private Aplicacion app;

    public Menu(Aplicacion app){

        if(app == null){
            System.err.println("app referencia a null.");
            return;
        }
            this.app = app;
    }


    public Aplicacion getApp() {
        return app;
    }


    public void iniciarAplicacion(){
        Scanner entrada = new Scanner(System.in);


        Output.mensaje(        "",
                "___  ___                              _         _____ _____ _____ _____ " ,
                "|  \\/  |                             | |       |  ___|_   _/  ___|  ___|" ,
                "| .  . | ___  _ __   ___  _ __   ___ | |_   _  | |__   | | \\ `--.| |__  " ,
                "| |\\/| |/ _ \\| '_ \\ / _ \\| '_ \\ / _ \\| | | | | |  __|  | |  `--. \\  __| " ,
                "| |  | | (_) | | | | (_) | |_) | (_) | | |_| | | |___  | | /\\__/ / |___ " ,
                "\\_|  |_/\\___/|_| |_|\\___/| .__/ \\___/|_|\\__, | \\____/  \\_/ \\____/\\____/ " ,
                "                         | |             __/ |                          " ,
                "                         |_|            |___/     ",
                "     _________                                           " ,
                "    / ======= \\      (!) Unas reglas iniciales:                                 " ,
                "   / __________\\                                                                " ,
                "  | ___________ |         -> ¿Cómo crear los jugadores?                          " ,
                "  | | -       | |               (*) Comando: crear jugador <nombre> <tipo_Avatar>" ,
                "  | |         | |               (*) De 2 a 6 jugadores.                          " ,
                "  | |_________| |               (*) Tipos de avatares disponibles:               " ,
                "  \\=____________/                   - Coche                                     " ,
                "  / \"\"\"\"\"\"\"\"\"\"\" \\                   - Esfinge                                   " ,
                " / ::::::::::::: \\                  - Sombrero                                  " ,
                "(_________________)                 - Pelota                                    " ,
                "",
                "",
                "-> ¿Cómo empezar a jugar?",
                "          (*) Comando: iniciar juego",
                "",
                "-> Para obtener información de los comandos introduzca: ayuda");

        app.imprimirBuffer();

        Output.imprimirEntradaComando();
        app.introducirComando(entrada.nextLine());

        while(true){

            if(app.getJuego().isIniciado()){
                Output.imprimirCabeceraJugador(app.getJuego().getTurno());
            }

            Output.imprimirEntradaComando();
            app.introducirComando(entrada.nextLine());

        }
    }

}
