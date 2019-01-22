package aplicacionGUI.ejecucionJuego;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacionGUI.AplicacionGUI;
import aplicacionGUI.ejecucionJuego.handlers.ClickIzquierdo;
import aplicacionGUI.ejecucionJuego.handlers.Pulsacion;
import aplicacionGUI.ejecucionJuego.handlers.Release;
import aplicacionGUI.informacion.Informacion;
import aplicacionGUI.input.Input;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.fondo.Fondo;

import java.util.ArrayList;

public class EjecucionJuego {

    /* Atributos */

    // Nodo raíz de la aplicación
    private final Group raiz;

    // Ventana de la aplicación
    private final Stage ventana;

    // Escena de la aplicación
    private final Scene escena;

    // GC para representar el fondo de la aplicación
    private final GraphicsContext gc;

    // Fondo a representar
    private final Image fondo = new Image(Fondo.class.getResource("fondo.jpg").toString());

    // Conjunto de menús contextuales activos
    private final ArrayList<ContextMenu> menus;

    // Registro de inputs activos
    private final ArrayList<Input> inputsActivos;

    // Aplicación de Monopoly sobre la que ejecutar el juego
    private Aplicacion app;

    // Sección superior de la GUI
    private Informacion informacion;

    // Sección de controles de la GUI
    private MenuGUI menuGUI;

    // Posiciones de eventos de ratón
    private double xPresionado;
    private double yPresionado;



    /* Constructor */

    /**
     * Se crea una instancia que relaciona la aplicación en ventana con el juego del Monopoly
     *
     * @param aplicacionGUI aplicación gráfica asociada
     */
    public EjecucionJuego(AplicacionGUI aplicacionGUI) {

        if (aplicacionGUI == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        // Se inicializan los atributos a partir de aquellos de la aplicación
        this.raiz = aplicacionGUI.getRaiz();
        this.ventana = aplicacionGUI.getVentana();
        this.escena = aplicacionGUI.getEscena();
        this.gc = aplicacionGUI.getGc();
        this.menus = aplicacionGUI.getMenus();
        this.inputsActivos = aplicacionGUI.getInputsActivos();
    }



    /* Getters y setters */

    public Group getRaiz() {
        return raiz;
    }

    public Stage getVentana() {
        return ventana;
    }

    public Scene getEscena() {
        return escena;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public ArrayList<ContextMenu> getMenus() {
        return menus;
    }

    public ArrayList<Input> getInputsActivos() {
        return inputsActivos;
    }

    public Aplicacion getApp() {
        return app;
    }

    public void setApp(Aplicacion app) {
        this.app = app;
    }

    public Informacion getInformacion() {
        return informacion;
    }

    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public void setMenuGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
    }

    public double getxPresionado() {
        return xPresionado;
    }

    public void setxPresionado(double xPresionado) {
        this.xPresionado = xPresionado;
    }

    public double getyPresionado() {
        return yPresionado;
    }

    public void setyPresionado(double yPresionado) {
        this.yPresionado = yPresionado;
    }



    /* Métodos */

    /**
     * Se inicia una partida de Monopoly, creando tanto el juego como los elementos gráficos
     */
    public void iniciar() {

        // Se crea un juego de Monopoly
        setApp(new Aplicacion());

        // todo quitar pruebas
        pruebas1();

        // Se crea la sección superior de la GUI, encargada de representar información como el tablero del juego
        setInformacion(new Informacion(getRaiz(), getApp().getJuego().getTablero()));

        // Se crea la sección inferior de la GUI, encargada de representar los controles y los jugadores
        setMenuGUI(new MenuGUI(getRaiz(), getApp(), "fondo.png", getInformacion().getTableroGUI()));

        // Se guarda la sección de controles en la sección de información (necesario por los tratos)
        getInformacion().setMenuGUI(menuGUI);

        // todo quitar pruebas
        pruebas2();


        // Se define la acción ante un click izquierdo
        escena.setOnMouseClicked(new ClickIzquierdo(this));

        // Se define la acción al presionar un botón del ratón
        escena.setOnMousePressed(new Pulsacion(this));

        // Se define la acción al soltar un botón del ratón
        escena.setOnMouseReleased(new Release(this));

        // Se registra el momento de inicio del juego
        final long tiempoInicio = System.nanoTime();

        // Se inicia el game loop
        EjecucionJuegoLoop loop = new EjecucionJuegoLoop(this);
        loop.iniciar();

        // Se muestra la ventana
        ventana.show();
    }

    private void pruebas1() {

        final Aplicacion app = getApp();

        try {
            app.introducirComando("crear jugador alvaro coche");
            app.introducirComando("crear jugador fran pelota");
            app.introducirComando("iniciar");
            app.introducirComando("pasta 100000");
            app.introducirComando("mover 1");
            app.introducirComando("comprar Platform 9 3/4");
            app.introducirComando("mover 2");
            app.introducirComando("comprar Diagon Alley");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar hotel");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar hotel");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar pista");
            app.introducirComando("edificar piscina");
            app.introducirComando("cambiar modo");
        } catch (MonopolyETSEException e) {
            e.toString();
        }


    }

    private void pruebas2() {

        menuGUI.getRegistroGUI().actualizarContenido("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce id rutrum nulla. Sed ut porttitor leo. Aliquam pharetra placerat ipsum, a mattis magna fermentum in. Nunc sit amet mollis mauris, vehicula tristique enim. Sed vel egestas turpis, vitae fermentum tortor. Suspendisse gravida enim sit amet gravida efficitur. Nunc feugiat sagittis tortor id ultricies. Curabitur tempor erat nec tincidunt hendrerit. Pellentesque ullamcorper, felis id venenatis imperdiet, velit odio malesuada nunc, in convallis odio metus malesuada purus. Nullam in vehicula lectus. Praesent eu eros interdum, egestas nisl quis, consequat metus. Ut pellentesque quam vel nunc aliquet hendrerit. Praesent a magna bibendum, varius nisi sit amet, maximus mi. In aliquet porttitor leo, vel mattis diam accumsan vel.\n" +
                "\n" +
                "Nulla facilisi. Proin gravida diam et sodales facilisis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer commodo fringilla ultrices. Curabitur tempor sem lectus, tempus mattis sem aliquet non. Suspendisse condimentum eu diam eu tincidunt. Ut porttitor sapien at malesuada molestie. Aenean fermentum urna nec dolor malesuada, sed fringilla diam rhoncus. Proin ut mi nec eros mollis pellentesque. Suspendisse semper semper purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce ullamcorper augue ut ante congue rhoncus. Proin finibus sed mauris vel fringilla. Vestibulum porttitor id sapien sit amet dapibus. Fusce tempus dictum est non condimentum. Nullam ut velit diam.\n" +
                "\n" +
                "In malesuada ipsum facilisis, elementum sapien a, mattis augue. Maecenas tristique elementum pharetra. Morbi eu molestie tortor. Sed scelerisque egestas lorem vel lacinia. Aenean interdum ipsum id sapien blandit, at aliquam massa porttitor. Integer pretium viverra blandit. Aliquam sem risus, lobortis ut gravida luctus, semper in sapien. Nunc pellentesque, velit semper volutpat ultricies, massa nulla scelerisque ex, et mattis augue tellus non est. Ut cursus erat sit amet eleifend sodales. Nullam blandit mauris massa, rhoncus luctus massa pretium ac. Vestibulum egestas, eros eu sodales feugiat, elit nibh venenatis eros, vel interdum ex sapien non dolor. Pellentesque neque quam, tincidunt in ipsum et, varius porta dui. Sed imperdiet lacus elit, non pulvinar ex laoreet in. Fusce vitae auctor magna. Sed ut ipsum vel dui scelerisque pharetra. Praesent vel tincidunt ipsum, vitae facilisis lacus.\n" +
                "\n" +
                "Praesent pulvinar urna ac justo vestibulum, in varius magna tincidunt. Aenean ac diam et diam varius ultricies. Integer aliquet urna sed libero ultrices facilisis. Nulla tempor elit hendrerit urna tristique rhoncus. Ut dapibus ornare commodo. Phasellus in lectus ullamcorper, gravida massa id, feugiat erat. Suspendisse tortor elit, volutpat et metus nec, tincidunt gravida neque. Praesent porttitor augue sed placerat mattis. Integer ut finibus nunc. Aliquam tincidunt aliquam nunc eget scelerisque. Pellentesque sagittis odio sit amet metus accumsan consectetur. Ut et enim fermentum, maximus tortor id, porttitor quam. Nunc in diam scelerisque sapien sagittis dapibus. Nunc in turpis vitae lacus porta sodales. Nulla sit amet ultricies nisl. Praesent at sem et odio efficitur finibus.\n" +
                "\n" +
                "Donec ut ante ut dolor pharetra accumsan. Integer molestie aliquam consequat. Nullam eu fermentum tellus, nec lacinia velit. Mauris molestie odio ac metus euismod, faucibus dapibus nulla placerat. Suspendisse potenti. Morbi non risus ac justo interdum efficitur ac vitae lacus. Integer convallis feugiat felis eget consequat. Nullam in lacus at neque dignissim dapibus. Nam rutrum diam in viverra viverra.\n" +
                "\n" +
                "Phasellus tristique tortor sit amet justo aliquet, non congue mauris fermentum. Aliquam eu mollis orci. Aliquam dictum ac lacus et pulvinar. Nullam mattis erat urna, nec dapibus lectus laoreet vitae. Curabitur nulla ipsum, varius sit amet dapibus eget, vestibulum vel mauris. Praesent ut sapien tincidunt, venenatis orci vitae, viverra sem. Donec est urna, tincidunt quis enim nec, pretium vestibulum ligula. Ut imperdiet dolor id lacus euismod tristique. Proin sed urna consectetur, faucibus elit at, viverra mi. Sed aliquet orci sit amet libero mattis consequat vitae vel ante. Donec ac sapien mauris. Nullam ultrices neque non ultricies tempor. Morbi dictum mollis orci eget feugiat.\n" +
                "\n" +
                "Proin euismod tempor est, a blandit nunc hendrerit a. Vestibulum varius purus vitae lacus pharetra suscipit. In eget sem ex. In malesuada ante in mauris commodo, gravida feugiat lectus tincidunt. Mauris hendrerit eleifend mi a efficitur. Proin sit amet leo nec tellus viverra placerat. Praesent eget enim aliquet, faucibus eros at, elementum libero. Vestibulum iaculis vel purus in tincidunt. Suspendisse potenti. Integer tincidunt tortor ac est lacinia consequat.\n" +
                "\n" +
                "Aenean non volutpat risus. Pellentesque semper et odio et congue. Vestibulum ut felis turpis. Fusce rhoncus urna quis rhoncus sollicitudin. Etiam eget velit viverra, tempus libero in, hendrerit enim. In laoreet eget ligula ut vulputate. Aliquam erat volutpat.\n" +
                "\n" +
                "Nunc sodales pellentesque arcu. Donec faucibus pharetra augue id ultrices. Aliquam dignissim eleifend vestibulum. Nulla porttitor efficitur leo, eget interdum lorem ornare vel. Integer venenatis diam ac porttitor pretium. Integer consequat facilisis sem, ac ultricies mauris mattis nec. Praesent imperdiet, metus sit amet suscipit posuere, neque turpis mattis est, ac tincidunt mi orci nec ligula. Sed posuere in quam at lobortis. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed eu molestie tortor. Mauris vehicula nulla id fermentum pellentesque. Praesent dignissim eros ut eros facilisis, vel cursus mi luctus. Vivamus tincidunt, enim quis volutpat posuere, augue nisl tincidunt ligula, sit amet varius ante erat nec nibh. Suspendisse ut luctus magna. Pellentesque dapibus lectus eget enim cursus porta.\n" +
                "\n" +
                "Nullam iaculis varius urna id feugiat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras sagittis felis luctus, eleifend felis a, tempus quam. Etiam auctor libero nulla, ac pretium lorem semper et. Phasellus et ex sed tellus vestibulum aliquet nec vel tortor. Ut luctus, massa ac malesuada consectetur, ex lorem efficitur diam, ut faucibus lorem urna a metus. Nunc eros ante, tristique ac risus eu, ultricies aliquam lorem. Nunc id dictum nunc. Sed fermentum euismod leo ac rutrum. Duis ullamcorper lectus ac volutpat posuere. Sed ornare lacinia est quis dictum. Pellentesque ullamcorper in risus eget egestas. Fusce vel varius arcu.");

        menuGUI.getRegistroGUI().anadirContenido("adadasdasdadasdasd");

        // Se añade texto de prueba al marco de información
        informacion.getMarcoInformacion().actualizarContenido(new String[]{
                "El Ministerio de Magia te investiga por colaboración con los mortífagos.",
                "Ve a Azkaban. Ve directamente sin pasar por la casilla de Salida y sin cobrar los 2M€.",
                "Y esto son más líneas de prueba para comprobar cómo se adapta el marco a distintos tamaños."});
        // Se activa
        informacion.getMarcoInformacion().setActivo(true);
    }
}
