package aplicacionGUI.editor.handlers;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.Celda;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.ImpuestoGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.PropiedadGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.SolarGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import monopoly.jugadores.Banca;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.*;

public class CrearCasilla implements EventHandler<ActionEvent> {

    /* Atributos */

    // Celda asociada al handler
    private final Celda celda;

    // Tipo de casilla a crear
    private final TipoCasilla tipoCasilla;

    // Grupo asociado a la casilla a crear
    private final Grupo grupo;



    /* Constructor */

    /**
     * Se crea un handler para la creación de un tipo de casilla especificado en la celda asociada
     *
     * @param celda       celda a asociar
     * @param tipoCasilla tipo de la casilla a crear
     */
    public CrearCasilla(Celda celda, TipoCasilla tipoCasilla) {
        this(celda, tipoCasilla, null);
    }

    /**
     * Se crea un handler para la creación de un tipo de casilla y grupo especificados en la celda asociada
     *
     * @param celda       celda a asociar
     * @param tipoCasilla tipo de la casilla a crear
     * @param grupo       grupo asociado a la casilla de crear
     */
    public CrearCasilla(Celda celda, TipoCasilla tipoCasilla, Grupo grupo) {

        if (celda == null) {
            System.out.println("Celda no inicializada");
            System.exit(1);
        }

        if (tipoCasilla == null) {
            System.out.println("Tipo de casilla no inicializado");
            System.exit(1);
        }

        this.celda = celda;
        this.tipoCasilla = tipoCasilla;
        this.grupo = grupo;
    }



    /* Getters y setters */

    public Celda getCelda() {
        return celda;
    }

    public TipoCasilla getTipoCasilla() {
        return tipoCasilla;
    }

    public Grupo getGrupo() {
        return grupo;
    }



    /* Métodos */

    /**
     * Se crea el tipo de casilla especificado en la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se crea la representación de la casilla junto con esta última
        final Celda celda = getCelda();
        final TableroGUI tableroGUI = Celda.getTableroGUI();
        final Group nodo = celda.getNodo();
        final int posicionTablero = celda.getPosicionTablero();
        final Tablero tablero = Celda.getTablero();
        final Banca banca = Celda.getBanca();
        final Grupo grupo = getGrupo();

        switch (getTipoCasilla()) {

            case carcel:
                celda.setCasillaGUI(new CasillaGUI(tableroGUI, nodo, new Carcel("Cárcel", posicionTablero,
                        Celda.getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case irCarcel:
                celda.setCasillaGUI(new CasillaGUI(tableroGUI, nodo, new IrCarcel("Ir a la cárcel",
                        posicionTablero, tablero), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case parking:
                celda.setCasillaGUI(new CasillaGUI(tableroGUI, nodo, new Parking("Parking",
                        posicionTablero, tablero), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case comunidad:
                celda.setCasillaGUI(new CasillaGUI(tableroGUI, nodo, new ComunidadCasilla("Casilla Comunidad",
                        posicionTablero, tablero), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case impuesto:
                celda.setCasillaGUI(new ImpuestoGUI(tableroGUI, nodo, new Impuesto("Casilla Impuesto",
                        posicionTablero, tablero, 0), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case servicio:
                celda.setCasillaGUI(new PropiedadGUI(tableroGUI, nodo, new Servicio("Casilla Servicio",
                        grupo, true, posicionTablero, banca, tablero),
                        ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case solar:
                celda.setCasillaGUI(new SolarGUI(tableroGUI, nodo, new Solar("Casilla Solar",
                        grupo, true, posicionTablero, banca, tablero),
                        ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case suerte:
                celda.setCasillaGUI(new CasillaGUI(tableroGUI, nodo, new SuerteCasilla("Casilla Suerte",
                        posicionTablero, tablero), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;

            case transporte:
                celda.setCasillaGUI(new PropiedadGUI(tableroGUI, nodo, new Transporte("Casilla Transporte",
                        grupo, true, posicionTablero, banca, tablero),
                        ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0, false));
                break;
        }

        // Se actualiza el número de casillas existentes del tipo correspondiente
        celda.getEditor().actualizarNumeroCasillas(getTipoCasilla(), posicionTablero, 1);
    }
}
