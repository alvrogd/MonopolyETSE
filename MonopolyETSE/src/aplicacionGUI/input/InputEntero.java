package aplicacionGUI.input;

public class InputEntero extends Input {

    /* Atributos */

    // Aquel que desea leer un entero
    private final ILectorEntero lector;



    /* Constructor */

    /**
     * Se crea una instancia mediante la cual obtener enteros del usuario a través del teclado
     *
     * @param editor       si el input se encuentra en un editor o en el juego, para establecer una correcta posición
     *                     en pantalla
     * @param atributo     identificador del atributo que desea modificar aquel que invocó al input (cuando se lea la
     *                     entrada del usuario, el método llamado para guardar la información obtenida recibirá como
     *                     argumento el identificador, pudiendo discernir qué atributo debe ser modificado)
     * @param lector       aquel que desea leer un entero del usuario
     * @param imagen       imagen a establecer cuando no se pulsa
     * @param imagenOscura imagen a establecer cuandos se pulsa
     */
    public InputEntero(boolean editor, int atributo, ILectorEntero lector, String imagen, String imagenOscura) {

        super(imagen, imagenOscura, editor, atributo);

        if (lector == null) {
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
     * Se ejecuta la acción definida ante el release de un click
     */
    @Override
    public void handleRelease() {

        super.handleRelease();

        // Se procesa el input del usuario
        String buffer = getTextField().getCharacters().toString();

        try {

            int entero = Integer.parseInt(buffer);
            getLector().almacenarEntero(entero, getAtributo());

            // Si ha cumplido la función, se elimina
            finalizar();

        } catch (NumberFormatException e) {

            System.err.println("No ha sido posible convertir el input del usuario a un entero");
        }
    }
}
