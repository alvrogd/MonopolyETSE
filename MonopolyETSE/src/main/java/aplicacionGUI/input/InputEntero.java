package aplicacionGUI.input;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;

public class InputEntero extends Input {

    /* Atributos */

    // Aquel que desea leer un entero
    private final ILectorEntero lector;



    /* Constructor */

    public InputEntero(Group raiz, boolean editor, int atributo, ILectorEntero lector) {

        super(raiz, ConstantesGUI.INPUT_ENTERO_IMAGEN, ConstantesGUI.INPUT_ENTERO_IMAGEN_OSCURA, editor, atributo);

        if( lector == null ) {
            System.err.println("Lector no inicializado");
            System.exit(1);
        }

        // Se guarda el lector del entero
        this.lector = lector;
    }


    /* Getters y setters */

    public ILectorEntero getLector() {
        return lector;
    }


    /* Métodos */

    /**
     * Se ejecuta la acción definida ante un release de un click
     */
    @Override
    public void handleRelease() {

        super.handleRelease();

        // Se procesa el input del usuario
        String buffer = getTextField().getCharacters().toString();

        try {
            int entero = Integer.parseInt(buffer);
            getLector().almacenarEntero(entero, getAtributo());
        }

        catch(NumberFormatException e) {
            System.err.println("No ha sido posible convertir el input del usuario a un entero");
        }
    }
}
