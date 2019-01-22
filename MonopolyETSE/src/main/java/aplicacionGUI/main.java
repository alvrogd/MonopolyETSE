package aplicacionGUI;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacionGUI.editor.Editor;
import aplicacionGUI.informacion.Informacion;
import aplicacionGUI.input.Input;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.fondo.Fondo;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alvrogd
 */
public class main extends Application {
    
    public static void main( String args[] ) {
        
        launch();
    }
    
    @Override
    public void start( Stage ventana ) {
        
        // Nombre de la ventana
        ventana.setTitle( "MonopolyETSE GUI Casilla" );
        
        // Fondo de la ventana
        Image fondo = new Image(Fondo.class.getResource("fondo.jpg").toString());
        
        // Se crea un nodo raíz
        Group raiz = new Group();
        // Se añade a una escena nueva
        Scene escena = new Scene( raiz );
        // Se añade la escena a la ventana
        ventana.setScene( escena );
        // Se establece un estilo personalizado para la escena (para el registro exclusivamente)
        escena.getStylesheets().add(ConstantesGUI.class.getResource("EstilosCSS.css").toExternalForm());
                               
        // Se crea un canvas en el que representar la GUI y se añade a la raíz
        // todo al final creo que este canvas será completamente innecesario si la parte de arriba y la de abajo tienen
        // sus respectivos canvas
        Canvas canvas = new Canvas( ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO );
        raiz.getChildren().add(canvas);
        
        // Se crea un entorno que manipular a partir del canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Se crea un array en el que registrar menús contextuales abiertos
        ArrayList<ContextMenu> menus = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int opcion = scanner.nextInt();

        if( opcion == 1 ) {
            probarJuego(raiz, ventana, escena, gc, fondo, menus);
        }

        else {
            probarEditor(raiz, ventana, escena, gc, fondo, menus);
        }
    }

    private void probarJuego(Group raiz, Stage ventana, Scene escena, GraphicsContext gc, Image fondo, ArrayList<ContextMenu> menus) {

        // Conjunto de inputs activos
        ArrayList<Input> inputsActivos = new ArrayList<>();
        Input.setInputsActivos(inputsActivos);

        // Raíz de los inputs
        Input.setRaiz(raiz);

        // Se crea una aplicación Monopoly
        Aplicacion app;

        try {

            app = new Aplicacion();
            //app.getMenu().iniciarAplicacion();
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

            // Se crea la sección superior de la GUI, encargada de representar información como el tablero del juego
            Informacion informacion = new Informacion(raiz, app.getJuego().getTablero());

            MenuGUI menuGUI = new MenuGUI(raiz, app, "fondo.png", informacion.getTableroGUI());

            informacion.setMenuGUI(menuGUI);

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

            //todo Lo pongo así por el tema de las inner classes, es la solución que me dio el intellij JAJAJA
            final double[] xPresionado = {0};
            final double[] yPresionado = {0};

            // Se define la acción ante un click derecho
            // todo lo pongo de este modo puesto que es más fácil de modificar para hacer pruebas; la intención sería
            // crear más adelante la clase aparte porque a Penín no le ha de de parecer buena idea
            escena.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent e ) {

                    double x = e.getX();
                    double y = e.getY();


                }
            });

            // Se define la acción ante un click izquierdo
            escena.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle( MouseEvent e ) {

                    double x = e.getX();
                    double y = e.getY();

                    if(menuGUI.contienePosicion(x, y)){
                        //Solo en caso de que el botón presionado sea el primario (izquierdo)
                        if(e.getButton().equals(MouseButton.PRIMARY)) {
                            menuGUI.handleClickIzquierdo(x, y);
                        }
                    } else if(informacion.contienePosicion(x, y)){
                        if(e.getButton().equals(MouseButton.PRIMARY)) {
                            informacion.handleClickIzquierdo(x, y);
                        } else if(e.getButton().equals(MouseButton.SECONDARY)){
                            informacion.handleClickDerecho(x, y, raiz, e, menus, app);
                        }
                    }
                }
            });

            escena.setOnMousePressed(new EventHandler<MouseEvent>(){

                @Override
                public void handle( MouseEvent e ){

                    double x = e.getX();
                    double y = e.getY();

                    xPresionado[0] = x;
                    yPresionado[0] = y;

                    for( ContextMenu contextMenu : menus ) {
                        contextMenu.hide();
                    }

                    menus.clear();

                    if(menuGUI.contienePosicion(x, y)){
                        menuGUI.handleClickPulsado(x, y);
                    }

                    if(inputsActivos.size() > 0 ) {
                        if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                            inputsActivos.get(0).handlePulsacion();
                        }
                    }
                }
            });

            escena.setOnMouseReleased(new EventHandler<MouseEvent>(){

                @Override
                public void handle( MouseEvent e ){

                    //Se utiliza xPresionado para que en vez de detectar la acción en la posición donde se suelta el click
                    //lo detecte en la posición donde se empezó a presionar
                    if(menuGUI.contienePosicion(xPresionado[0], yPresionado[0])){
                        menuGUI.handleClickSoltado(xPresionado[0], yPresionado[0]);
                    }

                    /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
                        informacion.handleClickDerecho(xPresionado[0], yPresionado[0], raiz, e, menus);
                    }*/

                    /*if( informacion.contienePosicion(xPresionado[0], yPresionado[0])) {
                        informacion.handleClickIzquierdo(xPresionado[0], yPresionado[0]);
                    }*/

                    if(inputsActivos.size() > 0 ) {
                        if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                            inputsActivos.get(0).handleRelease();
                        }
                    }

                }
            });

            // Se registra el momento de inicio del juego
            final long tiempoInicio = System.nanoTime();

            // Se inicia el game loop
            new AnimationTimer() {

                @Override
                public void handle( long currentNanoTime ){

                    // Tiempo que ha transcurrido desde el inicio del juego
                    double t = (currentNanoTime - tiempoInicio) / 1000000000.0;

                    // Clear
                    gc.clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

                    // Render
                    gc.drawImage(fondo, 0, 0);
                    informacion.render(t);
                    menuGUI.render(t);

                    if(inputsActivos.size() > 0 ) {
                        inputsActivos.get(0).render();
                    }
                }
            }.start();

            // Se muestra la ventana
            ventana.show();
        }

        catch( MonopolyETSEException e ) {
            e.toString();
        }
    }

    private void probarEditor(Group raiz, Stage ventana, Scene escena, GraphicsContext gc, Image fondo, ArrayList<ContextMenu> menus) {

        // Conjunto de inputs activos
        ArrayList<Input> inputsActivos = new ArrayList<>();
        Input.setInputsActivos(inputsActivos);

        // Raíz de los inputs
        Input.setRaiz(raiz);

        // Se crea un editor
        Editor editor = new Editor(raiz);

        final double[] xPresionado = {0};
        final double[] yPresionado = {0};

        escena.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle( MouseEvent e ){

                double x = e.getX();
                double y = e.getY();

                xPresionado[0] = x;
                yPresionado[0] = y;

                for( ContextMenu contextMenu : menus ) {
                    contextMenu.hide();
                }

                menus.clear();

                if(inputsActivos.size() > 0 ) {
                    if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                        inputsActivos.get(0).handlePulsacion();
                    }
                }
            }
        });

        escena.setOnMouseReleased(new EventHandler<MouseEvent>(){

            @Override
            public void handle( MouseEvent e ){

                //Se utiliza xPresionado para que en vez de detectar la acción en la posición donde se suelta el click
                //lo detecte en la posición donde se empezó a presionar
                if(editor.contienePosicion(xPresionado[0], yPresionado[0])){
                    editor.handleClick(xPresionado[0], yPresionado[0], raiz, e, menus);
                }

                if(inputsActivos.size() > 0 ) {
                    if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                        inputsActivos.get(0).handleRelease();
                    }
                }
            }
        });

        // Se registra el momento de inicio del juego
        final long tiempoInicio = System.nanoTime();

        // Se inicia el game loop
        new AnimationTimer() {

            @Override
            public void handle( long currentNanoTime ){

                // Tiempo que ha transcurrido desde el inicio del juego
                double t = (currentNanoTime - tiempoInicio) / 1000000000.0;

                // Clear
                gc.clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

                // Render
                gc.drawImage(fondo, 0, 0);
                editor.render(t);

                if(inputsActivos.size() > 0 ) {
                    inputsActivos.get(0).render();
                }
            }
        }.start();

        // Se muestra la ventana
        ventana.show();
    }
}
