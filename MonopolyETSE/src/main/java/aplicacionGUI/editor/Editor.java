package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

import java.util.ArrayList;

public class Editor {

    /* Atributos */

    // Nodo propiedad del editor
    private final Group nodo;

    // Sensor asociado al editor
    private final Rectangle sensor;

    // Diferencia para el sensor del editor
    private final Rectangle diferencia;

    // Celdas del editor
    private final ArrayList<ArrayList<Celda>> celdas;


    /* Constructor */

    public Editor(Group raiz ) {

        if( raiz == null ) {
            System.out.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el editor
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );

        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.EDITOR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y));

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Y su diferencia (es necesario el ajuste de juntar las casillas)
        this.diferencia = new Rectangle(ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO,
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ANCHO - 3 ),
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ALTO - 3 ));
        this.diferencia.setFill(Color.TRANSPARENT);

        // Se obtienen las correspondientes posiciones de las casillas en la ventana
        ArrayList<int[]> posiciones = TableroGUI.calcularPosiciones();

        // Se inicializa el array de celdas
        this.celdas = new ArrayList<>();

        // Se crean las celdas
        for( int i = 0, contador = 0; i < ConstantesGUI.NUMERO_LADOS; i++ ) {

            // Se crea una fila para cada lado
            ArrayList<Celda> fila = new ArrayList<>();

            // Se crean tantas celdas como casillas haya por fila
            for( int j = 0; j < ConstantesGUI.CASILLAS_POR_FILA; j++, contador++ ) {

                fila.add(new Celda(this, this.nodo, posiciones.get(contador)[0], posiciones.get(contador)[1]));
            }
        }
    }


    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Rectangle getDiferencia() {
        return diferencia;
    }

    public ArrayList<ArrayList<Celda>> getCeldas() {
        return celdas;
    }



    /* Métodos */

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.EDITOR_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY) && !getDiferencia().contains(posicionX, posicionY));
    }


    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus) {

        double posicionX = x - ConstantesGUI.EDITOR_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y;

        System.out.println("x es " + posicionX);
        System.out.println("y es " + posicionY);

        // Se comprueba cada una de las celdas
        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {

                if( celda.contienePosicion(posicionX, posicionY) ) {
                    celda.handleClickDerecho(posicionX, posicionY, nodoRaiz, e, menus);
                }
            }
        }
    }

    public void render(double t ) {

        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {
                celda.render(t);
            }
        }
    }
}
