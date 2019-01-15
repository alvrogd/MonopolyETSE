package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.ColorCasillaGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Avatar;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import resources.casillas.FondosCasillas;

public class CasillaGUI {

    /* Atributos */
    
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
    
    
    /* Constructor */
    
    public CasillaGUI(Group raiz, Casilla casilla, String ficheroFondo, int posicionX, int posicionY) {
        
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
    
    
    
    /* Métodos */
    
    public boolean contieneClickDerecho(double x, double y) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        return(getSensor().contains(posicionX, posicionY));
    }
    
    
    public void handleClickDerecho(double x, double y) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        System.out.println("Si" + getCasilla().getNombre());
    }
    
    
    public void render() {

        renderFondo();
        renderNombre();
        renderContenido();
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

    
    public void renderContenido() {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        getGc().setFill(Color.rgb(128, 128, 128, 0.6));
        getGc().fillRect(3, 19, ancho - 6, 43);

        // Se renderiza el contenido
        renderAvataresContenidos();
    }

    
    public void renderAvataresContenidos() {

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            getGc().fillText(String.valueOf(avatar.getIdentificador()), 10 + desplazamiento, 32);
            desplazamiento += 18;
        }
    }
}
