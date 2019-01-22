package aplicacionGUI.ejecucionEditor;

import aplicacionGUI.ConstantesGUI;
import javafx.animation.AnimationTimer;

public class EjecucionEditorLoop extends AnimationTimer {

    /* Atributos */

    // Ejecución del editor asociada
    private final EjecucionEditor ejecucionEditor;

    // Tiempo de inicio
    private long tiempoInicio;



    /* Constructor */

    /**
     * Se crea un animation timer que gestione el bucle de ejecución de una ejecución del editor
     *
     * @param ejecucionEditor ejecución del editor asociada
     */
    public EjecucionEditorLoop(EjecucionEditor ejecucionEditor) {

        if (ejecucionEditor == null) {
            System.err.println("Ejecución del editor no inicializada");
            System.exit(1);
        }

        this.ejecucionEditor = ejecucionEditor;
    }



    /* Getters y setters */

    public EjecucionEditor getEjecucionEditor() {
        return ejecucionEditor;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }



    /* Métodos */

    /**
     * Se inicializa el animation timer
     */
    public void iniciar() {

        // Se registra el momento de inicio
        setTiempoInicio(System.nanoTime());

        // Se inicia
        start();
    }

    /**
     * Se realizan las acciones correspondientes a un ciclo de ejecución del animation timer
     *
     * @param currentNanoTime tiempo del sistema correspondiente al ciclo de ejecución del animation timer
     */
    @Override
    public void handle(long currentNanoTime) {

        // Tiempo que ha transcurrido desde el inicio
        double t = (currentNanoTime - getTiempoInicio()) / 1000000000.0;

        // Se limpia la ventana
        getEjecucionEditor().getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

        // Se renderizan los elementos
        getEjecucionEditor().getGc().drawImage(getEjecucionEditor().getFondo(), 0, 0);
        getEjecucionEditor().getEditor().render(t);

        // Si existe algún input activo, se renderiza el primero
        if (getEjecucionEditor().getInputsActivos().size() > 0) {
            getEjecucionEditor().getInputsActivos().get(0).render();
        }
    }
}
