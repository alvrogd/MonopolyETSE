package aplicacionGUI.editor;

import javafx.scene.image.Image;
import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.InformacionCasilla;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;

public class InformacionCasillaGUI extends InformacionCasilla {

    /* Atributos */

    private final Image fondo;



    /* Constructores */

    public InformacionCasillaGUI(TipoCasilla tipoCasilla, String nombre, Image fondo ) {
        this(tipoCasilla, nombre, null, 0, fondo);
    }

    public InformacionCasillaGUI(TipoCasilla tipoCasilla, String nombre, Grupo grupo, int importe, Image fondo ) {

        super( tipoCasilla, nombre, grupo, importe);

        if( fondo == null ) {
            System.out.println("Imagen de fondo no inicializada");
            System.exit(1);
        }

        this.fondo = fondo;
    }



    /* Getters y setters */

    public Image getFondo() {
        return fondo;
    }
}
