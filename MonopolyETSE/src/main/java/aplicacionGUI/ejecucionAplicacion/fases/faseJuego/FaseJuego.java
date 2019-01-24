package aplicacionGUI.ejecucionAplicacion.fases.faseJuego;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacion.salidaPantalla.Output;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.InformacionCasillaGUI;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers.ClickIzquierdo;
import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers.Pulsacion;
import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.handlers.Release;
import aplicacionGUI.informacion.Informacion;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.jerarquiaCasillas.InformacionCasilla;

import java.util.ArrayList;
import java.util.Map;

public class FaseJuego extends Fase {

    /* Atributos */

    // Aplicación de Monopoly sobre la que ejecutar el juego
    private Aplicacion app;

    // Sección superior de la GUI
    private Informacion informacion;

    // Sección de controles de la GUI
    private MenuGUI menuGUI;



    /* Constructor */

    /**
     * Se crea una instancia que relaciona la aplicación en ventana con el juego del Monopoly
     *
     * @param aplicacionGUI aplicación gráfica asociada
     */
    public FaseJuego(AplicacionGUI aplicacionGUI) {

        super(aplicacionGUI, "fondo.jpg");

    }



    /* Getters y setters */

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



    /* Métodos */

    /**
     * Se inicia una partida de Monopoly, creando tanto el juego como los elementos gráficos
     */
    public void iniciar() {

        // Se crea un juego de Monopoly, en función de si el usuario a creado o no un tablero personalizado
        if (getAplicacionGUI().getTableroPersonalizado() != null) {
            setApp(new Aplicacion(convertirInformacion()));
        } else {
            setApp(new Aplicacion());
        }

        // Se crean los jugadores
        for( Map.Entry<String, TipoAvatar> entrada : getAplicacionGUI().getJugadoresCreados().entrySet() ) {

            try {
                getApp().introducirComando("crear jugador " + entrada.getKey() + " " + entrada.getValue().toString());
            } catch (MonopolyETSEException e) {
                System.err.println(e.toString());
            }
        }

        // Se inicia el juego
        try {
            getApp().introducirComando("iniciar");
        } catch (MonopolyETSEException e) {
            System.err.println(e.toString());
        }

        // todo quitar pruebas
        //pruebas1();

        // Se crea la sección superior de la GUI, encargada de representar información como el tablero del juego
        setInformacion(new Informacion(getRaiz(), getApp().getJuego().getTablero(),
                getAplicacionGUI().getTableroPersonalizado()));

        // Se crea la sección inferior de la GUI, encargada de representar los controles y los jugadores
        setMenuGUI(new MenuGUI(getRaiz(), getApp(), "fondo.png", getInformacion().getTableroGUI()));

        // Se guarda la sección de controles en la sección de información (necesario por los tratos)
        getInformacion().setMenuGUI(menuGUI);

        // todo quitar pruebas
        //pruebas2();

        // Se inicializa la información del marco de información
        ArrayList<String> informacion = new ArrayList<>();
        informacion.add("");
        getInformacion().getMarcoInformacion().actualizarContenido(informacion);

        getMenuGUI().getRegistroGUI().actualizarContenido("");

        // Se define la acción ante un click izquierdo
        getEscena().setOnMouseClicked(new ClickIzquierdo(this));

        // Se define la acción al presionar un botón del ratón
        getEscena().setOnMousePressed(new Pulsacion(this));

        // Se define la acción al soltar un botón del ratón
        getEscena().setOnMouseReleased(new Release(this));

        // Se indica que se ha inicializado
        setIniciado(true);
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
            System.err.println(e.toString());
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

        informacion.getMarcoInformacion().actualizarContenido(Output.toArrayString(
                "El Ministerio de Magia te investiga por colaboración con los mortífagos.\n"+
                "Ve a Azkaban. Ve directamente sin pasar por la casilla de Salida y sin cobrar los 2M€.\n"+
                "Y esto son más líneas de prueba para comprobar cómo se adapta el marco a distintos tamaños.\n"));
        // Se activa
        informacion.getMarcoInformacion().setActivo(true);
    }

    /**
     * Recoge de la aplicación gráfica asociada el tablero personalizado creado por el usuario y exporta la información
     * de clases InformacionCasillaGUI a InformacionCasilla
     *
     * @return información convertida
     */
    private ArrayList<InformacionCasilla> convertirInformacion() {

        ArrayList<InformacionCasilla> resultado = new ArrayList<>();

        for(InformacionCasillaGUI informacionCasillaGUI : getAplicacionGUI().getTableroPersonalizado()) {

            resultado.add(new InformacionCasilla(informacionCasillaGUI.getTipoCasilla(),
                    informacionCasillaGUI.getNombre(), informacionCasillaGUI.getGrupo(), informacionCasillaGUI.getImporte()));
        }

        return(resultado);
    }

    /**
     * Se renderiza el juego
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

        if (isIniciado()) {

            // Se renderizan los elementos
            getGc().drawImage(getFondo(), 0, 0);
            getInformacion().render(t);
            getMenuGUI().render(t);

            // Si existe algún input activo, se renderiza el primero
            if (getInputsActivos().size() > 0) {
                getInputsActivos().get(0).render();
            }
        }

    }

    /**
     * Se limpia el GC del juego
     */
    public void clear() {
        // Se limpia la ventana
        getGc().clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);
    }
}
