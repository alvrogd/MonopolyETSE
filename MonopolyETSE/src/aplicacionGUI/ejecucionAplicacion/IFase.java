package aplicacionGUI.ejecucionAplicacion;

public interface IFase {

    /**
     * Se inicializa la fase
     */
    void iniciar();

    /**
     * Se renderiza la fase
     * @param t tiempo transcurrido
     */
    void render(double t);

    /**
     * Se limpia la representación de la fase
     */
    void clear();
}
