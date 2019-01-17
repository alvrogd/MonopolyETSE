package aplicacionGUI.MenuGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
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
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import resources.MenuGUI.Jugadores.JugadoresImagen;

public class JugadorGUI {

    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final Image barra;
    private final Image avatar;

    private final Jugador jugador;

    private final int desplazamientoX;
    private final int desplazamientoY;

    private Rectangle sensor;

    /* Constructor */

    public JugadorGUI(Group raiz, Jugador jugador, int numJugador, TableroGUI tableroGUI){

        if(raiz == null){
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if(jugador == null){
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if(tableroGUI == null){
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if(numJugador < 1){
            System.err.println("El jugador no puede ser negativo o 0");
            System.exit(1);
        }

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

        this.sensor = new Rectangle(0, 0, ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.barra = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE).toString());
        this.avatar = tableroGUI.getRepresentacionesAvatares().get(jugador.getAvatar().getIdentificador());

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

    public void renderBarra(){
        getGc().drawImage(getBarra(), 0, 0);
    }

    public void renderAvatar(){
        getGc().drawImage(getAvatar(), ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_Y);
    }

    public void renderNombre(){
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(getJugador().getNombre(), ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_Y);
    }

    public void renderDinero(){
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(Integer.toString(getJugador().getFortuna()) + "K €", ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_Y);
    }

    public void render(){
        renderBarra();
        renderAvatar();
        renderNombre();
        renderDinero();
    }

}
