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

    // Contenido
    private String contenido;

    /* Constructor */
    public RegistroGUI(Group raiz) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el registro
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

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

        // Se añade el ScrollPane al nodo
        this.nodo.getChildren().add(this.panel);

        this.contenido = null;

    }

    /* Getters y setters */
    public Group getNodo() {
        return nodo;
    }

    public ScrollPane getPanel() {
        return panel;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /* Métodos */
    public void clear() {

        actualizarContenido("");
    }

    public void actualizarContenido(String contenido) {

        // Se guarda el contenido
        guardarContenido(ajustarInformacion(contenido));
    }

    public void anadirContenido(String nuevoContenido) {

        StringBuilder actual = new StringBuilder();

        // Se obtiene el contenido actual y se añaden dos saltos de línea
        actual.append(getContenido()).append("\n\n");

        // Se le añade el nuevo contenido ajustado
        actual.append(ajustarInformacion(nuevoContenido));

        // Se guarda el contenido
        guardarContenido(actual.toString());
    }

    private void guardarContenido(String contenido) {

        // Se crea un objeto de texto con el contenido
        Text text = new Text(contenido);

        // Se cambia la tipografía
        text.setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, ConstantesGUI.REGISTRO_FUENTE_TAMANO));

        // Se actualiza el contenido
        getPanel().setContent(text);
        
        // Y se guarda
        setContenido(contenido);
    }

    private String ajustarInformacion(String contenido) {

        // Se separa en palabras
        String[] palabras = contenido.split(" ");

        // Se itera sobre las palabras
        StringBuilder stringBuilder = new StringBuilder();

        int longitud = 0;

        for (String palabra : palabras) {

            // Si la palabra no cabe en la actual línea, se indica un salto de línea y se resetea la longitud de línea
            if ((longitud + palabra.length()) * (ConstantesGUI.REGISTRO_FUENTE_TAMANO - 4.7)
                    >= ConstantesGUI.REGISTRO_ANCHO) {
                stringBuilder.append("\n");
                longitud = 0;
            }
            
            // Si la palabra contiene un salto de línea, se resetea la longitud
            if( palabra.contains("\n")) {
                longitud = 0;
            }

            // Se añade la palabra junto con un espacio
            stringBuilder.append(palabra).append(" ");
            longitud += palabra.length() + 1;
        }

        return (stringBuilder.toString());
    }
}
