package aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.TipoFase;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class FaseSeleccion extends Fase {

    /* Constructor */

    /**
     * Se crea la fase de selección de tablero
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     */
    public FaseSeleccion(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, "fases.jpg");
    }



    /* Métodos */

    /**
     * Se inicializa la fase de selección de tablero
     */
    public void iniciar() {


        // Se define la acción ante una combinación de teclas
        getEscena().setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {

                getAplicacionGUI().setTipoFase(TipoFase.creacionTablero);
                getAplicacionGUI().ejecutarFase(getAplicacionGUI().getTipoFase());
            }
        });

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

            getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
            getGc().setStroke(Color.TRANSPARENT);
            getGc().setFill(Color.BLACK);
            getGc().setTextAlign(TextAlignment.CENTER);
            getGc().setLineWidth(1);

            // Se añade el nombre de la casilla (la posición es la parte central inferior)
            getGc().fillText("Selección", 100, 100);
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
