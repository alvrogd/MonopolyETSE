package aplicacionGUI.menuGUI.JugadoresGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.menuGUI.jugadores.JugadoresImagen;
import resources.sonidos.Sonidos;

import java.util.ArrayList;

public class JugadorGUI {

    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final Image barra;
    private final Image barraTratoOscuro;
    private final Image barraDescribirOscuro;
    private final Image turno;
    private final Image penalizado;
    private final Image bancarrota;

    private Image barraActual;
    private final Image avatar;

    private final MenuGUI menuGUI;

    private final Jugador jugador;

    private final int desplazamientoX;
    private final int desplazamientoY;

    private Rectangle sensor;
    private Rectangle boton;
    private Rectangle botonDescribir;
    private Rectangle botonAvatar;

    // Imágenes de estado
    private final static Image bufoCarcel = new Image(JugadoresImagen.class.getResource(
            ConstantesGUI.BUFO_CARCEL).toString());
    private final static Image bufoCabeza = new Image(JugadoresImagen.class.getResource(
            ConstantesGUI.BUFO_CABEZA).toString());

    // Sonido a reproducir cuando se pulsa un botón
    private static final Media sonido = new Media(Sonidos.class.getResource(ConstantesGUI.SONIDO_BOTON).toString());

    /* Constructor */

    public JugadorGUI(Group raiz, Jugador jugador, int numJugador, TableroGUI tableroGUI, MenuGUI menuGUI) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (jugador == null) {
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if (tableroGUI == null) {
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if (numJugador < 1) {
            System.err.println("El jugador no puede ser negativo o 0");
            System.exit(1);
        }

        if (menuGUI == null) {
            System.err.println("Menú no inicializado");
            System.exit(1);
        }

        this.menuGUI = menuGUI;
        this.jugador = jugador;

        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        this.desplazamientoX = ConstantesGUI.BARRA_DESPLAZAMIENTO_X;
        this.desplazamientoY = ConstantesGUI.BARRA_DESPLAZAMIENTO_Y +
                (ConstantesGUI.BARRA_JUGADOR_ALTO + ConstantesGUI.JUGADORES_SEPARACION) * (numJugador - 1);

        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));

        this.canvas = new Canvas(ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        // Sensor para la zona de jugadores
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Sensor para el botón
        this.boton = new Rectangle(ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_Y,
                ConstantesGUI.BARRA_JUGADOR_ALTO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.boton.setFill(Color.TRANSPARENT);

        this.botonDescribir = new Rectangle(ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_DES_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_DES_Y,
                ConstantesGUI.BARRA_JUGADOR_ALTO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.botonDescribir.setFill(Color.TRANSPARENT);

        this.botonAvatar = new Rectangle(0, 0,
                ConstantesGUI.BARRA_JUGADOR_ALTO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.botonAvatar.setFill(Color.TRANSPARENT);

        this.turno = new Image(JugadoresImagen.class.getResource("barraTurno.png").toString());
        this.penalizado = new Image(JugadoresImagen.class.getResource("barraPenalizar.png").toString());

        this.barra = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE).toString());
        this.barraActual = this.barra;

        this.bancarrota = new Image(JugadoresImagen.class.getResource("bancarrota.png").toString());

        this.barraTratoOscuro = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE_TRATO_OSCURO).toString());
        this.barraDescribirOscuro = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE_DESCRIBIR_OSCURO).toString());

        this.avatar = tableroGUI.getRepresentacionesAvatares().get(jugador.getAvatar().getIdentificador());

    }

    public Image getBancarrota() {
        return bancarrota;
    }

    public Image getTurno() {
        return turno;
    }

    public Group getNodo() {
        return nodo;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getBarra() {
        return barra;
    }

    public Image getAvatar() {
        return avatar;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public Image getBarraActual() {
        return barraActual;
    }

    public Image getBarraTratoOscuro() {
        return barraTratoOscuro;
    }

    public Image getBarraDescribirOscuro() {
        return barraDescribirOscuro;
    }

    public Rectangle getBotonDescribir() {
        return botonDescribir;
    }

    public Rectangle getBotonAvatar() {
        return botonAvatar;
    }

    public void setBarraActual(Image barraActual) {
        this.barraActual = barraActual;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Rectangle getBoton() {
        return boton;
    }

    public Image getPenalizado() {
        return penalizado;
    }

    public static Media getSonido() {
        return sonido;
    }

    public static Image getBufoCarcel() {
        return bufoCarcel;
    }

    public static Image getBufoCabeza() {
        return bufoCabeza;
    }

    public void renderBarra() {
        getGc().drawImage(getBarraActual(), 0, 0);
        if (getJugador().equals(getMenuGUI().getJuego().getTurno())) {
            getGc().drawImage(getTurno(), 0, 0);
        } else if (getJugador().getTurnosPenalizado() > 0) {
            getGc().drawImage(getPenalizado(), 0, 0);
        }
    }

    public void renderAvatar() {
        getGc().drawImage(getAvatar(), ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_Y);
    }

    public void renderNombre() {
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(getJugador().getNombre(), ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_Y);
    }

    public void renderDinero() {
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(Integer.toString(getJugador().getFortuna()) + "K €", ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_Y);
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return (getSensor().contains(posicionX, posicionY));
    }

    public boolean pulsandoBotonTrato(double x, double y) {
        double posicionX = x;
        double posicionY = y;

        return (getBoton().contains(posicionX, posicionY));
    }

    public boolean pulsandoAvatar(double x, double y) {
        double posicionX = x;
        double posicionY = y;

        return (getBotonAvatar().contains(posicionX, posicionY));
    }

    public boolean pulsandoBotonDescribir(double x, double y) {
        double posicionX = x;
        double posicionY = y;

        return (getBotonDescribir().contains(posicionX, posicionY));
    }

    public void handleTratos() {
        getMenuGUI().getBotonera().setCasillasDar(new ArrayList<>());
        getMenuGUI().getBotonera().setCasillasRecibir(new ArrayList<>());
        getMenuGUI().getBotonera().setInmunidades(new ArrayList<>());
        getMenuGUI().setSiguientePaso(false);
        getMenuGUI().setEstarDandoDinero(false);
        getMenuGUI().setFaseDinero(false);
        getMenuGUI().setJugadorProponerTrato(getJugador());
        getMenuGUI().setProponiendoTrato(true);
        getMenuGUI().setEstarDandoEnTrato(true);
        getMenuGUI().setFaseNoAlquiler(false);
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if (!getJugador().isEstaBancarrota()) {
            if (pulsandoBotonTrato(posicionX, posicionY)) {
                if (getMenuGUI().getBotonera().isAyuda()) {
                    Aplicacion.consola.imprimir(TipoFuncion.toString(TipoFuncion.proponerTrato));
                    getMenuGUI().getBotonera().setAyuda(false);
                } else {
                    handleTratos();
                }
            } else if (pulsandoBotonDescribir(posicionX, posicionY)) {
                if (getMenuGUI().getBotonera().isAyuda()) {
                    Aplicacion.consola.imprimir(TipoFuncion.toString(TipoFuncion.describirJugador));
                    getMenuGUI().getBotonera().setAyuda(false);
                } else {
                    getMenuGUI().getRegistroGUI().anadirContenido(getJugador().toString());
                }
            } else if (pulsandoAvatar(posicionX, posicionY)) {
                if (getMenuGUI().getBotonera().isAyuda()) {
                    Aplicacion.consola.imprimir(TipoFuncion.toString(TipoFuncion.describirAvatar));
                    getMenuGUI().getBotonera().setAyuda(false);
                } else {
                    getMenuGUI().getRegistroGUI().anadirContenido(getJugador().getAvatar().toString());
                }
            }
        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if (!getJugador().isEstaBancarrota()) {
            if (pulsandoBotonTrato(posicionX, posicionY)) {

                MediaPlayer reproductor = new MediaPlayer(getSonido());
                reproductor.play();
                setBarraActual(getBarraTratoOscuro());

            } else if (pulsandoBotonDescribir(posicionX, posicionY)) {

                MediaPlayer reproductor = new MediaPlayer(getSonido());
                reproductor.play();
                setBarraActual(getBarraDescribirOscuro());
            }
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        if (!getJugador().isEstaBancarrota()) {
            if (pulsandoBotonTrato(posicionX, posicionY)) {
                setBarraActual(getBarra());
            } else if (pulsandoBotonDescribir(posicionX, posicionY)) {
                setBarraActual(getBarra());
            }
        }
    }

    public void renderBancarrota() {
        getGc().drawImage(getBancarrota(), 0, 0);
    }

    public void render() {
        renderBarra();
        renderAvatar();
        renderNombre();
        renderDinero();

        if (getJugador().isEstaBancarrota()) {
            renderBancarrota();
        }

        renderBufos();
    }

    private void renderBufos() {

        boolean cabezaImpreso = false;

        if (getMenuGUI().getJuego().jugadorEnCabeza().equals(getJugador())) {
            getGc().drawImage(getBufoCabeza(), 407, 4);
            cabezaImpreso = true;
        }

        if (getJugador().getAvatar().isEncarcelado()) {
            getGc().drawImage(getBufoCarcel(), cabezaImpreso ? 370 : 407, 4);
        }
    }

    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final JugadorGUI otro = (JugadorGUI) obj;

        // Si los nombres del botón son el mismo
        return (this.getJugador().equals(otro.getJugador()));

    } /* Fin del método equals */
}
