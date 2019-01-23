package aplicacion;

import aplicacion.excepciones.AplicacionException;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacion.salidaPantalla.*;
import java.util.HashSet;

import aplicacionGUI.menuGUI.entrada.EntradaGUI;
import aplicacionGUI.menuGUI.registroGUI.ConsolaInterfaz;
import monopoly.Juego;
import monopoly.tablero.jerarquiaCasillas.InformacionCasilla;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;

import java.util.ArrayList;

public class Aplicacion {

    private Juego juego;
    private Menu menu;
    private ArrayList<ArrayList<Object>> buffer;
    private boolean haLanzadoDados;
    public static ConsolaNormal consola = new ConsolaNormal();

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

    /**
     * Constructor que crea el juego a partir de la información de unas casillas predefinidas.
     * @param informacionCasillas ArrayList con la información de las casillas.
     */
    public Aplicacion(ArrayList<InformacionCasilla> informacionCasillas) {

        juego = new Juego(informacionCasillas);
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

        //En caso de que el buffer se vaya a imprimir en el tablero y el juego esté iniciado
        if (Output.isImpresionTablero() && getJuego().isIniciado()) {
            System.out.print("\033[H\033[2J"); //se limpia la consola

            //Se imprime el tablero junto el buffer
            Aplicacion.consola.imprimir(TableroASCII.pintaTablero(getJuego().getTablero(), Output.getBuffer()));
            Output.vaciarBuffer();

        } else {
            //En caso contrario se comprueba si el juego está iniciado
            int tam = Output.getBuffer().size();

            if (getJuego().isIniciado()) {
                System.out.print("\033[H\033[2J");
                //Si está iniciado se imprime el tablero sin nada en su interior
                Aplicacion.consola.imprimir(TableroASCII.pintaTablero(getJuego().getTablero(), null));
            }

            //Se imprime el buffer
            for (int i = 0; i < tam; i++) {
                Aplicacion.consola.imprimir(Output.getBuffer().get(i).get(0).toString());
            }

            //Se vacía el buffer
            Output.vaciarBuffer();
        }

    }

    public void introducirComando(String linea) throws MonopolyETSEException {

        Comando comando = new Comando(linea,this);

        comando.ejecutarComando();

        imprimirBuffer();

    }

    /**
     * Función para limpiar el tablero
     */
    public void limpiarTablero() {

        for (int i = 0; i < 50; i++)
            Aplicacion.consola.imprimir("");

    }

}


