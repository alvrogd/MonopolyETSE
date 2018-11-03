package aplicacion;

import aplicacion.salidaPantalla.Output;
import monopoly.Juego;
import monopoly.jugadores.Jugador;

import java.util.Scanner;

public class Aplicacion {

    private Juego juego;

    public Aplicacion(){

        juego = new Juego();

    }

    public void introducirComando(Jugador jugador){

        Scanner entrada = new Scanner(System.in);
        String comando;

        Output.imprimirCabeceraJugador(jugador);
        Output.imprimirEntradaComando();

        comando = entrada.nextLine();

        switch(comando){
            case TipoComando.crearJugador.getComando():
                crearJugador();

        }
    }

    private void crearJugador(){



    }

}


