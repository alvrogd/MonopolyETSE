package aplicacionGUI.input;

import aplicacionGUI.ConstantesGUI;

public class InputString extends Input {

    /* Atributos */

    // Aquel que desea leer un string
    private final ILectorString lector;



    /* Constructor */

    /**
     * Se crea una instancia mediante la cual obtener strings del usuario a través del teclado
     *
     * @param editor   si el input se encuentra en un editor o en el juego, para establecer una correcta posición
     *                 en pantalla
     * @param atributo identificador del atributo que desea modificar aquel que invocó al input (cuando se lea la
     *                 entrada del usuario, el método llamado para guardar la información obtenida recibirá como
     *                 argumento el identificador, pudiendo discernir qué atributo debe ser modificado)
     * @param lector   aquel que desea leer un string del usuario
     */
    public InputString(boolean editor, int atributo, ILectorString lector) {

        super(ConstantesGUI.INPUT_STRING_IMAGEN, ConstantesGUI.INPUT_STRING_IMAGEN_OSCURA, editor, atributo);

        if (lector == null) {
            System.err.println("Lector no inicializado");
            System.exit(1);
        }

        // Se guarda el lector del entero
        this.lector = lector;
    }


    /* Getters y setters */

    public ILectorString getLector() {
        return lector;
    }



    /* Métodos */

    /**
     * Se ejecuta la acción definida ante el release de un click
     */
    @Override
    public void handleRelease() {

        super.handleRelease();

        // Se procesa el input del usuario
        getLector().almacenarString(getTextField().getCharacters().toString(), getAtributo());

        // Se elimina al haber cumplido la función
        finalizar();
    }
}
