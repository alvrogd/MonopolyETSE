package aplicacionGUI.editor.handlers;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.Celda;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.Carcel;

public class CrearCasilla implements EventHandler<ActionEvent> {

    /* Atributos */

    // Celda asociada al handler
    private final Celda celda;

    // Tipo de casilla a crear
    private final TipoCasilla tipoCasilla;



    /* Constructor */

    /**
     * Se crea un handler para la creación de un tipo de casilla especificado en la celda asociada
     * @param celda celda a asociar
     * @param tipoCasilla tipo de la casilla a crear
     */
    public CrearCasilla(Celda celda, TipoCasilla tipoCasilla){

        if( celda == null ) {
            System.out.println("Celda no inicializada");
            System.exit(1);
        }

        if( tipoCasilla == null ) {
            System.out.println("Tipo de casilla no inicializado");
            System.exit(1);
        }

        this.celda = celda;
        this.tipoCasilla = tipoCasilla;
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
     * Se crea el tipo de casilla especificado en la celda asociada
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se crea la representación de la casilla junto con esta última
        final Celda celda = getCelda();

        celda.setCasillaGUI(new CasillaGUI(Celda.getTableroGUI(), celda.getNodo(), new Carcel("Cárcel",
                celda.getPosicionTablero(), Celda.getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));

        celda.getEditor().actualizarNumeroCasillas(TipoCasilla.carcel, celda.getPosicionTablero(), 1);
    }
}
