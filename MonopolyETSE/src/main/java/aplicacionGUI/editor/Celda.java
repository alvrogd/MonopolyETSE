package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.handlers.*;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Banca;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.*;

import java.util.ArrayList;
import java.util.HashSet;

public class Celda {

    /* Atributos */

    // Editor asociado
    private final Editor editor;

    // Nodo propiedad del sensor
    private final Group nodo;

    // Desplazamiento dado de la celda
    private final int desplazamientoX;
    private final int desplazamientoY;

    // Sensor de la celda
    private final Rectangle sensor;

    // Representación de casilla asociada
    private CasillaGUI casillaGUI;

    // Posición en el tablero de la casilla asociada
    private final int posicionTablero;

    // Menú contextual mostrado
    private ContextMenu menu;

    // Instancias que asociar a las casillas creadas
    private static final Banca banca = new Banca();
    private static final Tablero tablero = new Tablero();
    private static final TableroGUI tableroGUI = new TableroGUI();

    // Grupos que asociar a las casillas
    private static final Grupo grupoNegro = new Grupo(TipoGrupo.negro);
    private static final Grupo grupoCyan = new Grupo(TipoGrupo.cyan);
    private static final Grupo grupoRosa = new Grupo(TipoGrupo.rosa);
    private static final Grupo grupoNaranja = new Grupo(TipoGrupo.naranja);
    private static final Grupo grupoRojo = new Grupo(TipoGrupo.rojo);
    private static final Grupo grupoMarron = new Grupo(TipoGrupo.marron);
    private static final Grupo grupoVerde = new Grupo(TipoGrupo.verde);
    private static final Grupo grupoAzul = new Grupo(TipoGrupo.azul);
    private static final Grupo grupoServicios = new Grupo(TipoGrupo.servicios);
    private static final Grupo grupoTransportes = new Grupo(TipoGrupo.transporte);



    /* Constructor */

    /**
     * Se crea una celda del editor del tablero
     *
     * @param editor          editor asociado
     * @param raiz            nodo sobre el cual crear un hijo para la celda
     * @param posicionX       posición (coordenada X) de la celda en el tablero
     * @param posicionY       posición (coordenada Y) de la celda en el tablero
     * @param posicionTablero posición (del 0 al 39) de la casilla asociada en el tablero final
     */
    public Celda(Editor editor, Group raiz, int posicionX, int posicionY, int posicionTablero) {

        if (editor == null) {
            System.out.println("Editor no inicializado");
            System.exit(1);
        }

        if (raiz == null) {
            System.out.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se registra el editor al que pertenece
        this.editor = editor;

        // Se añade al nodo dado un nuevo nodo de uso para la celda
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        this.desplazamientoX = posicionX;
        this.desplazamientoY = posicionY;
        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se guarda la posición de la casilla en el tablero
        this.posicionTablero = posicionTablero;

        // El menú no será creado hasta ser solicitado
        this.menu = null;
    }



    /* Getters y setters */

    public Editor getEditor() {
        return editor;
    }

    public Group getNodo() {
        return nodo;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public CasillaGUI getCasillaGUI() {
        return casillaGUI;
    }

    public void setCasillaGUI(CasillaGUI casillaGUI) {
        this.casillaGUI = casillaGUI;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public int getPosicionTablero() {
        return posicionTablero;
    }

    public ContextMenu getMenu() {
        return menu;
    }

    public void setMenu(ContextMenu menu) {
        this.menu = menu;
    }

    public static Banca getBanca() {
        return banca;
    }

    public static Tablero getTablero() {
        return tablero;
    }

    public static TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    public static Grupo getGrupoNegro() {
        return grupoNegro;
    }

    public static Grupo getGrupoCyan() {
        return grupoCyan;
    }

    public static Grupo getGrupoRosa() {
        return grupoRosa;
    }

    public static Grupo getGrupoNaranja() {
        return grupoNaranja;
    }

    public static Grupo getGrupoRojo() {
        return grupoRojo;
    }

    public static Grupo getGrupoMarron() {
        return grupoMarron;
    }

    public static Grupo getGrupoVerde() {
        return grupoVerde;
    }

    public static Grupo getGrupoAzul() {
        return grupoAzul;
    }

    public static Grupo getGrupoServicios() {
        return grupoServicios;
    }

    public static Grupo getGrupoTransportes() {
        return grupoTransportes;
    }



    /* Métodos */

    /**
     * Se comprueba si contiene una posición 2D dada
     *
     * @param x coordenada X
     * @param y coordenada Y
     * @return si contiene la posición dada
     */
    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return (getSensor().contains(posicionX, posicionY));
    }


    /**
     * Se ejecuta la acción definida ante un click
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     * @param nodoRaiz nodo de anclaje
     * @param e        evento del click
     * @param menus    conjunto de menús contextuales activos
     */
    public void handleClick(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        System.out.println("Sensor x = " + getDesplazamientoX() + ", y =  " + getDesplazamientoY());

        // Se genera el menú
        setMenu(generarMenuContextual());

        // Se muestra el menú
        getMenu().show(nodoRaiz, e.getScreenX(), e.getScreenY());

        // Se añade al listado de menús activos
        menus.add(getMenu());
    }

    /**
     * Se renderiza la celda
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        if (getCasillaGUI() != null) {
            getCasillaGUI().render(t);
        }
    }

    /**
     * Se limpia el GC de la celda
     */
    public void clear() {

        if(getCasillaGUI() != null ) {
            getCasillaGUI().clear();
        }
    }

    /**
     * Se genera el menú contextual correspondiente a la celda
     *
     * @return menú contextual generado
     */
    public ContextMenu generarMenuContextual() {

        // Se crea el menú de opciones para la casilla
        ContextMenu menu = new ContextMenu();

        // Si la casilla existe
        if (getCasillaGUI() != null) {
            generarMenuContextualCasilla(menu);
        } else {
            generarMenuContextualNoCasilla(menu);
        }

        return (menu);
    }

    /**
     * Se genera el menú contextual correspondiente a cuando no existe una casilla en la celda
     *
     * @param menu menú contextual al que añadir opciones
     */
    private void generarMenuContextualNoCasilla(ContextMenu menu) {

        // Se crea un submenú para las opciones de crear una casilla
        Menu submenu = new Menu("Crear casilla");

        // Si se trata de una de las esquinas y no es la primera casilla (reservada para la salida)
        if (getPosicionTablero() % 10 == 0 && getPosicionTablero() != 0) {

            if (getEditor().masCasillas(TipoCasilla.carcel, getPosicionTablero())) {

                // Se añade la opción para crear una cárcel
                anadirCrearCasilla(submenu, "Cárcel", TipoCasilla.carcel);
            }

            if (getEditor().masCasillas(TipoCasilla.irCarcel, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de ir a la cárcel
                anadirCrearCasilla(submenu, "Ir a la cárcel", TipoCasilla.irCarcel);
            }

            if (getEditor().masCasillas(TipoCasilla.parking, getPosicionTablero())) {

                // Se añade la opción para crear un parking
                anadirCrearCasilla(submenu, "Parking", TipoCasilla.parking);
            }
        }

        // En caso contrario
        else {

            if (getEditor().masCasillas(TipoCasilla.comunidad, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de comunidad
                anadirCrearCasilla(submenu, "Comunidad", TipoCasilla.comunidad);
            }

            if (getEditor().masCasillas(TipoCasilla.impuesto, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de impuestos
                anadirCrearCasilla(submenu, "Impuesto", TipoCasilla.impuesto);
            }

            if (getEditor().masCasillas(TipoCasilla.servicio, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de servicios
                anadirCrearCasilla(submenu, "Servicio", TipoCasilla.servicio, getGrupoTransportes());
            }

            if (getEditor().masCasillas(TipoCasilla.solar, getPosicionTablero())) {

                // Se añade la opción para crear un solar
                Menu subsubmenu = new Menu("Solar");

                // Se obtienen los posibles grupos para la celda
                HashSet<TipoGrupo> gruposDisponibles = getGruposSolaresLibres();

                if (gruposDisponibles.contains(TipoGrupo.negro)) {
                    anadirCrearCasilla(subsubmenu, "Grupo negro", TipoCasilla.solar, getGrupoNegro());
                }

                if (gruposDisponibles.contains(TipoGrupo.cyan)) {
                    anadirCrearCasilla(subsubmenu, "Grupo cyan", TipoCasilla.solar, getGrupoCyan());

                }

                if (gruposDisponibles.contains(TipoGrupo.rosa)) {
                    anadirCrearCasilla(subsubmenu, "Grupo rosa", TipoCasilla.solar, getGrupoRosa());

                }

                if (gruposDisponibles.contains(TipoGrupo.naranja)) {
                    anadirCrearCasilla(subsubmenu, "Grupo naranja", TipoCasilla.solar, getGrupoNaranja());

                }

                if (gruposDisponibles.contains(TipoGrupo.rojo)) {
                    anadirCrearCasilla(subsubmenu, "Grupo rojo", TipoCasilla.solar, getGrupoRojo());

                }

                if (gruposDisponibles.contains(TipoGrupo.marron)) {
                    anadirCrearCasilla(subsubmenu, "Grupo marrón", TipoCasilla.solar, getGrupoMarron());

                }

                if (gruposDisponibles.contains(TipoGrupo.verde)) {
                    anadirCrearCasilla(subsubmenu, "Grupo verde", TipoCasilla.solar, getGrupoVerde());

                }

                if (gruposDisponibles.contains(TipoGrupo.azul)) {
                    anadirCrearCasilla(subsubmenu, "Grupo azul", TipoCasilla.solar, getGrupoAzul());

                }

                // Se añade el (sub:)submenú si contiene alguna opción
                if (!subsubmenu.getItems().isEmpty()) {
                    submenu.getItems().add(subsubmenu);
                }
            }

            if (getEditor().masCasillas(TipoCasilla.suerte, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de suerte
                anadirCrearCasilla(submenu, "Suerte", TipoCasilla.suerte);
            }

            if (getEditor().masCasillas(TipoCasilla.transporte, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de transportes
                anadirCrearCasilla(submenu, "Transporte", TipoCasilla.transporte, getGrupoTransportes());
            }
        }

        // Se añade el submenú si contiene alguna opción
        if (!submenu.getItems().isEmpty()) {
            menu.getItems().add(submenu);
        }
    }

    /**
     * Se añade a un menú la opción de crear una casilla del tipo especificado
     *
     * @param submenu     menú al cual añadir la opción
     * @param texto       texto de la opción
     * @param tipoCasilla tipo de casilla asociado a la opción
     */
    private void anadirCrearCasilla(Menu submenu, String texto, TipoCasilla tipoCasilla) {

        // Se crea el item
        MenuItem item = new MenuItem(texto);
        // Se le asocia un handler para crear una casilla en caso de click
        item.setOnAction(new CrearCasilla(this, tipoCasilla));
        // Se añade al menú
        submenu.getItems().add(item);
    }

    /**
     * Se añade a un menú la opción de crear una casilla del tipo y grupo especificados
     *
     * @param submenu     menú al cual añadir la opción
     * @param texto       texto de la opción
     * @param tipoCasilla tipo de casilla asociado a la opción
     * @param grupo       grupo asociado a la opción
     */
    private void anadirCrearCasilla(Menu submenu, String texto, TipoCasilla tipoCasilla, Grupo grupo) {

        // Se crea el item
        MenuItem item = new MenuItem(texto);
        // Se le asocia un handler para crear una casilla en caso de click
        item.setOnAction(new CrearCasilla(this, tipoCasilla, grupo));
        // Se añade al menú
        submenu.getItems().add(item);
    }

    /**
     * Se genera el menú contextual correspondiente a cuando existe una casilla en la celda
     *
     * @param menu menú contextual al que añadir opciones
     */
    private void generarMenuContextualCasilla(ContextMenu menu) {

        // Si no es la casilla de salida
        if (getPosicionTablero() != 0) {

            // Se añade la opción para eliminar la casilla
            MenuItem item1 = new MenuItem("Eliminar casilla");
            item1.setOnAction(new EliminarCasilla(this, getCasillaGUI().getCasilla()));
            menu.getItems().addAll(item1, new SeparatorMenuItem());
        }

        // Se añade la opción para cambiar el nombre
        MenuItem item2 = new MenuItem("Cambiar nombre");
        item2.setOnAction(new CambiarNombre(this));
        menu.getItems().add(item2);

        // Se añade la opción para cambiar la imagen de fondo
        MenuItem item3 = new MenuItem("Cambiar fondo");
        item3.setOnAction(new CambiarFondo(this));
        menu.getItems().add(item3);

        // Si se trata de una propiedad
        if (getCasillaGUI().getCasilla() instanceof Propiedad) {
            generalMenuContextualPropiedad(menu);
        }

        // Si se trata de un impuesto
        else if (getCasillaGUI().getCasilla() instanceof Impuesto) {
            generalMenuContextualImpuesto(menu);
        }
    }

    /**
     * Se genera el menú contextual correspondiente a cuando existe una propiedad en la celda
     *
     * @param menu menú contextual al que añadir opciones
     */
    private void generalMenuContextualPropiedad(ContextMenu menu) {

        // Se añade la opción para cambiar el precio inicial
        MenuItem item = new MenuItem("Cambiar precio inicial");
        item.setOnAction(new CambiarPrecioInicial(this));
        menu.getItems().add(item);

        // Si se trata de un solar
        if (getCasillaGUI().getCasilla() instanceof Solar) {
            generarMenuContextualSolar(menu);
        }
    }

    private void generarMenuContextualSolar(ContextMenu menu) {

        // todo añadir o no?
        /*// Se añade la opción para cambiar el grupo
        MenuItem item = new MenuItem("Cambiar grupo");
        item.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Escogida opcion cambiar grupo");
            }
        });

        menu.getItems().add(item);*/
    }

    /**
     * Se genera el menú contextual correspondiente a cuando existe un impuesto en la celda
     *
     * @param menu menú contextual al que añadir opciones
     */
    private void generalMenuContextualImpuesto(ContextMenu menu) {

        // Se añade la opción para cambiar el impuesto
        MenuItem item = new MenuItem("Cambiar impuesto");
        item.setOnAction(new CambiarImpuesto(this));
        menu.getItems().add(item);
    }

    /**
     * Se comprueba de qué grupos de solares es posible que la celda cree un solar, buscando los correspondientes a su
     * fila y que no se encuentran llenos
     *
     * @return grupos de solares libres para crear una casilla perteneciente a ellos
     */
    public HashSet<TipoGrupo> getGruposSolaresLibres() {

        return (getEditor().getGruposSolaresLibres(getPosicionTablero()));
    }

    /**
     * Se proceso la información de la celda para crear un contenedor sobre información de una representación de una
     * casilla
     *
     * @return información de una representación de una casilla
     */
    public InformacionCasillaGUI toInformacionCasillaGUI() {

        // En primer lugar, se guardar en variables determinadas referencias para evitar llamar a métodos
        // constantemente
        final Casilla casilla = getCasillaGUI().getCasilla();

        final TipoCasilla tipoCasilla;
        final String nombre = getCasillaGUI().getCasilla().getNombre();
        final Grupo grupo;
        final int importe;
        final Image fondo = getCasillaGUI().getFondo();

        if (casilla instanceof Propiedad) {

            Propiedad propiedad = (Propiedad) casilla;

            // Se guardan el grupo, y el precio inicial como importe
            grupo = propiedad.getGrupo();
            importe = propiedad.getPrecioActual();

            if (propiedad instanceof Servicio) {
                tipoCasilla = TipoCasilla.servicio;
            } else if (propiedad instanceof Solar) {
                tipoCasilla = TipoCasilla.solar;
            } else {
                tipoCasilla = TipoCasilla.transporte;
            }
        } else if (casilla instanceof Accion) {

            // No se guarda ningún grupo y el importe también se ignora
            grupo = null;
            importe = 0;

            if (casilla instanceof Especial) {

                if (casilla instanceof Carcel) {
                    tipoCasilla = TipoCasilla.carcel;
                } else if (casilla instanceof IrCarcel) {
                    tipoCasilla = TipoCasilla.irCarcel;
                } else if (casilla instanceof Parking) {
                    tipoCasilla = TipoCasilla.parking;
                } else {
                    tipoCasilla = TipoCasilla.salida;
                }
            } else if (casilla instanceof ComunidadCasilla) {
                tipoCasilla = TipoCasilla.comunidad;
            } else {
                tipoCasilla = TipoCasilla.suerte;
            }
        } else {
            // No se guarda un grupo pero sí se guarda el impuesto asociado como importe
            tipoCasilla = TipoCasilla.impuesto;
            grupo = null;
            importe = ((Impuesto) casilla).getImpuesto();
        }

        return (new InformacionCasillaGUI(tipoCasilla, nombre, grupo, importe, fondo));
    }
}
