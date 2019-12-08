package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.TipoFase;
import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers.ClickIzquierdo;
import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers.Pulsacion;
import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers.Release;
import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers.Tecla;

import java.util.ArrayList;
import java.util.HashMap;

public class FaseJugador extends Fase {

    // ArrayList con los botones que pueden aparecer
    private ArrayList<BotonFase> botones;

    // ArrayList con los botones actuales a mostrar en pantalla
    private ArrayList<BotonFase> botonesActuales;

    // HashMap donde se almacena un ArrayList de botones a los que se accede a partir de una función raíz
    private HashMap<TipoFuncionFase, ArrayList<BotonFase>> botonesPagina;

    public FaseJugador(AplicacionGUI aplicacionGUI) {
        super(aplicacionGUI, "introducirJugadores.png");
        this.botones = new ArrayList<>();
        this.botonesActuales = new ArrayList<>();
        this.botonesPagina = new HashMap<>();
        crearBotones();
    }

    public void crearBotones() {
        for (TipoFuncionFase funcion : TipoFuncionFase.values()) {

            BotonFase boton = new BotonFase(this, getRaiz(), funcion.toString(), funcion, funcion.getPosicionX(), funcion.getPosicionY());

            getBotones().add(boton);

            if (funcion.isPagina()) {
                // En caso de que no contenga la clave se crea el ArrayList
                if (!getBotonesPagina().containsKey(funcion.getFuncionRaiz())) {
                    getBotonesPagina().put(funcion.getFuncionRaiz(), new ArrayList<>());
                }

                getBotonesPagina().get(funcion.getFuncionRaiz()).add(boton);

            }
        }
    }

    public HashMap<TipoFuncionFase, ArrayList<BotonFase>> getBotonesPagina() {
        return botonesPagina;
    }

    public void setBotonesPagina(HashMap<TipoFuncionFase, ArrayList<BotonFase>> botonesPagina) {
        this.botonesPagina = botonesPagina;
    }

    public ArrayList<BotonFase> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<BotonFase> botones) {
        this.botones = botones;
    }

    public ArrayList<BotonFase> getBotonesActuales() {
        return botonesActuales;
    }

    public void setBotonesActuales(ArrayList<BotonFase> botonesActuales) {
        this.botonesActuales = botonesActuales;
    }

    public void iniciar() {

        // Se define la acción ante un click izquierdo
        getEscena().setOnMouseClicked(new ClickIzquierdo(this));

        // Se define la acción al presionar un botón del ratón
        getEscena().setOnMousePressed(new Pulsacion(this));

        // Se define la acción al soltar un botón del ratón
        getEscena().setOnMouseReleased(new Release(this));

        // Se define un gestor vacío para pulsaciones de teclas
        getEscena().setOnKeyPressed(new Tecla());

        // Se busca el botón de añadir jugadores y se inicia
        for (BotonFase boton : getBotones()) {
            if (boton.getFuncion().equals(TipoFuncionFase.anadirJugador)) {
                boton.setActivo(true);
            }
        }

        setIniciado(true);
    }

    public void finalizar() {

        // Este método para que no haya problemas debería poner todos los botones con su activo a false.
        for (BotonFase boton : getBotones()) {
            boton.setActivo(false);
        }

        getAplicacionGUI().setTipoFase(TipoFase.seleccionTablero);
        getAplicacionGUI().ejecutarFase(getAplicacionGUI().getTipoFase());
    }

    public void handlerIzquierdo(double x, double y) {
        for (BotonFase boton : getBotonesActuales()) {
            if (boton.contienePosicion(x, y)) {
                boton.handleClickIzquierdo(x, y);
            }
        }
    }

    public void handlerPulsado(double x, double y) {
        for (BotonFase boton : getBotonesActuales()) {
            if (boton.contienePosicion(x, y)) {
                boton.handleClickPulsado(x, y);
            }
        }
    }

    public void handlerRelease(double x, double y) {
        for (BotonFase boton : getBotonesActuales()) {
            if (boton.contienePosicion(x, y)) {
                boton.handleClickSoltado(x, y);
            }
        }
    }

    public void actualizarBotones() {

        setBotonesActuales(new ArrayList<>());

        for (BotonFase boton : getBotones()) {

            if (boton.isActivo()) {
                getBotonesActuales().add(boton);
            } else {
                getGc().clearRect(0, 0, ConstantesGUI.BOTONFASE_ANCHO, ConstantesGUI.BOTONFASE_ALTO);
            }

        }

    }

    public void render(double t) {

        actualizarBotones();

        getGc().drawImage(getFondo(), 0, 0);

        for (BotonFase boton : getBotonesActuales()) {
            boton.render();
        }

        // Si existe algún input activo, se renderiza el primero
        if (getInputsActivos().size() > 0) {
            getInputsActivos().get(0).render();
        }
    }

    public void clear() {

    }

}
