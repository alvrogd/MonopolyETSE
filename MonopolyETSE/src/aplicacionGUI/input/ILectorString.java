package aplicacionGUI.input;

public interface ILectorString {

    /**
     * Se almacena un string dado en un atributo obtenido en función del identificador
     *
     * @param stringLeido           string leído del usuario
     * @param identificadorAtributo identificador del atributo a modificar
     */
    void almacenarString(String stringLeido, int identificadorAtributo);
}
