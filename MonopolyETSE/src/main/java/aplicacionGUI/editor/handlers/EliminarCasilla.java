package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.tablero.jerarquiaCasillas.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.*;

public class EliminarCasilla implements EventHandler<ActionEvent> {

    /* Atributos */

    // Celda asociada al handler
    private final Celda celda;

    // Tipo de casilla a eliminar
    private final TipoCasilla tipoCasilla;



    /* Constructor */

    /**
     * Se crea un handler para la eliminación de un tipo de casilla especificado en la celda asociada
     *
     * @param celda   celda a asociar
     * @param casilla casilla a eliminar
     */
    public EliminarCasilla(Celda celda, Casilla casilla) {

        if (celda == null) {
            System.out.println("Celda no inicializada");
            System.exit(1);
        }

        if (casilla == null) {
            System.out.println("Casilla no inicializado");
            System.exit(1);
        }

        this.celda = celda;

        // Se obtiene el tipo de casilla correspondiente a la casilla dada
        if (casilla instanceof Propiedad) {

            if (casilla instanceof Servicio) {
                this.tipoCasilla = TipoCasilla.servicio;
            } else if (casilla instanceof Solar) {
                this.tipoCasilla = TipoCasilla.solar;
            } else {
                this.tipoCasilla = TipoCasilla.transporte;
            }
        } else if (casilla instanceof Accion) {

            if (casilla instanceof Especial) {

                if (casilla instanceof Carcel) {
                    this.tipoCasilla = TipoCasilla.carcel;
                } else if (casilla instanceof IrCarcel) {
                    this.tipoCasilla = TipoCasilla.irCarcel;
                } else if (casilla instanceof Parking) {
                    this.tipoCasilla = TipoCasilla.parking;
                } else {
                    this.tipoCasilla = TipoCasilla.salida;
                }
            } else if (casilla instanceof ComunidadCasilla) {
                this.tipoCasilla = TipoCasilla.comunidad;
            } else {
                this.tipoCasilla = TipoCasilla.suerte;
            }
        } else {
            this.tipoCasilla = TipoCasilla.impuesto;
        }
    }



    /* Getters y setters */

    public Celda getCelda() {
        return celda;
    }

    public TipoCasilla getTipoCasilla() {
        return tipoCasilla;
    }



    /* Métodos */

    /**
     * Se elimina el tipo de casilla especificado en la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se actualiza el número de casillas existentes del tipo correspondiente
        getCelda().getEditor().actualizarNumeroCasillas(getTipoCasilla(), getCelda().getPosicionTablero(), -1);
        // Se limpia el gc de la representación de la casilla
        getCelda().getCasillaGUI().clear();
        // Se borra la representación
        getCelda().setCasillaGUI(null);
    }
}
