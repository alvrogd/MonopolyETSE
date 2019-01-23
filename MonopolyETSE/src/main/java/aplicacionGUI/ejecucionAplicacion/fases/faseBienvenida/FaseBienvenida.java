package aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida.handlers.Tecla;

public class FaseBienvenida extends Fase {

    /* Constructor */

    /**
     * Se crea la fase de bienvenida
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     */
    public FaseBienvenida(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, ConstantesGUI.FASE_BIENVENIDA_FONDO);
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

            getGc().drawImage(getFondo(), 0, 0);
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
