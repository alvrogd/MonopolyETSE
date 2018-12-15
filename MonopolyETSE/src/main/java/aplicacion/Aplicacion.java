package aplicacion;

import aplicacion.salidaPantalla.Output;
import aplicacion.salidaPantalla.TableroASCII;
import aplicacion.salidaPantalla.TipoColor;
import monopoly.Juego;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.*;
import monopoly.tablero.cartas.Carta;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Edificio;
import monopoly.tablero.jerarquiaCasillas.Grupo;

import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion {

    private Juego juego;
    private Menu menu;
    private ArrayList<ArrayList<Object>> buffer;
    private boolean haLanzadoDados;

    /*Constructores*/

    /**
     * Constructor que crea el juego, el menú y el buffer.
     */
    public Aplicacion() {

        juego = new Juego();
        menu = new Menu(this);
        buffer = new ArrayList<>();

        Output.setBuffer(buffer);

    }

    /*Getters*/
    public Juego getJuego() {
        return juego;
    }

    public Menu getMenu() {
        return menu;
    }

    public ArrayList<ArrayList<Object>> getBuffer() {
        return buffer;
    }

    public boolean isHaLanzadoDados() {
        return haLanzadoDados;
    }

    /*Métodos*/

    /**
     * Función para imprimir el buffer guardado en Output
     */
    public void imprimirBuffer() {

        //En caso de que el buffer se vaya a imprimir en el trablero y el juego esté iniciado
        if (Output.isImpresionTablero() && getJuego().isIniciado()) {
            System.out.print("\033[H\033[2J"); //se limpia la consola

            //Se imprime el tablero junto el buffer
            System.out.println(TableroASCII.pintaTablero(getJuego().getTablero(), Output.getBuffer()));
            Output.vaciarBuffer();

        } else {
            //En caso contrario se comprueba si el juego está iniciado
            int tam = Output.getBuffer().size();

            if (getJuego().isIniciado()) {
                System.out.print("\033[H\033[2J");
                //Si está iniciado se imprime el tablero sin nada en su interior
                System.out.println(TableroASCII.pintaTablero(getJuego().getTablero(), null));
            }

            //Se imprime el buffer
            for (int i = 0; i < tam; i++) {
                System.out.println(Output.getBuffer().get(i).get(0));
            }

            //Se vacía el buffer
            Output.vaciarBuffer();
        }

    }

    /**
     * Función para limpiar el tablero
     */
    public void limpiarTablero() {

        for (int i = 0; i < 50; i++)
            System.out.println();

    }


    private void ejecutarSuerte(){

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getJuego().getCartasSuerte().size();

        for(Carta carta : getJuego().getCartasSuerte()){

            System.out.println("("+count+") "+carta.toString());
            count++;

        }

        System.out.println("(*) Opción: ");
        opc = entrada.nextInt();

        getJuego().getTurno().leerCarta(getJuego().getCartasSuerte().get(opc%size));

    }

    private void ejecutarComunidad(){

        int count = 0, opc, size;
        Scanner entrada = new Scanner(System.in);

        size = getJuego().getCartasComunidad().size();

        for(Carta carta : getJuego().getCartasComunidad()){

            System.out.println("("+count+") "+carta.toString());
            count++;

        }

        System.out.println("(*) Opción: ");
        opc = entrada.nextInt();

        getJuego().getTurno().leerCarta(getJuego().getCartasComunidad().get(opc%size));

    }
}


