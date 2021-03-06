package aplicacion;

import aplicacion.excepciones.ArgComandoIncorrectoException;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacion.salidaPantalla.ConsolaNormal;
import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;
import monopoly.jugadores.excepciones.EstarPenalizadoException;

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

        try {
            app.introducirComando(Aplicacion.consola.leer("Acción"));
        } catch (MonopolyETSEException e) {
            Output.errorComando(e.getMessage());
            app.imprimirBuffer();
        }

        while(true){

            if(app.getJuego().isIniciado()){
                if(!app.getJuego().isFinalizado())
                    Output.imprimirCabeceraJugador(app.getJuego().getTurno());
                else{
                    app.imprimirBuffer();
                    System.exit(1);
                }
            }

            try {
                app.introducirComando(Aplicacion.consola.leer("Acción"));
            } catch(ArgComandoIncorrectoException arg){

                if(arg.isHaySugerencia())
                    Output.sugerencia(Output.toArrayString(arg.getSugerencia()));

                Output.errorComando(arg.getMessage());
                app.imprimirBuffer();

            } catch(EstarPenalizadoException penalizadoExcp){

                Output.errorComando(penalizadoExcp.getMessage());
                getApp().getJuego().getTurno().getAvatar().getTablero().getJuego().setHaLanzadoDados(true);
                app.imprimirBuffer();

            } catch (MonopolyETSEException e) {
                Output.errorComando(e.getMessage());
                app.imprimirBuffer();
            }
        }
    }
}
