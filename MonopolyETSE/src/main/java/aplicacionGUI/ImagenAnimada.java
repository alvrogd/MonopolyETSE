
package aplicacionGUI;

import javafx.scene.image.Image;

import java.util.ArrayList;


public class ImagenAnimada {

    /* Atributos */

    // Sprites de la animación
    private final ArrayList<Image> frames;

    // Duración de la animación
    private double duracion;

    // Tiempo de inicio de la animación
    private double tiempoInicio;



    /* Constructor */

    /**
     * Se inicializa una animación
     *
     * @param localizador   clase a partir de la cual localizar los frames
     * @param nombresFrames nombre de cada uno de los frames a obtener
     * @param duracion      duración de la animación
     */
    public ImagenAnimada(Object localizador, String[] nombresFrames, double duracion) {

        if (localizador == null) {
            System.err.println("Localizador no inicalizado");
            System.exit(1);
        }

        if (nombresFrames == null) {
            System.err.println("Array de nombres no inicializado");
            System.exit(1);
        }

        if (duracion <= 0) {
            System.err.println("La duración debe ser mayor que 0");
            System.exit(1);
        }

        // Se crean imágenes con los frames especificados
        this.frames = new ArrayList<>();

        for (String string : nombresFrames) {
            this.frames.add(new Image(localizador.getClass().getResource(string).toString()));
        }

        // Se guarda la duración
        this.duracion = duracion;

        // Se guarda un tiempo de inicio despreciable
        this.tiempoInicio = 0;
    }



    /* Getters y setters */

    public ArrayList<Image> getFrames() {
        return frames;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(double tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }




    /* Métodos */

    /**
     * Se obtiene un frame de la animación especificando el tiempo
     *
     * @param t tiempo transcurrido
     * @return  frame correspondiente
     */
    public Image getFrame(double t) {

        // Se calcula el número de frame:
        // - Se considera
        int i = (int) (((t - getTiempoInicio()) % (getFrames().size() * getDuracion())) / getDuracion());

        return (getFrames().get(i));
    }

    /**
     * Se obtiene un frame de la animación en orden inverso especificando el tiempo
     *
     * @param t tiempo transcurrido
     * @return  frame correspondiente
     */
    public Image getFrameInverso(double t) {

        int i = (int) (((t - getTiempoInicio()) % (getFrames().size() * getDuracion())) / getDuracion());

        return (getFrames().get(getFrames().size() - 1 - i));
    }

    /**
     * Se obtiene un frame de la animación especificando el número de frame
     *
     * @param i número de frame
     * @return  frame correspondiente
     */
    public Image getFrameNumero(int i) {

        return (getFrames().get(i));
    }

    /**
     * Se obtiene un frame de la animación en orden inverso especificando el número de frame
     *
     * @param i número de frame
     * @return  frame correspondiente
     */
    public Image getFrameInversoNumero(int i) {

        return (getFrames().get(getFrames().size() - 1 - i));
    }

    /**
     * Se obtiene un número de frame de la animación especificando el tiempo
     *
     * @param t tiempo transcurrido
     * @return  número de frame correspondiente
     */
    public int getIndice(double t) {
        return ((int) (((t - getTiempoInicio()) % (getFrames().size() * getDuracion())) / getDuracion()));
    }

    /**
     * Se obtiene un número de frame de la animación en orden inverso especificando el tiempo
     *
     * @param t tiempo transcurrido
     * @return  número de frame correspondiente
     */
    public int getIndiceInverso(double t) {
        return (getFrames().size() - 1 - getIndice(t));
    }
}
