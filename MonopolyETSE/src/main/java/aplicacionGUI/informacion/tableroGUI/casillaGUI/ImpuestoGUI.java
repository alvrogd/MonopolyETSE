package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.tablero.jerarquiaCasillas.Impuesto;

public class ImpuestoGUI extends CasillaGUI {

    /* Constructor */

    /**
     * Se crea una representación de una propiedad
     *
     * @param tableroGUI          representación de un tablero asociada a la representación de una casilla
     * @param raiz                nodo sobre el cual crear un hijo para la representación de la casilla
     * @param impuesto            casilla de impuesto a representar
     * @param ficheroFondo        imagen de fondo de la casilla a representar
     * @param posicionX           posición (coordenada X) de la representación de la casilla en la representación del
     *                            tablero
     * @param posicionY           posición (coordenada Y) de la representación de la casilla en la representación del
     *                            tablero
     * @param perteneceTableroGUI si pertenece a una representación de un tablero
     */
    public ImpuestoGUI(TableroGUI tableroGUI, Group raiz, Impuesto impuesto, String ficheroFondo, int posicionX,
                       int posicionY, boolean perteneceTableroGUI) {

        super(tableroGUI, raiz, impuesto, ficheroFondo, posicionX, posicionY, perteneceTableroGUI);
    }



    /* Métodos */

    /**
     * Se devuelve la casilla de impuesto asociada
     *
     * @return propiedad asociada
     */
    public Impuesto getImpuesto() {

        return ((Impuesto) getCasilla());
    }

    /**
     * Se renderiza el contenido (avatares contenidos e importe del impuesto)
     *
     * @param t tiempo transcurrido
     */
    @Override
    public void renderContenido(double t) {

        super.renderContenido(t);
        renderImpuesto();
    }

    /**
     * Se muestra el importe del impuesto asociado a la casilla
     */
    public void renderImpuesto() {

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.BOLD, 12));
        getGc().setLineWidth(1);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);

        getGc().fillText("Imp.: " + getImpuesto().getImpuesto() + " K €", 5, 59);
    }
}
