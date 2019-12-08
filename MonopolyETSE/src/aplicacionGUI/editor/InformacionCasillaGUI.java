package aplicacionGUI.editor;

import javafx.scene.image.Image;
import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.InformacionCasilla;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;

public class InformacionCasillaGUI extends InformacionCasilla {

    /* Atributos */

    // ImagenesFases de la representación de la casilla
    private final Image fondo;



    /* Constructores */

    /**
     * Se crea un contenedor de información personalizada sobre una representación de una casilla y de esta
     *
     * @param tipoCasilla tipo de casilla al que corresponde la información
     * @param nombre      nombre de la casilla
     * @param fondo       imagen de fases que mostrar al representar la casilla
     */
    public InformacionCasillaGUI(TipoCasilla tipoCasilla, String nombre, Image fondo) {
        this(tipoCasilla, nombre, null, 0, fondo);
    }

    /**
     * Se crea un contenedor de información personalizada sobre una representación de una casilla y de esta
     *
     * @param tipoCasilla tipo de casilla al que corresponde la información
     * @param nombre      nombre de la casilla
     * @param grupo       grupo al que pertenece la casilla
     * @param importe     importe asociado a la casilla (precio de compra, impuesto...)
     * @param fondo       imagen de fases que mostrar al representar la casilla
     */
    public InformacionCasillaGUI(TipoCasilla tipoCasilla, String nombre, Grupo grupo, int importe, Image fondo) {

        super(tipoCasilla, nombre, grupo, importe);

        if (fondo == null) {
            System.out.println("Imagen de fases no inicializada");
            System.exit(1);
        }

        this.fondo = fondo;
    }



    /* Getters y setters */

    public Image getFondo() {
        return fondo;
    }
}
