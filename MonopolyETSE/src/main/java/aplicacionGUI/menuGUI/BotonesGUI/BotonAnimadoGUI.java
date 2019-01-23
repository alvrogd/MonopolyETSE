package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.sonidos.Sonidos;

public class BotonAnimadoGUI extends BotonGUI{

    private ImagenAnimada animacion;
    private Image tachadoAvanzado;
    private Image tachadoNormal;
    private boolean ponerTachado;
    private boolean animandose;
    private boolean primerFrame;

    private double frame;

    // Sonido a reproducir cuando se pasa a modo avanzado
    private static final Media sonido = new Media(Sonidos.class.getResource(ConstantesGUI.SONIDO_AVANZADO).toString());

    // Booleano para saber en que modo está
    private boolean modo;

    public BotonAnimadoGUI(BotoneraGUI botonera, Group raiz, String nombre, Aplicacion app, TipoFuncion funcion, int fila, int columna, Object localizador, String[] nombreFrames, double duracion) {
        super(botonera, raiz, app, nombre, funcion, fila, columna, true, false);
        crearImagenAnimada(localizador, nombreFrames, duracion);
        this.ponerTachado = false;
        this.modo = false;
        this.animandose = false;
        this.primerFrame = true;
    }

    public void crearImagenAnimada(Object localizador, String[] nombresFrames, double duracion){
        setAnimacion(new ImagenAnimada( localizador, nombresFrames, duracion ));
        this.tachadoNormal = new Image( localizador.getClass().getResource("tachadoNormal.png").toString());
        this.tachadoAvanzado = new Image( localizador.getClass().getResource("tachadoAvanzado.png").toString());
    }

    public Image getTachadoAvanzado() {
        return tachadoAvanzado;
    }

    public Image getTachadoNormal() {
        return tachadoNormal;
    }

    public boolean isAnimandose() {
        return animandose;
    }

    public void setAnimandose(boolean animandose) {
        this.animandose = animandose;
    }

    public boolean isPonerTachado() {
        return ponerTachado;
    }

    public boolean isModo() {
        return modo;
    }

    public void setModo(boolean modo) {
        this.modo = modo;
    }

    public void setPonerTachado(boolean ponerTachado) {
        this.ponerTachado = ponerTachado;
    }

    public ImagenAnimada getAnimacion() {
        return animacion;
    }

    public void setAnimacion(ImagenAnimada animacion) {
        this.animacion = animacion;
    }

    public boolean isPrimerFrame() {
        return primerFrame;
    }

    public void setPrimerFrame(boolean primerFrame) {
        this.primerFrame = primerFrame;
    }

    public static Media getSonido() {
        return sonido;
    }

    @Override
    public void inhabilitarBoton(){

        super.inhabilitarBoton();
        setPonerTachado(true);

    }

    @Override
    public void habilitarBoton(){
        super.habilitarBoton();
        setPonerTachado(false);
    }

    @Override
    public void handleClickIzquierdo(double x, double y){
        if(!isPonerTachado()) {
            double posicionX = x - getDesplazamientoX();
            double posicionY = y - getDesplazamientoY();

            if (pulsandoBoton(posicionX, posicionY)) {

                super.handleClickIzquierdo(x, y);
                setAnimandose(true);

                // Se reproduce un sonido de "upgrade" si pasa a avanzado
                if(!getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
                    System.out.println("Upgrade");
                    MediaPlayer reproductor = new MediaPlayer(getSonido());
                    reproductor.play();
                }
            }
        }
    }

    public void render(int fila, int columna, double t){

        if(isAnimandose()){
            renderAnimado(t);
        } else {

            if (isPonerTachado()) {
                if (getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
                    setBotonActual(getTachadoNormal());
                } else {
                    setBotonActual(getTachadoAvanzado());
                }
            } else {
                if(getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()){
                    setBotonActual(getAnimacion().getFrameInversoNumero(0));
                } else {
                    setBotonActual(getAnimacion().getFrameNumero(0));
                }
            }

        }

        super.render(fila, columna, t);

    }

    public void renderAnimado(double t){

        Image frame;

        if(isPrimerFrame()){
            getAnimacion().setTiempoInicio(t);
            setPrimerFrame(false);
        }

        if(getApp().getJuego().getTurno().getAvatar().isMovimientoEstandar()){

            frame = getAnimacion().getFrame(t);
            setBotonActual(frame);

            // En caso de estar ante el último frame se pone animandose a false y primerFrame a true
            if(getAnimacion().getIndice(t) == getAnimacion().getFrames().size() - 1){
                setPrimerFrame(true);
                setAnimandose(false);
            }

        } else {

            frame = getAnimacion().getFrameInverso(t);
            setBotonActual(frame);

            // En caso de estar ante el último frame se pone animandose a false y primerFrame a true
            if(getAnimacion().getIndiceInverso(t) == 0){
                setPrimerFrame(true);
                setAnimandose(false);
            }

        }

    }
}
