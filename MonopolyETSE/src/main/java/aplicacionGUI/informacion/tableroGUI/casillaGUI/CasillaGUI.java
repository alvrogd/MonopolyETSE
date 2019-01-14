package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.ColorCasillaGUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Avatar;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import resources.casillas.FondosCasillas;

public class CasillaGUI {

    /* Atributos */
    
    // Dimensiones de la representación
    public final int ANCHO = ConstantesGUI.CASILLA_ANCHO;
    public final int ALTO = ConstantesGUI.CASILLA_ALTO;

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
        this.fondo = new Image(FondosCasillas.class.getResource(ficheroFondo).toString());
    }

    
    
    /* Getters y setters */
    
    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public Image getFondo() {
        return fondo;
    }

    
    
    /* Métodos */
    
    public void render(GraphicsContext gc, int x, int y) {

        renderFondo(gc, x, y);
        renderNombre(gc, x, y);
        renderContenido(gc, x, y);
    }

    public void renderFondo(GraphicsContext gc, int x, int y) {

        // Se añade la imagen
        gc.drawImage(fondo, x, y);
    }

    public void renderNombre(GraphicsContext gc, int x, int y) {

        // Se añade el color a la casilla en la posición del nombre
        gc.setStroke(Color.TRANSPARENT);
        
        if(getCasilla() instanceof Propiedad){
            gc.setFill(ColorCasillaGUI.tipoColorToColorTransparente(((Propiedad) getCasilla()).getGrupo().getTipo().getColor()));
        }
        
        else {
            gc.setFill(Color.rgb(128, 128, 128, 0.85));
        }
        
        gc.fillRect(x + 3, y + 3, getANCHO() - 6, 14);

        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setLineWidth(1);

        // Se añade el nombre de la casilla (la posición es la parte central inferior)
        gc.fillText(getCasilla().getNombre(), x + ANCHO / 2, y + 14);
    }

    public void renderContenido(GraphicsContext gc, int x, int y) {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        gc.setFill(Color.rgb(128, 128, 128, 0.7));
        gc.fillRect(x + 3, y + 19, ANCHO - 6, 43);

        // Se renderiza el contenido
        renderAvataresContenidos(gc, x, y);
    }

    public void renderAvataresContenidos(GraphicsContext gc, int x, int y) {

        // Se establece la tipografía
        gc.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        gc.setStroke(Color.TRANSPARENT);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.LEFT);

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            gc.fillText(String.valueOf(avatar.getIdentificador()), x + 10 + desplazamiento, y + 32);
            desplazamiento += 18;
        }
    }
}
