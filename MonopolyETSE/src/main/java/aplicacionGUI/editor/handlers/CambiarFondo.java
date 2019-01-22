package aplicacionGUI.editor.handlers;

import aplicacionGUI.editor.Celda;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class CambiarFondo extends CambiarAtributo {

    /* Constructor */

    /**
     * Se crea un handler para el cambio de la imagen de fondo de la representación de la casilla contenida en la celda
     * dada
     *
     * @param celda celda a asociar
     */
    public CambiarFondo(Celda celda) {

        super(celda);
    }



    /* Métodos */

    /**
     * Se cambia la imagen de fondo de la representación de la casilla de la celda asociada
     *
     * @param event click que activa el handler
     */
    @Override
    public void handle(ActionEvent event) {

        // Se crea una ventana en la que escoger un archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escoja una imagen de fondo");
        File imagen = fileChooser.showOpenDialog(null);

        // Si el usuario ha escogido una imagen
        if (imagen != null) {
            getCelda().getCasillaGUI().setFondo(new Image(imagen.toURI().toString()));
        }

        // Si el usuario quiere provocar un SEGFAULT
        else {
            System.out.println("Operación cancelada");
        }
    }
}
