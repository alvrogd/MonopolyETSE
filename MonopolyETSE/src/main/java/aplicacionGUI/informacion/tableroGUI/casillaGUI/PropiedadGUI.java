package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers.DeshipotecarPropiedad;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers.HipotecarPropiedad;
import aplicacionGUI.menuGUI.entrada.CambiarTurnoTrato;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Banca;
import monopoly.jugadores.tratos.Inmunidad;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;

import java.util.ArrayList;
import java.util.HashSet;

public class PropiedadGUI extends CasillaGUI {

    /* Constructor */

    /**
     * Se crea una representación de una propiedad
     *
     * @param tableroGUI          representación de un tablero asociada a la representación de una casilla
     * @param raiz                nodo sobre el cual crear un hijo para la representación de la casilla
     * @param propiedad           propiedad a representar
     * @param ficheroFondo        imagen de fondo de la casilla a representar
     * @param posicionX           posición (coordenada X) de la representación de la casilla en la representación del
     *                            tablero
     * @param posicionY           posición (coordenada Y) de la representación de la casilla en la representación del
     *                            tablero
     * @param perteneceTableroGUI si pertenece a una representación de un tablero
     */
    public PropiedadGUI(TableroGUI tableroGUI, Group raiz, Propiedad propiedad, String ficheroFondo, int posicionX,
                        int posicionY, boolean perteneceTableroGUI) {

        super(tableroGUI, raiz, propiedad, ficheroFondo, posicionX, posicionY, perteneceTableroGUI);
    }



    /* Métodos */

    /**
     * Se devuelve la propiedad asociada
     *
     * @return propiedad asociada
     */
    public Propiedad getPropiedad() {

        return ((Propiedad) getCasilla());
    }


    /**
     * Se ejecuta la acción definida ante un click izquierdo
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    @Override
    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if (getTableroGUI().getInformacion().getMenuGUI().isProponiendoTrato()) {
            handleProponiendoTratos(posicionX, posicionY);
        }
    }

    /**
     * Se ejecuta la acción definida ante la fase de proposición de tratos
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    public void handleProponiendoTratos(double x, double y) {

        // En caso de encontrarse en la fase del dinero, a la casilla no le incumbe y se corta el flujo
        if (getTableroGUI().getInformacion().getMenuGUI().isFaseDinero()) {
            return;
        }

        // En caso de estar en la fase de noalquiler
        if (getTableroGUI().getInformacion().getMenuGUI().isFaseNoAlquiler()) {
            handleNoAlquiler();
        }

        else {
            //En caso de que se estén dando casillas
            if (getTableroGUI().getInformacion().getMenuGUI().isEstarDandoEnTrato()) {
                handleEstarDando();
            } else {
                handleNoEstarDando();
            }
        }
    }

    /**
     * Se ejecuta la acción definida ante la fase de noalquiler
     */
    private void handleNoAlquiler() {

        final Propiedad propiedad = getPropiedad();

        // Se comprueba si pertenece al jugador que propone el traro
        if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())) {

            // Si se encuentra seleccionada
            if (isSeleccionada()) {

                int contador = 0;

                // En caso de estar ya seleccionada, es que se está deseleccionando, por lo que se quita la casilla
                for (Inmunidad inmunidad : getTableroGUI().getInformacion().getMenuGUI().getTrato().getInmunidades()) {

                    if (inmunidad.getPropiedad().equals(propiedad)) {
                        getTableroGUI().getInformacion().getMenuGUI().getTrato().getInmunidades().remove(contador);
                        break;
                    }

                    contador++;
                }

                // Se modifica la seleccion
                setSeleccionada(false);
            }

            // Si no se encuentra seleccionada, se añade
            else {

                Inmunidad inmunidad = new Inmunidad((Propiedad) getCasilla(), 0);

                getTableroGUI().getInformacion().getMenuGUI().getTrato().getInmunidades().add(inmunidad);
                new CambiarTurnoTrato(getTableroGUI().getInformacion().getMenuGUI().getTrato(), getTableroGUI().getInformacion().getMenuGUI(), inmunidad);
                setSeleccionada(true);
            }
        }
    }

    /**
     * Se ejecuta la acción definida ante la fase de dar
     */
    private void handleEstarDando() {

        final Propiedad propiedad = getPropiedad();

        // Se comprueba que la casilla sea del jugador que tiene el turno
        if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJuego().getTurno())) {

            if (isSeleccionada()) {

                // En caso de estar ya seleccionada, es que se está deseleccionando, por lo tanto se quita la casilla
                getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasDar().remove(getPropiedad());
                // Se modifica la selección
                setSeleccionada(false);
            }

            else {
                getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasDar().add((Propiedad) getCasilla());
                setSeleccionada(true);
            }
        }
    }

    /**
     * Se ejecuta la acción definida ante la fase alternativa a dar
     */
    public void handleNoEstarDando() {

        final Propiedad propiedad = getPropiedad();

        // Se comprueba que la propiedad seleccionada pertenezca al jugador
        if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())) {

            if (isSeleccionada()) {

                getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasRecibir().remove(propiedad);
                setSeleccionada(false);
            }

            else {
                getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasRecibir().add(propiedad);
                setSeleccionada(true);
            }
        }
    }

    /**
     * Se renderiza el contenido (avatares contenidos, propietario y casilla de selección)
     *
     * @param t tiempo transcurrido
     */
    @Override
    public void renderContenido(double t) {

        super.renderContenido(t);
        renderPropietario();

        // Si se encuentra en una representación de un tablero
        if( isPerteneceTableroGUI() ) {
            renderCasillaSeleccion();
        }
    }

    /**
     * Se renderiza el propietario o, en su defecto, el precio
     */
    public void renderPropietario() {

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);

        // Si el propietario es la banca, se añade el precio de la casilla
        if (getPropiedad().getPropietario() instanceof Banca) {

            getGc().fillText(getPropiedad().getPrecioActual() + " K €", 5, 58);
        }

        // En caso contrario, se indica el propietario
        else {

            // Se diferencia entre estar hipotecada o no
            if (getPropiedad().isHipotecada()) {

                getGc().fillText("Hipot.: " + getPropiedad().getPropietario().getNombre(), 5, 58);
            } else {

                getGc().fillText("Prop.: " + getPropiedad().getPropietario().getNombre(), 5, 58);
            }
        }
    }

    /**
     * Se renderiza la casilla de selección
     */
    public void renderCasillaSeleccion() {

        Propiedad propiedad = getPropiedad();

        if (getTableroGUI().getInformacion().getMenuGUI().isProponiendoTrato()) {

            if (getTableroGUI().getInformacion().getMenuGUI().isEstarDandoEnTrato()) {

                if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJuego().getTurno())) {
                    renderRecuadro();
                    if (isSeleccionada()) {
                        getTableroGUI().getInformacion().getMenuGUI().getCasillasAuxiliar().add(this);
                        renderRecuadroRelleno();
                    }
                }

            } else {
                if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())) {
                    renderRecuadro();
                    if (isSeleccionada()) {
                        getTableroGUI().getInformacion().getMenuGUI().getCasillasAuxiliar().add(this);
                        renderRecuadroRelleno();
                    }
                }
            }
        }
    }

    /**
     * Se renderiza un recuadro de selección
     */
    public void renderRecuadro() {
        // Cambiar a partir de este momento el color de las líneas a negro
        getGc().setStroke(Color.BLACK);
        // Cambiar a partir de este momento el grosor de las líneas a 1 puntos
        getGc().setLineWidth(1);
        // Dibujar un rectángulo vacio
        getGc().strokeRoundRect(ConstantesGUI.CASILLA_ANCHO - 25, ConstantesGUI.CASILLA_ALTO - 25, 20, 20, 5, 5);
    }

    /**
     * Se renderiza el relleno del recuadro de selección
     */
    public void renderRecuadroRelleno() {
        renderRecuadro();
        // Cambiar a partir de este momento el color de relleno a verde
        getGc().setFill(Color.GREEN);
        // Dibujar un rectángulo con bordes redondeados a partir de la posición
        getGc().fillRoundRect(ConstantesGUI.CASILLA_ANCHO - 25, ConstantesGUI.CASILLA_ALTO - 25, 20, 20, 5, 5);
    }

    /**
     * Se genera un menú contextual para la propiedad
     *
     * @param app aplicación de Monopoly sobre la cual se ejecuta el juego
     * @return menú contextual generado
     */
    public ContextMenu generarMenuContextual(Aplicacion app) {

        // Se crea el menú de opciones para a partir del padre
        ContextMenu menu = super.generarMenuContextual(app);

        // Se obtienen las funciones propias a la casilla
        HashSet<TipoFuncion> funciones = getCasilla().funcionesARealizar();

        // Opciones a añadir al menú
        ArrayList<MenuItem> opciones = new ArrayList<>();

        if (funciones.contains(TipoFuncion.hipotecar)) {

            // Se añade la opción de hipotecar
            MenuItem item1 = new MenuItem("Hipotecar");
            item1.setOnAction(new HipotecarPropiedad(getPropiedad(), app));
            opciones.add(item1);
        }

        if (funciones.contains(TipoFuncion.deshipotecar)) {

            // Se añade la opción de deshipotecar
            MenuItem item2 = new MenuItem("Deshipotecar");
            item2.setOnAction(new DeshipotecarPropiedad(getPropiedad(), app));
            opciones.add(item2);
        }

        // Si se van a añadir nuevas opciones
        if (!opciones.isEmpty()) {

            // Se añade un sepador
            menu.getItems().add(new SeparatorMenuItem());

            // Y se añaden las opciones
            menu.getItems().addAll(opciones);
        }

        return (menu);
    }
}
