package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.fases.ImagenesFases;

public class BotonFase{

    // Nombre del botón
    private final String nombre;

    // Función que tiene el botón
    private final TipoFuncionFase funcion;

    // Imágenes del botón
    private final Image boton;
    private final Image botonOscuro;
    private Image botonActual;

    // Posición en la fase
    private int posicionX;
    private int posicionY;

    // Nodo del botón
    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    // Sensor para el botón
    private final Rectangle sensor;

    // Transformación
    private Translate translate;

    // Fase
    private FaseJugador faseJugador;

    // Sonido a reproducir cuando se pulsa un botón
    private static final Media sonido = new Media(resources.sonidos.Sonidos.class.getResource(ConstantesGUI.SONIDO_BOTON).toString());

    // Sonido a reproducir cuando se lanzan los dados
    private static final Media sonidoDados = new Media(resources.sonidos.Sonidos.class.getResource(ConstantesGUI.SONIDO_DADOS).toString());

    public BotonFase(FaseJugador fase, Group raiz, String nombre, TipoFuncionFase funcion, int posicionX, int posicionY){
        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(nombre == null){
            System.err.println("Nombre no inicializado");
            System.exit(1);
        }

        if(funcion == null){
            System.err.println("Función no inicializada");
            System.exit(1);
        }

        if(fase == null){
            System.err.println("Fase no inicializada.");
            System.exit(1);
        }

        this.faseJugador = fase;
        this.nombre = nombre;
        this.funcion = funcion;

        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Posición en la ventana
        this.posicionX = posicionX;
        this.posicionY = posicionY;

        this.translate = new Translate(posicionX, posicionY);

        this.nodo.getTransforms().add(this.translate);

        this.canvas = new Canvas(ConstantesGUI.BOTONFASE_ANCHO, ConstantesGUI.BOTONFASE_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        this.sensor = new Rectangle(0, 0, ConstantesGUI.BOTONFASE_ANCHO, ConstantesGUI.BOTONFASE_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.boton = new Image(ImagenesFases.class.getResource(nombre + ".png").toString());
        this.botonOscuro = new Image(ImagenesFases.class.getResource(nombre + "Oscuro.png").toString());
        this.botonActual = this.boton;

    }

    public FaseJugador getFaseJugador() {
        return faseJugador;
    }

    public void setFaseJugador(FaseJugador faseJugador) {
        this.faseJugador = faseJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoFuncionFase getFuncion() {
        return funcion;
    }

    public Image getBoton() {
        return boton;
    }

    public Image getBotonOscuro() {
        return botonOscuro;
    }

    public Image getBotonActual() {
        return botonActual;
    }

    public void setBotonActual(Image botonActual) {
        this.botonActual = botonActual;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public Group getNodo() {
        return nodo;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }

    public static Media getSonido() {
        return sonido;
    }

    public static Media getSonidoDados() {
        return sonidoDados;
    }

    public void inhabilitarBoton(){

        getSensor().setX(-1000);
        getSensor().setY(-1000);

    }

    public void habilitarBoton(){

        getSensor().setX(0);
        getSensor().setY(0);

    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getPosicionX();
        double posicionY = y - getPosicionY();

        return(getSensor().contains(posicionX, posicionY));
    }

    public boolean pulsandoBoton(double x, double y){
        return(getSensor().contains(x, y));
    }

    public void anadirJugador(){

    }

    public void coche(){

    }

    public void esfinge(){

    }

    public void sombrero(){

    }

    public void pelota(){

    }

    public void ejecutarAccion(){

        switch(getFuncion()){

            case anadirJugador:
                anadirJugador();
                break;
            case coche:
                coche();
                break;
            case esfinge:
                esfinge();
                break;
            case sombrero:
                sombrero();
                break;
            case pelota:
                pelota();
                break;
        }

    }

    public void handleClickIzquierdo(double x, double y){

        double posicionX = x - getPosicionX();
        double posicionY = y - getPosicionY();

        if(pulsandoBoton(posicionX, posicionY)){

        }

    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - getPosicionX();
        double posicionY = y - getPosicionY();

        if(pulsandoBoton(posicionX, posicionY)){

            MediaPlayer reproductor = new MediaPlayer(getSonido());
            reproductor.play();
            setBotonActual(getBotonOscuro());
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - getPosicionX();
        double posicionY = y - getPosicionY();

        if(pulsandoBoton(posicionX, posicionY)){
            setBotonActual(getBoton());
        }
    }

    public void render(double t){
        getGc().drawImage(getBotonActual(), 0, 0);
    }

    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final BotonFase otro = (BotonFase) obj;

        // Si los nombres del botón son el mismo
        return (this.getNombre().equals(otro.getNombre()));

    } /* Fin del método equals */
}
