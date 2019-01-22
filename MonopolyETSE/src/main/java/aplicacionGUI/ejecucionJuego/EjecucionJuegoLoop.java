package aplicacionGUI.ejecucionJuego;

import aplicacionGUI.ConstantesGUI;
import javafx.animation.AnimationTimer;

public class EjecucionJuegoLoop extends AnimationTimer {

    /* Atributos */

    // Ejecución del juego asociada
    private final EjecucionJuego ejecucionJuego;

    // Tiempo de inicio
    private long tiempoInicio;



    /* Constructor */

    /**
     * Se crea un animation timer que gestione el bucle de ejecución de una ejecución del juego
     *
     * @param ejecucionJuego ejecución del juego asociada
     */
    public EjecucionJuegoLoop(EjecucionJuego ejecucionJuego) {

        if (ejecucionJuego == null) {
            System.err.println("Ejecución del juego no inicializada");
            System.exit(1);
        }

        this.ejecucionJuego = ejecucionJuego;

        // Se registra el momento de creación
        this.tiempoInicio = System.nanoTime();
    }



    /* Getters y setters */

    public EjecucionJuego getEjecucionJuego() {
        return ejecucionJuego;
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
        getEjecucionJuego().getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

        // Se renderizan los elementos
        getEjecucionJuego().getGc().drawImage(getEjecucionJuego().getFondo(), 0, 0);
        getEjecucionJuego().getInformacion().render(t);
        getEjecucionJuego().getMenuGUI().render(t);

        // Si existe algún input activo, se renderiza el primero
        if (getEjecucionJuego().getInputsActivos().size() > 0) {
            getEjecucionJuego().getInputsActivos().get(0).render();
        }
    }
}
