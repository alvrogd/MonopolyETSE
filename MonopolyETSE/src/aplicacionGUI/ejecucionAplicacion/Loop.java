package aplicacionGUI.ejecucionAplicacion;

import javafx.animation.AnimationTimer;

public class Loop extends AnimationTimer  {

    /* Atributos */

    // Fase asociada
    private IFase fase;

    // Tiempo de inicio
    private final long tiempoInicio;



    /* Constructor */

    /**
     * Se crea un animation timer que gestione el bucle de ejecución de una ejecución del editor
     *
     * @param fase fase con la que iniciar el loop
     */
    public Loop(IFase fase) {

        if (fase == null) {
            System.err.println("Fase no inicializada");
            System.exit(1);
        }

        this.fase = fase;

        // Se registra el tiempo de inicio
        this.tiempoInicio = System.nanoTime();
    }



    /* Getters y setters */

    public IFase getFase() {
        return fase;
    }

    public void setFase(IFase fase) {
        this.fase = fase;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }



    /* Métodos */

    /**
     * Se inicializa el animation timer
     */
    public void iniciar() {

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

        getFase().render(t);
    }
}
