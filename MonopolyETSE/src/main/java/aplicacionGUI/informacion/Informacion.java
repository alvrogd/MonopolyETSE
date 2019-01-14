package aplicacionGUI.informacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
import javafx.scene.transform.Translate;
import monopoly.tablero.Tablero;

public class Informacion {

    /* Atributos */
    
    // Nodo propiedad de esta sección (la superior, representa información del juego)
    private final Group nodo;
    
    // Representación del tablero
    private final TableroGUI tableroGUI;
    

    
    /* Constructor */
    
    public Informacion(Group raiz, Tablero tablero) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para esta región de la GUI
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);
        
        // Se establece para la región la posición correspondiente en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.INFORMACION_DESPLAZAMIENTO_X,
                ConstantesGUI.INFORMACION_DESPLAZAMIENTO_Y));

        // Se crea la representación del tablero
        this.tableroGUI = new TableroGUI(this.nodo, tablero);
    }

    
    
    /* Getters */
    
    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    
    public Group getNodoInformacion() {
        return nodo;
    }

    
    
    /* Métodos */
    
    public void render() {

        getTableroGUI().render();
    }
}
