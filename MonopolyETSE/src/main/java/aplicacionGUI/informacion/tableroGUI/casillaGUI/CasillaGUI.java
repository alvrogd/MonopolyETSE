package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import aplicacionGUI.informacion.tableroGUI.ColorCasillaGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Avatar;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import resources.avatares.modoAvanzado.AnimacionAvataresModoAvanzado;
import resources.casillas.FondosCasillas;

public class CasillaGUI {

    /* Atributos */
    
    // Representación del tablero asociada
    private final TableroGUI tableroGUI;
    
    // Nodo propiedad de la casilla
    private final Group nodo;
    
    // Canvas contenido en el nodo
    private final Canvas canvas;
    
    // Contexto en el que representar objetos
    private final GraphicsContext gc;
    
    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.CASILLA_ANCHO;
    private final int alto = ConstantesGUI.CASILLA_ALTO;
    
    // Desplazamiento dado de la casilla
    private final int desplazamientoX;
    private final int desplazamientoY;

    // La casilla asociada
    private final Casilla casilla;
    
    // Imagen de fondo de la casilla asociada
    private final Image fondo;
    
    // Sensor de la casilla
    private Rectangle sensor;
    
    // Animación de movimiento avanzado para los avatares
    private final static ImagenAnimada ANIMACION_MODO_AVANZADO = new ImagenAnimada(new AnimacionAvataresModoAvanzado(),
            ConstantesGUI.AVATARES_AVANZADO_FRAMES, 0.25);
    
    
    
    /* Constructor */
    
    public CasillaGUI(TableroGUI tableroGUI, Group raiz, Casilla casilla, String ficheroFondo, int posicionX,
            int posicionY) {
        
        if( tableroGUI == null ) {
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }
        
        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        if (ficheroFondo == null) {
            System.err.println("Nombre del fichero de fondo no inicializado");
            System.exit(1);
        }
        
        // Se registra la representación del tablero a la que pertenece
        this.tableroGUI = tableroGUI;
        
        // Se añade al nodo dado un nuevo nodo de uso para la casilla
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        // Se establece su correspondiente posición en la ventana
        this.desplazamientoX = posicionX;
        this.desplazamientoY = posicionY;
        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));
        
        // Se crea un canvas en el nuevo nodo para representar la casilla
        this.canvas = new Canvas( ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.nodo.getChildren().add(canvas);
        
        // Se genera un contexto a partir del canvas para insertar la representación de la casilla
        this.gc = this.canvas.getGraphicsContext2D();
        
        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se guarda la referencia a la casilla asociada
        this.casilla = casilla;
        
        // Se obtiene el fondo correspondiente
        this.fondo = new Image(FondosCasillas.class.getResource(ficheroFondo).toString());
    }

    
    
    /* Getters y setters */
           
    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    
    public int getAncho() {
        return ancho;
    }

    
    public int getAlto() {
        return alto;
    }

    
    public Casilla getCasilla() {
        return casilla;
    }
    

    public Image getFondo() {
        return fondo;
    }

    
    public Rectangle getSensor() {
        return sensor;
    }

    
    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    
    public Group getNodo() {
        return nodo;
    }

    
    public Canvas getCanvas() {
        return canvas;
    }

    
    public GraphicsContext getGc() {
        return gc;
    }

    
    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    
    public static ImagenAnimada getANIMACION_MODO_AVANZADO() {
        return ANIMACION_MODO_AVANZADO;
    }
    
    
    
    /* Métodos */
    
    public boolean contienePosicion(double x, double y) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        return(getSensor().contains(posicionX, posicionY));
    }
    
    
    public void handleClickDerecho(double x, double y, Group nodoRaiz, ContextMenuEvent e) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        System.out.println(getCasilla().getNombre());
        
        generarMenuContextual().show(nodoRaiz, e.getScreenX(), e.getScreenY());
    }
    
    
    public void render(double t) {

        renderFondo();
        renderNombre();
        renderContenido(t);
    }
    
    
    public void renderFondo() {

        // Se añade la imagen
        getGc().drawImage(getFondo(), 0, 0);
    }

    
    public void renderNombre() {

        // Se añade el color a la casilla en la posición del nombre
        getGc().setStroke(Color.TRANSPARENT);
        
        if(getCasilla() instanceof Propiedad){
            getGc().setFill(ColorCasillaGUI.tipoColorToColorTransparente(((Propiedad) getCasilla()).getGrupo().getTipo(
                    ).getColor()));
        }
        
        else {
            getGc().setFill(Color.rgb(128, 128, 128, 0.85));
        }
        
        getGc().fillRect(3, 3, getAncho() - 6, 14);

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);

        // Se añade el nombre de la casilla (la posición es la parte central inferior)
        getGc().fillText(getCasilla().getNombre(), ancho / 2, 14);
    }

    
    public void renderContenido(double t) {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        getGc().setFill(Color.rgb(128, 128, 128, 0.6));
        getGc().fillRect(3, 19, ancho - 6, 43);

        // Se renderiza el contenido
        renderAvataresContenidos(t);
    }

    
    public void renderAvataresContenidos(double t) {

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            getGc().drawImage(getTableroGUI().getRepresentacionesAvatares().get(avatar.getIdentificador()), 6 +
                    desplazamiento, 22);
            
            // Y, si se encuentra en modo avanzado, se añade la animación
            if( !avatar.isMovimientoEstandar()) {
                getGc().drawImage(getANIMACION_MODO_AVANZADO().getFrame(t), 3 + desplazamiento, 20);
            }

            desplazamiento += 18;
        }
    }
    
    
    public ContextMenu generarMenuContextual() {
        
        // Se crea el menú de opciones para la casilla
        ContextMenu menu = new ContextMenu();
        
        // Se añade la opción de describir
        MenuItem item1 = new MenuItem( "Describir" );
        item1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle( ActionEvent event ) {
                System.out.println("Escogida opcion describir");
            }
        });
        
        // Se añade la opción al menú
        menu.getItems().add(item1);
        
        return( menu );
    }
}
