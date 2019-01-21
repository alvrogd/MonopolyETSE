package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import aplicacionGUI.informacion.tableroGUI.ColorCasillaGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import java.util.ArrayList;
import java.util.HashSet;

import aplicacionGUI.menuGUI.MenuGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.tratos.Inmunidad;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.avatares.modoAvanzado.AnimacionAvataresModoAvanzado;
import resources.casillas.FondosCasillas;

public class CasillaGUI {

    /* Atributos */
    
    // Representación del tablero asociada
    private final TableroGUI tableroGUI;
    
    // Nodo propiedad de la casilla
    private final Group nodo;
    
    // Canvas contenido en el nodo
    private final Canvas canvas;
    
    // Contexto en el que representar objetos
    private final GraphicsContext gc;
    
    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.CASILLA_ANCHO;
    private final int alto = ConstantesGUI.CASILLA_ALTO;
    
    // Desplazamiento dado de la casilla
    private final int desplazamientoX;
    private final int desplazamientoY;

    // La casilla asociada
    private Casilla casilla;
    
    // Imagen de fondo de la casilla asociada
    private Image fondo;
    
    // Sensor de la casilla
    private Rectangle sensor;
    
    // Animación de movimiento avanzado para los avatares
    private final static ImagenAnimada ANIMACION_MODO_AVANZADO = new ImagenAnimada(new AnimacionAvataresModoAvanzado(),
            ConstantesGUI.AVATARES_AVANZADO_FRAMES, 0.25);
    
    // Menú contextual mostrado
    private ContextMenu menu;

    // Booleano seleccionada para saber si en la gestión de los tratos la casilla ha sido seleccionada.
    private boolean seleccionada;
    
    
    
    /* Constructor */
    
    public CasillaGUI(TableroGUI tableroGUI, Group raiz, Casilla casilla, String ficheroFondo, int posicionX,
            int posicionY) {
        
        if( tableroGUI == null ) {
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }
        
        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        if (ficheroFondo == null) {
            System.err.println("Nombre del fichero de fondo no inicializado");
            System.exit(1);
        }
        
        // Se registra la representación del tablero a la que pertenece
        this.tableroGUI = tableroGUI;
        
        // Se añade al nodo dado un nuevo nodo de uso para la casilla
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        // Se establece su correspondiente posición en la ventana
        this.desplazamientoX = posicionX;
        this.desplazamientoY = posicionY;
        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));
        
        // Se crea un canvas en el nuevo nodo para representar la casilla
        this.canvas = new Canvas( ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.nodo.getChildren().add(canvas);
        
        // Se genera un contexto a partir del canvas para insertar la representación de la casilla
        this.gc = this.canvas.getGraphicsContext2D();
        
        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se guarda la referencia a la casilla asociada
        this.casilla = casilla;
        
        // Se obtiene el fondo correspondiente
        this.fondo = new Image(FondosCasillas.class.getResource(ficheroFondo).toString());
        
        // Se genera el menú correspondiente
        this.menu = null;
        this.seleccionada = false;
    }

    
    
    /* Getters y setters */
           
    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    
    public int getAncho() {
        return ancho;
    }

    
    public int getAlto() {
        return alto;
    }

    
    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Image getFondo() {
        return fondo;
    }

    public void setFondo(Image fondo) {
        this.fondo = fondo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    
    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    
    public Group getNodo() {
        return nodo;
    }

    
    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    
    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    
    public static ImagenAnimada getANIMACION_MODO_AVANZADO() {
        return ANIMACION_MODO_AVANZADO;
    }

    public ContextMenu getMenu() {
        return menu;
    }

    public void setMenu(ContextMenu menu) {
        this.menu = menu;
    }
    
    
    
    /* Métodos */
    
    public boolean contienePosicion(double x, double y) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        return(getSensor().contains(posicionX, posicionY));
    }

    public void handleProponiendoTratos(double x, double y){

        // Se comprueba que esta casilla sea una propiedad
        if(getCasilla() instanceof Propiedad){
            Propiedad propiedad = (Propiedad) getCasilla();

            // En caso de que está en la fase del dinero, a la casilla no le incumbe y se corta el flujo
            if(getTableroGUI().getInformacion().getMenuGUI().isFaseDinero()){
                return;
            }

            // En caso de estar en la fase de noalquiler
            if(getTableroGUI().getInformacion().getMenuGUI().isFaseNoAlquiler()){
                if(propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())){
                    if(isSeleccionada()){
                        int contador = 0;
                        // En caso de estar ya seleccionada, es que se está deseleccionando, por lo tanto se quita la casilla
                        for(Inmunidad inmunidad : getTableroGUI().getInformacion().getMenuGUI().getBotonera().getInmunidades()){
                            if(inmunidad.getPropiedad().equals(propiedad)){
                                getTableroGUI().getInformacion().getMenuGUI().getBotonera().getInmunidades().remove(contador);
                                break;
                            }
                            contador++;
                        }
                        // Se modifica la seleccion
                        setSeleccionada(false);
                    } else {
                        getTableroGUI().getInformacion().getMenuGUI().getBotonera().getInmunidades().add(new Inmunidad((Propiedad)getCasilla(), Aplicacion.consola.leer("Número de turnos", true)));
                        setSeleccionada(true);
                    }
                }
            } else {

                //En caso de que se estén dando casillas, se comprueba que esta sea del jugador que tiene el turno
                if (getTableroGUI().getInformacion().getMenuGUI().isEstarDandoEnTrato()) {
                    if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJuego().getTurno())) {
                        if (isSeleccionada()) {
                            // En caso de estar ya seleccionada, es que se está deseleccionando, por lo tanto se quita la casilla
                            getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasDar().remove((Propiedad) getCasilla());
                            // Se modifica la seleccion
                            setSeleccionada(false);
                        } else {
                            getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasDar().add((Propiedad) getCasilla());
                            setSeleccionada(true);
                        }
                    }
                } else {
                    // Se comprueba que la propiedad seleccionada pertenezca al jugador
                    if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())) {
                        if (isSeleccionada()) {
                            getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasRecibir().remove((Propiedad) getCasilla());
                            setSeleccionada(false);
                        } else {
                            getTableroGUI().getInformacion().getMenuGUI().getBotonera().getCasillasRecibir().add((Propiedad) getCasilla());
                            setSeleccionada(true);
                        }
                    }
                }
            }
        }
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(getTableroGUI().getInformacion().getMenuGUI().isProponiendoTrato()){
            handleProponiendoTratos(posicionX, posicionY);
        }
    }
    
    
    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus, Aplicacion app) {
        
        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();
        
        System.out.println(getCasilla().getNombre());
        
        // Se genera el menú
        setMenu(generarMenuContextual(app));
        
        // Se muestra el menú
        getMenu().show(nodoRaiz, e.getScreenX(), e.getScreenY());
        
        // Se añade al listado de menús activos
        menus.add(getMenu());
    }
    
    
    public void render(double t) {

        clear();
        renderFondo();
        renderNombre();
        renderContenido(t);

        if(getCasilla() instanceof Propiedad) {

            Propiedad propiedad = (Propiedad) getCasilla();

            if (getTableroGUI().getInformacion().getMenuGUI().isProponiendoTrato()) {

                if (getTableroGUI().getInformacion().getMenuGUI().isEstarDandoEnTrato()) {

                    if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJuego().getTurno())) {
                        renderRecuadro();
                        if (isSeleccionada()) {
                            getTableroGUI().getInformacion().getMenuGUI().getCasillasAuxiliar().add(this);
                            renderRecuadroRelleno();
                        }
                    }

                } else {
                    if (propiedad.getPropietario().equals(getTableroGUI().getInformacion().getMenuGUI().getJugadorProponerTrato())) {
                        renderRecuadro();
                        if (isSeleccionada()) {
                            getTableroGUI().getInformacion().getMenuGUI().getCasillasAuxiliar().add(this);
                            renderRecuadroRelleno();
                        }
                    }
                }
            }
        }
    }

    public void renderRecuadro(){
        // Cambiar a partir de este momento el color de las líneas a negro
        getGc().setStroke(Color.BLACK);
        // Cambiar a partir de este momento el grosor de las líneas a 1 puntos
        getGc().setLineWidth(1);
        // Dibujar un rectángulo vacio
        getGc().strokeRoundRect(ConstantesGUI.CASILLA_ANCHO-25, ConstantesGUI.CASILLA_ALTO-25, 20, 20, 5, 5);
    }

    public void renderRecuadroRelleno(){
        renderRecuadro();
        // Cambiar a partir de este momento el color de relleno a verde
        getGc().setFill(Color.GREEN);
        // Dibujar un rectángulo con bordes redondeados a partir de la posición
        getGc().fillRoundRect(ConstantesGUI.CASILLA_ANCHO-25, ConstantesGUI.CASILLA_ALTO-25, 20, 20, 5, 5);
    }
    
    
    public void renderFondo() {

        // Se añade la imagen
        getGc().drawImage(getFondo(), 0, 0);
    }

    
    public void renderNombre() {

        // Se añade el color a la casilla en la posición del nombre
        getGc().setStroke(Color.TRANSPARENT);
        
        if(getCasilla() instanceof Propiedad){
            getGc().setFill(ColorCasillaGUI.tipoColorToColorTransparente(((Propiedad) getCasilla()).getGrupo().getTipo(
                    ).getColor()));
        }
        
        else {
            getGc().setFill(Color.rgb(128, 128, 128, 0.85));
        }
        
        getGc().fillRect(3, 3, getAncho() - 6, 14);

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);

        // Se añade el nombre de la casilla (la posición es la parte central inferior)
        getGc().fillText(getCasilla().getNombre(), ancho / 2, 14);
    }

    
    public void renderContenido(double t) {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        getGc().setFill(Color.rgb(128, 128, 128, 0.6));
        getGc().fillRect(3, 19, ancho - 6, 43);

        // Se renderiza el contenido
        renderAvataresContenidos(t);
    }


    public void clear() {

        getGc().clearRect(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
    }

    
    public void renderAvataresContenidos(double t) {

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            getGc().drawImage(getTableroGUI().getRepresentacionesAvatares().get(avatar.getIdentificador()), 6 +
                    desplazamiento, 22);
            
            // Y, si se encuentra en modo avanzado, se añade la animación
            if( !avatar.isMovimientoEstandar()) {
                getGc().drawImage(getANIMACION_MODO_AVANZADO().getFrame(t), 3 + desplazamiento, 20);
            }

            desplazamiento += 18;
        }
    }
    
    
    public ContextMenu generarMenuContextual(Aplicacion app) {
        
        // Se crea el menú de opciones para la casilla
        ContextMenu menu = new ContextMenu();
        
        // Se obtienen las funciones propias a la casilla
        HashSet<TipoFuncion> funciones = getCasilla().funcionesARealizar();
        
        if( funciones.contains(TipoFuncion.describir)) {
            
            // Se añade la opción de describir
            MenuItem item1 = new MenuItem( "Describir" );
            item1.setOnAction(new EventHandler<ActionEvent>() {
            
                @Override
                public void handle( ActionEvent event ) {
                    System.out.println(getCasilla().toString());
                }
            });
            
            // Se añade la opción al menú
            menu.getItems().add(item1);
        }
        
        return( menu );
    }
}
