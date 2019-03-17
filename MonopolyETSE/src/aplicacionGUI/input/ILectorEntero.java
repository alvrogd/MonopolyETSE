package aplicacionGUI.input;

public interface ILectorEntero {

    /**
     * Se almacena un entero dado en un atributo obtenido en función del identificador
     *
     * @param enteroLeido           entero leído del usuario
     * @param identificadorAtributo identificador del atributo a modificar
     */
    void almacenarEntero(int enteroLeido, int identificadorAtributo);
}
