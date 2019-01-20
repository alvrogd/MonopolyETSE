package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.jerarquiaCasillas.Impuesto;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;

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
    // Menú contextual mostrado
    private ContextMenu menu;



    /* Constructor */

    public Celda(Editor editor, Group raiz, int posicionX, int posicionY) {

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

    public ContextMenu getMenu() {
        return menu;
    }

    public void setMenu(ContextMenu menu) {
        this.menu = menu;
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
        if( getCasillaGUI() != null ) {
            generarMenuContextualCasilla(menu);
        }

        else {
            generarMenuContextualNoCasilla(menu);
        }

        return(menu);
    }

    private void generarMenuContextualNoCasilla(ContextMenu menu ) {

        // Se crea un submenú para las opciones de crear una casilla
        Menu item1 = new Menu( "Crear casilla" );

        if( true) {

            // Se añade la opción para crear una casilla de comunidad
            MenuItem item2 = new MenuItem( "Comunidad" );
            item2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear comunidad");
                }
            });

            item1.getItems().add(item2);
        }

        if( true ) {

            // Se añade la opción para crear una casilla de impuestos
            MenuItem item3 = new MenuItem( "Impuesto" );
            item3.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear impuesto");
                }
            });

            item1.getItems().add(item3);
        }

        if( true) {

            // Se añade la opción para crear una casilla de servicios
            MenuItem item4 = new MenuItem( "Servicio" );
            item4.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear servicio");
                }
            });

            item1.getItems().add(item4);
        }

        if( true) {

            // Se añade la opción para crear un solar
            MenuItem item5 = new MenuItem( "Solar" );
            item5.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear solar");
                }
            });

            item1.getItems().add(item5);
        }

        if( true) {

            // Se añade la opción para crear una casilla de suerte
            MenuItem item6 = new MenuItem( "Suerte" );
            item6.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear suerte");
                }
            });

            item1.getItems().add(item6);
        }

        if( true) {

            // Se añade la opción para crear una casilla de transportes
            MenuItem item7 = new MenuItem( "Transporte" );
            item7.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion crear transporte");
                }
            });

            item1.getItems().add(item7);
        }

        // Se añade el submenú si contiene alguna opción
        if( !item1.getItems().isEmpty()) {
            menu.getItems().add(item1);
        }
    }

    private void generarMenuContextualCasilla(ContextMenu menu ) {

        if( true) {

            // Se añade la opción para cambiar el nombre
            MenuItem item1 = new MenuItem( "Cambiar nombre" );
            item1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion cambiar nombre");
                }
            });

            menu.getItems().add(item1);
        }

        // Si se trata de una propiedad
        if( getCasillaGUI().getCasilla() instanceof Propiedad) {
            generalMenuContextualPropiedad(menu);
        }

        // Si se trata de un impuesto
        else if (getCasillaGUI().getCasilla() instanceof Impuesto ) {
            generalMenuContextualImpuesto(menu);
        }
    }

    private void generalMenuContextualPropiedad(ContextMenu menu ) {

        if( true) {

            // Se añade la opción para cambiar el precio inicial
            MenuItem item1 = new MenuItem( "Cambiar precio inicial" );
            item1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion cambiar precio inicial");
                }
            });

            menu.getItems().add(item1);
        }

        // Si se trata de un solar
        if( getCasillaGUI().getCasilla() instanceof Solar) {
            generarMenuContextualSolar(menu);
        }
    }

    private void generarMenuContextualSolar(ContextMenu menu ) {

        if( true) {

            // Se añade la opción para cambiar el grupo
            MenuItem item1 = new MenuItem( "Cambiar grupo" );
            item1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion cambiar grupo");
                }
            });

            menu.getItems().add(item1);
        }
    }

    private void generalMenuContextualImpuesto(ContextMenu menu ) {

        if( true) {

            // Se añade la opción para cambiar el impuesto
            MenuItem item1 = new MenuItem( "Cambiar impuesto" );
            item1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion cambiar impuesto");
                }
            });

            menu.getItems().add(item1);
        }
    }
}
