package aplicacionGUI.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Avatar;
import monopoly.tablero.jerarquiaCasillas.Casilla;

public class CasillaGUI {

    /* Atributos */
    
    // Dimensiones de la representación
    public static final int ANCHO = ConstantesGUI.ANCHO_CASILLA;
    public static final int ALTO = ConstantesGUI.ALTO_CASILLA;

    // La casilla asociada
    private final Casilla casilla;
    // Imagen de fondo de la casilla asociada
    private final Image fondo;

    
    
    /* Constructor */
    
    public CasillaGUI(Casilla casilla, String ficheroFondo) {

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        if (ficheroFondo == null) {
            System.err.println("Nombre del fichero de fondo no inicializado");
            System.exit(1);
        }

        this.casilla = casilla;
        this.fondo = new Image(ficheroFondo);
    }

    
    
    /* Getters y setters */
    
    public static int getANCHO() {
        return ANCHO;
    }

    public static int getALTO() {
        return ALTO;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public Image getFondo() {
        return fondo;
    }

    
    
    /* Métodos */
    
    public void render(GraphicsContext gc) {

        renderFondo(gc);
        renderNombre(gc);
        renderContenido(gc);
    }

    public void renderFondo(GraphicsContext gc) {

        // Se añade la imagen
        gc.drawImage(fondo, 100, 100);
    }

    public void renderNombre(GraphicsContext gc) {

        // Se añade el color a la casilla en la posición del nombre
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.rgb(128, 128, 128, 0.85));
        gc.fillRect(3, 3, getANCHO() - 6, 13);

        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setLineWidth(1);

        // Se añade el nombre de la casilla (la posición es la parte central inferior)
        gc.fillText(getCasilla().getNombre(), ANCHO / 2, 14);
    }

    public void renderContenido(GraphicsContext gc) {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        gc.setFill(Color.rgb(128, 128, 128, 0.7));
        gc.fillRect(3, 18, ANCHO - 6, 44);

        // Se renderiza el contenido
        renderAvataresContenidos(gc);
    }

    public void renderAvataresContenidos(GraphicsContext gc) {

        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            gc.fillText(String.valueOf(avatar.getIdentificador()), 4 + desplazamiento, 24);
        }
    }
}
