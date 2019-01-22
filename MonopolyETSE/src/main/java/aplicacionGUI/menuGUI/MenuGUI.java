package aplicacionGUI.menuGUI;
import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.menuGUI.BotonesGUI.BotonGUI;
import aplicacionGUI.menuGUI.BotonesGUI.BotoneraGUI;
import aplicacionGUI.menuGUI.JugadoresGUI.JugadoresGUI;
import aplicacionGUI.menuGUI.entrada.CambiarDarTrato;
import aplicacionGUI.menuGUI.entrada.CambiarRecibirTrato;
import aplicacionGUI.menuGUI.registroGUI.ConsolaInterfaz;
import aplicacionGUI.menuGUI.registroGUI.RegistroGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.jugadores.tratos.Trato;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.menuGUI.MenuGUIFondo;

import java.util.ArrayList;

public class MenuGUI{

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    // Juego
    private final Juego juego;

    // Aplicacion
    private Aplicacion aplicacion;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imagen de fondo para el menú
    private final Image fondo;

    // Canvas para la representación del menú
    private final Canvas canvas;
    
    // Registro del menú
    private final RegistroGUI registroGUI;

    // Representación de la zona de los jugadores
    private final JugadoresGUI jugadoresGUI;

    // Representación de la botonera
    private final BotoneraGUI botonera;

    // Número de jugadores
    private int numJugadores;

    // Booleano que indica si se está proponiendo un trato
    private boolean proponiendoTrato;

    // Booleano que indica si se están seleccionando propiedades para dar o para recibir
    private boolean estarDandoEnTrato;

    // Booleano que indica si se está proponiendo un trato y a mayores se está en la fase del dinero
    private boolean faseDinero;

    // Booleano para saber si el jugador está recibiendo o dando dinero
    private boolean estarDandoDinero;

    // Jugador al que se le está proponiendo un trato en caso de que se esté proponiendo
    private Jugador jugadorProponerTrato;

    // Booleano para saber si se ha entrado en la fase de no alquiler
    private boolean faseNoAlquiler;

    // Booleano para indicar si se puede continuar al siguiente paso
    private boolean siguientePaso;

    // Array auxiliar donde se añadirán las CasillasGUI que participan en una parte del trato
    private ArrayList<CasillaGUI> casillasAuxiliar;

    // Trato auxiliar donde se almacena el trato que se está creando actualmente
    private Trato trato;

    // BotonGUI auxiliar donde se almacenará el botón de aceptar
    private BotonGUI botonGUI;

    // Booleano para saber si está un input activo
    private boolean inputActivo;

    // Booleano para indicarle al marco de información si se puede imprimir
    private boolean renderMarco;

    /* Constructor */

    public MenuGUI(Group raiz, Aplicacion app, String imagen, TableroGUI tableroGUI){

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if(tableroGUI == null){
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if (app == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        if(imagen == null){
            System.err.println("Imagen no inicializada");
            System.exit(1);
        }

        // Se asigna el juego
        this.juego = app.getJuego();
        this.aplicacion = app;

        // Se añade el nodo a la raíz
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece la posición correspondiente en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.MENU_DESPLAZAMIENTO_X, ConstantesGUI.MENU_DESPLAZAMIENTO_Y));

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CONTROLES_ANCHO, ConstantesGUI.CONTROLES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crea un canvas en el nuevo nodo para representar el menú
        this.canvas = new Canvas( ConstantesGUI.CONTROLES_ANCHO, ConstantesGUI.CONTROLES_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        this.fondo = new Image(MenuGUIFondo.class.getResource(imagen).toString());
        
        // Se crea el registro del medio
        this.registroGUI = new RegistroGUI(this.nodo);

        // Se crea la representación de los jugadores (parte derecha)
        this.jugadoresGUI = new JugadoresGUI(this.nodo, juego, tableroGUI, this);

        this.botonera = new BotoneraGUI(this.nodo, app, this);

        this.numJugadores = 0;

        this.proponiendoTrato = false;
        this.estarDandoEnTrato = false;

        this.jugadorProponerTrato = null;
        this.siguientePaso = false;
        this.estarDandoDinero = false;
        this.faseDinero = false;
        this.faseNoAlquiler = false;

        this.casillasAuxiliar = new ArrayList<>();
        this.inputActivo = false;

        for(BotonGUI boton : getBotonera().getBotones()){
            if(boton.getFuncion().equals(TipoFuncion.aceptar)){
                this.botonGUI = boton;
                break;
            }
        }

        this.renderMarco = true;
    }

    public BotoneraGUI getBotonera() {
        return botonera;
    }

    public boolean isRenderMarco() {
        return renderMarco;
    }

    public void setRenderMarco(boolean renderMarco) {
        this.renderMarco = renderMarco;
    }

    public void anadirJugador(Jugador jugador){
        if(jugador == null){
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }
        getJugadoresGUI().nuevoJugador(jugador);
    }

    public boolean isInputActivo() {
        return inputActivo;
    }

    public void setInputActivo(boolean inputActivo) {
        this.inputActivo = inputActivo;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public boolean isProponiendoTrato() {
        return proponiendoTrato;
    }

    public boolean isSiguientePaso() {
        return siguientePaso;
    }

    public Trato getTrato() {
        return trato;
    }

    public void setTrato(Trato trato) {
        this.trato = trato;
    }

    public BotonGUI getBotonGUI() {
        return botonGUI;
    }

    public void setBotonGUI(BotonGUI botonGUI) {
        this.botonGUI = botonGUI;
    }

    public void setSiguientePaso(boolean siguientePaso) {
        this.siguientePaso = siguientePaso;
    }

    public ArrayList<CasillaGUI> getCasillasAuxiliar() {
        return casillasAuxiliar;
    }

    public boolean isFaseDinero() {
        return faseDinero;
    }

    public void setFaseDinero(boolean faseDinero) {
        this.faseDinero = faseDinero;
    }

    public boolean isFaseNoAlquiler() {
        return faseNoAlquiler;
    }

    public void setFaseNoAlquiler(boolean faseNoAlquiler) {
        this.faseNoAlquiler = faseNoAlquiler;
    }

    public boolean isEstarDandoDinero() {
        return estarDandoDinero;
    }

    public void setEstarDandoDinero(boolean estarDandoDinero) {
        this.estarDandoDinero = estarDandoDinero;
    }

    public void setProponiendoTrato(boolean proponiendoTrato) {
        this.proponiendoTrato = proponiendoTrato;
    }

    public boolean isEstarDandoEnTrato() {
        return estarDandoEnTrato;
    }

    public void setEstarDandoEnTrato(boolean estarDandoEnTrato) {
        this.estarDandoEnTrato = estarDandoEnTrato;
    }

    public Jugador getJugadorProponerTrato() {
        return jugadorProponerTrato;
    }

    public void setJugadorProponerTrato(Jugador jugadorProponerTrato) {
        this.jugadorProponerTrato = jugadorProponerTrato;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public void setCasillasAuxiliar(ArrayList<CasillaGUI> casillasAuxiliar) {
        this.casillasAuxiliar = casillasAuxiliar;
    }

    public JugadoresGUI getJugadoresGUI() {
        return jugadoresGUI;
    }

    public Juego getJuego() {
        return juego;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        if(numJugadores < 0){
            System.err.println("Número de jugadores no puede ser negativo");
            System.exit(1);
        }
        this.numJugadores = numJugadores;
    }

    public RegistroGUI getRegistroGUI() {
        return registroGUI;
    }

    public void incrementarJugadores(int incremento){
        this.numJugadores += incremento;
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if (getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickIzquierdo(posicionX, posicionY);
        } else if (getBotonera().contienePosicion(posicionX, posicionY)) {
            getBotonera().handleClickIzquierdo(posicionX, posicionY);
        }
    }

    public void handleClickPulsado(double x, double y){
        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if( getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickPulsado(posicionX, posicionY);
        } else if(getBotonera().contienePosicion(posicionX, posicionY)){
            getBotonera().handleClickPulsado(posicionX, posicionY);
        }
    }

    public void handleClickSoltado(double x, double y){
        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if( getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickSoltado(posicionX, posicionY);
        } else if(getBotonera().contienePosicion(posicionX, posicionY)){
            getBotonera().handleClickSoltado(posicionX, posicionY);
        }
    }

    public void render(double t){

        if(getJuego().getNombresJugadores().size() > getNumJugadores()){
            int jugadoresNuevos = getJuego().getNombresJugadores().size() - getNumJugadores();

            for(int i = getNumJugadores(); i < getJuego().getNombresJugadores().size(); i++){
                anadirJugador(getJuego().getJugador(getJuego().getNombresJugadores().get(i)));
            }

            incrementarJugadores(jugadoresNuevos);
        }

        // Se muestra la imagen
        getGc().drawImage(getFondo(), 0, 0);
        getJugadoresGUI().render();
        getBotonera().render(t);

        // En caso de que se haya activado el siguiente paso
        if(isSiguientePaso()){
            // Si se está proponiendo un trato
            if(isProponiendoTrato()){

                // Como se está realizando un trato el renderizando del marco se va a desactivar
                setRenderMarco(false);

                // Se deseleccionan todas las casillas del anterior trato
                for(CasillaGUI casillaGUI : getCasillasAuxiliar()){
                    casillaGUI.setSeleccionada(false);
                }

                // Se actualiza el ArrayList
                setCasillasAuxiliar(new ArrayList<>());

                // Si está en la fase del dinero
                if(isFaseDinero()){

                    // Si en el trato estaba dando ahora tocará recibir
                    if(isEstarDandoDinero()){

                        setEstarDandoDinero(false);
                        new CambiarRecibirTrato(getTrato(), this);

                    } else {

                        // Esta es la última parte del trato y por lo tanto se procede a crearlo y procesarlo.
                        Integer entero = 0;

                        // Se ha finalizado la parte del input
                        setInputActivo(false);

                        getBotonera().setDineroRecibir(entero);

                        setEstarDandoDinero(false);

                        ConsolaInterfaz.imprimir("Trato enviado a " + getJugadorProponerTrato().getNombre() + " por parte de " + getJuego().getTurno().getNombre());

                        getAplicacion().getJuego().incrementarNumTratos(1);

                        String idTrato = "trato" + getAplicacion().getJuego().getNumTratos();

                        //No se hace esto ya que se añaden en el propio trato al irse añadiendo desde el handler de la propiedad
                        //getTrato().setInmunidades(getBotonera().getInmunidades());
                        getTrato().setPropiedadesDar(getBotonera().getCasillasDar());
                        getTrato().setPropiedadesRecibir(getBotonera().getCasillasRecibir());
                        getTrato().setId(idTrato);

                        getAplicacion().getJuego().getTurno().getTratosEmitidos().put(trato.getId(), trato);
                        try {
                            getJugadorProponerTrato().proponerTrato(trato);
                        } catch (NoSerPropietarioException e) {
                            ConsolaInterfaz.imprimir(e.getMessage());
                        }

                        // Se finaliza el trato y se vuelve el botón de aceptar en la botonera
                        getBotonera().anadirBoton(getBotonGUI());

                        // Como la proposición del trato finaliza, se vuelve a activar el render del marco
                        setRenderMarco(true);
                        setProponiendoTrato(false);
                    }
                } else {
                    // Si en el trato se estaba dando ahora tocará recibir
                    if (isEstarDandoEnTrato()) {
                        // Como esta es la primera parte que se ejecuta del trato, se crea el trato nuevo entre los
                        // jugadores
                        setTrato(new Trato(getJuego().getTurno(), getJugadorProponerTrato()));
                        System.out.println(getBotonera().getCasillasDar());
                        setEstarDandoEnTrato(false);
                    } else {
                        // Si está en falso tocará ir a la fase del noalquier
                        System.out.println(getBotonera().getCasillasRecibir());
                        if(isFaseNoAlquiler()){
                            // Si se estaba en la fase de no alquiler, tocará ir a la fase de dinero
                            setFaseDinero(true);
                            setEstarDandoDinero(true);
                            setFaseNoAlquiler(false);
                            new CambiarDarTrato(getTrato(), this);

                            // Ahora se elimina el botón aceptar para que no se pueda continuar sin introducir el valor
                            getBotonera().eliminarBoton(getBotonGUI());
                        } else {
                            setFaseNoAlquiler(true);
                        }
                    }
                }
            }

            setSiguientePaso(false);
        }

    }


}
