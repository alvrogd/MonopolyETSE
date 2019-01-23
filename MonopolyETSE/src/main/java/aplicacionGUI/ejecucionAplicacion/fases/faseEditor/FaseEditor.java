package aplicacionGUI.ejecucionAplicacion.fases.faseEditor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.Editor;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.TipoFase;
import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.handlers.ClickIzquierdo;
import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.handlers.Pulsacion;
import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.handlers.Release;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class FaseEditor extends Fase {

    /* Atributos */

    // Editor del tablero
    private Editor editor;

    // Si la edición ha finalizado
    private boolean edicionFinalizada;



    /* Constructor */

    /**
     * Se crea una instancia que ofrece en ventana un editor para el tablero del Monopoly
     *
     * @param aplicacionGUI aplicación gráfica asociada
     */
    public FaseEditor(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, "fases.jpg");

        // Inicialmente, la edición no ha finalizado y no se ha iniciado el editor
        this.edicionFinalizada = false;
    }



    /* Getters y setters */

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public boolean isEdicionFinalizada() {
        return edicionFinalizada;
    }

    public void setEdicionFinalizada(boolean edicionFinalizada) {
        this.edicionFinalizada = edicionFinalizada;
    }



    /* Métodos */

    /**
     * Se inicia el editor del tablero
     */
    public void iniciar() {

        // Se crea un editor
        setEditor(new Editor(getAplicacionGUI(), getRaiz()));

        // Se define la acción al realizar un click izquierdo
        getEscena().setOnMouseClicked(new ClickIzquierdo());

        // Se define la acción al presionar un botón del ratón
        getEscena().setOnMousePressed(new Pulsacion(this));

        // Se define la acción al soltar un botón del ratón
        getEscena().setOnMouseReleased(new Release(this));

        // Se define la acción ante una combinación de teclas
        getEscena().setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {

                getAplicacionGUI().setTipoFase(TipoFase.inicioJuego);
                getAplicacionGUI().ejecutarFase(getAplicacionGUI().getTipoFase());
            }
        });

        // Se indica que se ha inicializado
        setIniciado(true);
    }

    /**
     * Se renderiza el editor del tablero
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        // Se limpia la ventana
        clear();

        // Si se ha inicializado
        if (isIniciado()) {

            // Se renderizan los elementos
            getGc().drawImage(getFondo(), 0, 0);
            getEditor().render(t);

            // Si existe algún input activo, se renderiza el primero
            if (getInputsActivos().size() > 0) {
                getInputsActivos().get(0).render();
            }
        }
    }

    /**
     * Se limpia el GC del editor del tablero
     */
    public void clear() {

        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

        // Se limpia el editor
        getEditor().clear();
    }
}
