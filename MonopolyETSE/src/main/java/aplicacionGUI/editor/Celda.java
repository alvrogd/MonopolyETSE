package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.PropiedadGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.SolarGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
import java.util.Scanner;

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

        // Se genera el menú correspondiente
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

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return (getSensor().contains(posicionX, posicionY));
    }


    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
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

    public void render(double t) {

        if (getCasillaGUI() != null) {
            getCasillaGUI().render(t);
        }
    }

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

    private void generarMenuContextualNoCasilla(ContextMenu menu) {

        // Se crea un submenú para las opciones de crear una casilla
        Menu submenu = new Menu("Crear casilla");

        // Si se trata de una de las esquinas
        if(getPosicionTablero() % 10 == 0) {

            if (getEditor().masCasillas(TipoCasilla.carcel, getPosicionTablero())) {

                // Se añade la opción para crear una cárcel
                MenuItem item = new MenuItem("Cárcel");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new Carcel("Cárcel",
                                getPosicionTablero(), getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.carcel, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.irCarcel, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de ir a la cárcel
                MenuItem item = new MenuItem("Ir a la cárcel");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new IrCarcel("Ir a la cárcel",
                                getPosicionTablero(), getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.irCarcel, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.parking, getPosicionTablero())) {

                // Se añade la opción para crear un parking
                MenuItem item = new MenuItem("Parking");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new Parking("Parking",
                                getPosicionTablero(), getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.parking, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.salida, getPosicionTablero())) {

                // Se añade la opción para crear una salida
                MenuItem item = new MenuItem("Salida");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new Salida("Salida",
                                getPosicionTablero(), getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.salida, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }
        }

        // En caso contrario
        else {

            if (getEditor().masCasillas(TipoCasilla.comunidad, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de comunidad
                MenuItem item = new MenuItem("Comunidad");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new ComunidadCasilla("Casilla Comunidad",
                                getPosicionTablero(), getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.comunidad, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.impuesto, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de impuestos
                MenuItem item = new MenuItem("Impuesto");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new Impuesto("Casilla Impuesto", getPosicionTablero(),
                                getTablero(), 100), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.impuesto, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.servicio, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de servicios
                MenuItem item = new MenuItem("Servicio");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new PropiedadGUI(getTableroGUI(), getNodo(), new Servicio("Casilla Servicio",
                                getGrupoServicios(),true, getPosicionTablero(), getBanca(), getTablero()),
                                ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.servicio, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.solar, getPosicionTablero())) {

                // Se añade la opción para crear un solar
                MenuItem item = new MenuItem("Solar");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new SolarGUI(getTableroGUI(), getNodo(), new Solar("Casilla Solar",
                                getGrupoNegro(),true, getPosicionTablero(), getBanca(), getTablero()),
                                ConstantesGUI.EDITOR_CASILLA_BLANCO, 0,0 ));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.solar, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.suerte, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de suerte
                MenuItem item = new MenuItem("Suerte");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new CasillaGUI(getTableroGUI(), getNodo(), new SuerteCasilla("Casilla Suerte", getPosicionTablero(),
                                getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.suerte, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }

            if (getEditor().masCasillas(TipoCasilla.transporte, getPosicionTablero())) {

                // Se añade la opción para crear una casilla de transportes
                MenuItem item = new MenuItem("Transporte");
                item.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        setCasillaGUI(new PropiedadGUI(getTableroGUI(), getNodo(), new Transporte("Casilla Transporte",
                                getGrupoTransportes(),true, getPosicionTablero(), getBanca(), getTablero()),
                                ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
                        getEditor().actualizarNumeroCasillas(TipoCasilla.transporte, getPosicionTablero(), 1);
                    }
                });

                submenu.getItems().add(item);
            }
        }

        // Se añade el submenú si contiene alguna opción
        if (!submenu.getItems().isEmpty()) {
            menu.getItems().add(submenu);
        }
    }

    private void generarMenuContextualCasilla(ContextMenu menu) {

        // Se añade la opción para eliminar la casilla
        MenuItem item1 = new MenuItem("Eliminar casilla");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                final Casilla casilla = getCasillaGUI().getCasilla();

                // Se actualiza el número de casillas presentes del tipo de casilla correspondiente
                if( casilla instanceof Propiedad ) {

                    if( casilla instanceof Servicio ) {
                        getEditor().actualizarNumeroCasillas(TipoCasilla.servicio, getPosicionTablero(), -1);
                    }

                    else if( casilla instanceof Solar ) {
                        getEditor().actualizarNumeroCasillas(TipoCasilla.solar, getPosicionTablero(), -1);
                    }

                    else {
                        getEditor().actualizarNumeroCasillas(TipoCasilla.transporte, getPosicionTablero(), -1);
                    }
                }

                else if( casilla instanceof Accion) {

                    if( casilla instanceof Especial ) {

                        if( casilla instanceof Carcel ) {
                            getEditor().actualizarNumeroCasillas(TipoCasilla.carcel, getPosicionTablero(), -1);
                        }

                        if( casilla instanceof IrCarcel ) {
                            getEditor().actualizarNumeroCasillas(TipoCasilla.irCarcel, getPosicionTablero(), -1);
                        }

                        if( casilla instanceof Parking ) {
                            getEditor().actualizarNumeroCasillas(TipoCasilla.parking, getPosicionTablero(), -1);
                        }

                        else {
                            getEditor().actualizarNumeroCasillas(TipoCasilla.salida, getPosicionTablero(), -1);
                        }
                    }

                    else if( casilla instanceof ComunidadCasilla ) {
                        getEditor().actualizarNumeroCasillas(TipoCasilla.comunidad, getPosicionTablero(), -1);
                    }

                    else {
                        getEditor().actualizarNumeroCasillas(TipoCasilla.suerte, getPosicionTablero(), -1);
                    }
                }

                else {
                    getEditor().actualizarNumeroCasillas(TipoCasilla.impuesto, getPosicionTablero(), -1);
                }

                getCasillaGUI().clear();
                setCasillaGUI(null);
            }
        });

        menu.getItems().addAll(item1, new SeparatorMenuItem());

        // Se añade la opción para cambiar el nombre
        MenuItem item2 = new MenuItem("Cambiar nombre");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scanner scanner = new Scanner(System.in);
                getCasillaGUI().getCasilla().setNombre(scanner.nextLine());
            }
        });

        menu.getItems().add(item2);

        // Se añade la opción para cambiar la imagen de fondo
        MenuItem item3 = new MenuItem("Cambiar fondo");
        item3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Escogida opcion cambiar fondo");
            }
        });

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

    private void generalMenuContextualPropiedad(ContextMenu menu) {

        // Se añade la opción para cambiar el precio inicial
        MenuItem item = new MenuItem("Cambiar precio inicial");
        item.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scanner scanner = new Scanner(System.in);
                final Propiedad propiedad = (Propiedad)getCasillaGUI().getCasilla();
                propiedad.getGrupo().setPrecio(scanner.nextInt() * propiedad.getGrupo().getTipo().getTamano());
            }
        });

        menu.getItems().add(item);

        // Si se trata de un solar
        if (getCasillaGUI().getCasilla() instanceof Solar) {
            generarMenuContextualSolar(menu);
        }
    }

    private void generarMenuContextualSolar(ContextMenu menu) {

        // Se añade la opción para cambiar el grupo
        MenuItem item = new MenuItem("Cambiar grupo");
        item.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Escogida opcion cambiar grupo");
            }
        });

        menu.getItems().add(item);
    }

    private void generalMenuContextualImpuesto(ContextMenu menu) {

        // Se añade la opción para cambiar el impuesto
        MenuItem item = new MenuItem("Cambiar impuesto");
        item.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Scanner scanner = new Scanner(System.in);
                final Propiedad propiedad = (Propiedad)getCasillaGUI().getCasilla();
                propiedad.getGrupo().setPrecio(scanner.nextInt() * propiedad.getGrupo().getTipo().getTamano());
            }
        });

        menu.getItems().add(item);
    }
}
