package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.excepciones.EdificiosSolarException;
import monopoly.jugadores.excepciones.ImposibleCambiarModoException;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.Tablero;

public class Sombrero extends Avatar {

    /* Atributos */

    // Se establece al cambiar a modo avanzado si el movimiento se efectuará hacia la izquierda o hacia la derecha
    private boolean moverseANorte;



    /* Constructor */

    /**
     * Constructor que crea un avatar de la clase Sombrero para un jugador
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Sombrero(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        super(jugador, tablero, casillaInicial);
    }



    /* Getters y setters */

    public boolean isMoverseANorte() {
        return moverseANorte;
    }

    public void setMoverseANorte(boolean moverseANorte) {
        this.moverseANorte = moverseANorte;
    }



    /* Métodos */

    /**
     * Se comprueba si el avatar puede cambiar su modo de movimiento
     *
     * @return si el avatar puede cambiar el modo de movimiento
     */
    public boolean noPoderCambiarMovimiento(boolean forzar) throws ImposibleCambiarModoException {

        // Si está en movimiento avanzado y ya ha lanzado los dados
        if( !isMovimientoEstandar() && getTablero().getJuego().isHaHechoUnaTirada() )
            throw new ImposibleCambiarModoException("No puede cambiarse el modo de movimiento después de haber " +
                    "tirado los dados en el modo avanzado");

        return (false);
    }


    /**
     * Se comprueba, en función de la tirada obtenida, si no es posible realizar otra nueva tirada más tarde
     * @param primeraTirada valor del primer dado
     * @param segundaTirada valor del segundo dado
     * @return              si no es posible realizar otra tirada más
     */
    @Override
    public boolean noMasTiradas(int primeraTirada, int segundaTirada) {

        if (isMovimientoEstandar())
            return (super.noMasTiradas(primeraTirada, segundaTirada));

        else
            // Si no ha sacado un 4 o más o ha hecho el máximo de tiradas en un turno
            return (getJugador().getTiradasEnTurno() >= 3 || (primeraTirada + segundaTirada) < 4);
    }

    /**
     * Se calcula la posición de la casilla a la que un avatar debe moverse, dado un número de casillas a avanzar; el
     * funcionamiento depende del modo de movimiento establecido actualmente
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla destino
     */
    public int calcularNuevaPosicion(int numeroCasillas) throws EdificiosSolarException {

        // Si el jugador se encuentra en movimiento estándar, se avanza con normalidad
        if (isMovimientoEstandar())
            return (actualizarPosicionNormal(numeroCasillas));

            // En caso contrario
        else
            return (actualizarPosicionEsfinge(numeroCasillas,false));
    }

    /**
     * @return si el avatar se encuentra en la fila norte o sur
     */
    private boolean estarNorteSur(){

        int fila = getPosicion().getPosicionEnTablero()/10;
        boolean salida = false;

        if(fila == Constantes.OESTE || fila == Constantes.ESTE){
            salida = true;
        }

        return salida;
    }


    private int actualizarPosicionZigZag(int numeroCasillas){

        int posicion = getPosicion().getPosicionEnTablero()%10;
        int linea = getPosicion().getPosicionEnTablero()/10;

        if(linea == Constantes.OESTE){
            linea = Constantes.ESTE;
        } else {
            linea = Constantes.OESTE;
        }

        posicion = 10 - numeroCasillas - posicion;

        return(linea*10+posicion);

    }

    /**
     * Se actualiza la posición del avatar empleando el movimiento avanzado de una esfinge
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    public int actualizarPosicionEsfinge(int numeroCasillas, boolean recursivo) throws EdificiosSolarException {

        if (numeroCasillas >= 4 || recursivo) {

            int linea = getPosicion().getPosicionEnTablero()/10;
            int posicion = getPosicion().getPosicionEnTablero()%10;

            if(!estarNorteSur()){

                linea++;
                linea %= 4;
                posicion = 1;
                numeroCasillas--;

            } else {

                if(numeroCasillas < 10 - posicion){

                    int posicionNueva;

                    if(numeroCasillas % 2 == 0){
                        posicionNueva = actualizarPosicionNormal(numeroCasillas);
                        posicion = posicionNueva%10;
                        linea = posicionNueva/10;
                    } else {
                        posicionNueva = actualizarPosicionZigZag(numeroCasillas);
                        posicion = posicionNueva%10;
                        linea = posicionNueva/10;
                    }
                    numeroCasillas = 0;

                } else {

                    numeroCasillas -= 10-posicion;

                    if((10-posicion) % 2 != 0){
                        if(linea == Constantes.OESTE){
                            linea = Constantes.ESTE+1;
                            linea %= 4;
                        } else if (linea == Constantes.ESTE){
                            linea = Constantes.OESTE+1;
                            linea %= 4;
                        }
                    }

                    posicion = 0;

                }

            }

            getPosicion().getAvataresContenidos().remove(getIdentificador());
            setPosicion(getTablero().getCasillas().get(linea).get(posicion));
            getPosicion().getAvataresContenidos().put(getIdentificador(), this);

            if(numeroCasillas == 0){
                setCasillasRestantesPorMoverse(0);
                return(getPosicion().getPosicionEnTablero());
            }

            return(actualizarPosicionEsfinge(numeroCasillas,true));
        }

        // Si se ha sacado menos de un 4, se deshacen las compras de propiedades y edificios, y se eliminan los pagos
        // obtenidos por premios en el último turno
        else {
            getJugador().revertirAcciones();
            Output.respuesta( "Ohoh! Se han deshecho las acciones que te convienen de la última tirada :(");
            setCasillasRestantesPorMoverse(0);
            return (getPosicion().getPosicionEnTablero());
        }
    }
}
