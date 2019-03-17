package aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.handlers.Pulsacion;
import aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.handlers.Release;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.shape.Rectangle;
import resources.fases.ImagenesFases;

public class FaseSeleccion extends Fase {

    /* Atributos */

    // Imágenes por las que cambiar el fondo de la fase
    private final Image editorSeleccionado = new Image(ImagenesFases.class.getResource(
            ConstantesGUI.FASE_SELECCION_EDITOR).toString());
    private final Image predeterminadoSeleccionado = new Image(ImagenesFases.class.getResource(
            ConstantesGUI.FASE_SELECCION_PREDETERMINADO).toString());

    // Fondo seleccionado
    private Image fondoSeleccionado;

    // Sensores para cada opción
    private final Rectangle sensorEditor;
    private final Rectangle sensorPredeterminado;

    // Sonido para los botones
    private final Media sonido = new Media(resources.sonidos.Sonidos.class.getResource(
            ConstantesGUI.SONIDO_BOTON).toString());



    /* Constructor */

    /**
     * Se crea la fase de selección de tablero
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     */
    public FaseSeleccion(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, ConstantesGUI.FASE_SELECCION_FONDO);

        // Inicialmente, se establece el fondo sin selección
        this.fondoSeleccionado = super.getFondo();

        // Se crean los sensores
        this.sensorEditor = new Rectangle(ConstantesGUI.FASE_SELECCION_EDITOR_DESPLAZAMIENTO_X,
                ConstantesGUI.FASE_SELECCION_EDITOR_DESPLAZAMIENTO_Y, ConstantesGUI.FASE_SELECCION_BOTON_ANCHO,
                ConstantesGUI.FASE_SELECCION_BOTON_ALTO);
        this.sensorPredeterminado = new Rectangle(ConstantesGUI.FASE_SELECCION_PREDETERIMADO_DESPLAZAMIENTO_X,
                ConstantesGUI.FASE_SELECCION_PREDETERIMADO_DESPLAZAMIENTO_Y, ConstantesGUI.FASE_SELECCION_BOTON_ANCHO,
                ConstantesGUI.FASE_SELECCION_BOTON_ALTO);
    }



    /* Getters y setters */

    public Image getEditorSeleccionado() {
        return editorSeleccionado;
    }

    public Image getPredeterminadoSeleccionado() {
        return predeterminadoSeleccionado;
    }

    public Image getFondoSeleccionado() {
        return fondoSeleccionado;
    }

    public void setFondoSeleccionado(Image fondoSeleccionado) {
        this.fondoSeleccionado = fondoSeleccionado;
    }

    public Rectangle getSensorEditor() {
        return sensorEditor;
    }

    public Rectangle getSensorPredeterminado() {
        return sensorPredeterminado;
    }

    public Media getSonido() {
        return sonido;
    }



    /* Métodos */

    /**
     * Se inicializa la fase de selección de tablero
     */
    public void iniciar() {


        // Se define la acción ante una pulsación de un botón del ratón
        getEscena().setOnMousePressed(new Pulsacion(this));

        // Se define la acción ante un release de un botón del ratón
        getEscena().setOnMouseReleased(new Release(this));

        setIniciado(true);
    }

    /**
     * Se renderiza la fase de selección de tablero
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
     * Se limpia el GC de la fase de selección de tablero
     */
    public void clear() {
        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);
    }
}
