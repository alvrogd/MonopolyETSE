package aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida.handlers.Tecla;
import javafx.scene.image.Image;
import resources.fases.ImagenesFases;

public class FaseBienvenida extends Fase {

    /* Atributos */

    // Fondo alternativo
    private final Image fondoAlternativo = new Image(ImagenesFases.class.getResource(
            ConstantesGUI.FASE_BIENVENIDA_ALTERNATIVO).toString());

    // Fondo seleccionado
    private Image fondoSeleccionado;



    /* Constructor */

    /**
     * Se crea la fase de bienvenida
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     */
    public FaseBienvenida(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, ConstantesGUI.FASE_BIENVENIDA_FONDO);

        // Inicialmente, se establece el fondo normal
        this.fondoSeleccionado = super.getFondo();
    }



    /* Getters y setters */

    public Image getFondoAlternativo() {
        return fondoAlternativo;
    }

    public Image getFondoSeleccionado() {
        return fondoSeleccionado;
    }

    public void setFondoSeleccionado(Image fondoSeleccionado) {
        this.fondoSeleccionado = fondoSeleccionado;
    }



    /* Métodos */

    /**
     * Se inicializa la fase de bienvenida
     */
    public void iniciar() {

        // Se define la acción ante una combinación de teclas
        getEscena().setOnKeyPressed(new Tecla(this));

        setIniciado(true);
    }

    /**
     * Se renderiza la fase de bienvenida
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

        if (isIniciado()) {

            getGc().drawImage(getFondoSeleccionado(), 0, 0);
        }
    }

    /**
     * Se limpia el GC de la fase de bievenida
     */
    public void clear() {

        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);
    }
}
