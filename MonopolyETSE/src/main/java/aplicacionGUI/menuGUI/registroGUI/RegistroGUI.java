
package aplicacionGUI.menuGUI.registroGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

public class RegistroGUI {
    
    /* Atributos */
    
    // Nodo propiedad del tablero
    private final Group nodo;
    
    // ScrollPane asociado
    private final ScrollPane panel;
    
    
    /* Constructor */
    
    public RegistroGUI( Group raiz ) {
        
        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }
        
        // Se añade al nodo dado un nuevo nodo de uso para el registro
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        // Se mueve el registro a su posición adecuada
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.REGISTRO_DESPLAZAMIENTO_X,
                ConstantesGUI.REGISTRO_DESPLAZAMIENTO_Y));
        
        // Se crea el ScrollPane en el que insertar texto
        this.panel = new ScrollPane();
        
        // Se indica que la barra vertical siempre se encuentre visible
        this.panel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        // Se establece su tamaño
        this.panel.setPrefViewportWidth(ConstantesGUI.REGISTRO_ANCHO);
        this.panel.setPrefViewportHeight(ConstantesGUI.REGISTRO_ALTO);
        
       // this.panel.setStyle("RegistroGUICSS.css");
        
        
        // Se añade el ScrollPane al nodo
        this.nodo.getChildren().add(this.panel);
        
    }  
    
    
    
    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }

    public ScrollPane getPanel() {
        return panel;
    }

    
    
    /* Métodos */
    
    public void clear() {
        
        actualizarContenido("");
    }
    
    public void actualizarContenido(String contenido) {
        
        // Se crea un objeto de texto con el contenido
        // todo que no se pase en tamaño horizontal
        Text text = new Text(contenido);
        
        // Se cambia la tipografía
        text.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        
        // Se actualiza el contenido
        getPanel().setContent(text);
    }
}
