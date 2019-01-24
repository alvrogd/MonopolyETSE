package aplicacionGUI;

public class ConstantesGUI {

    // Dimensiones de la ventana
    public final static int VENTANA_ANCHO = 1920;
    public final static int VENTANA_ALTO = 1000;

    // Dimensiones de la sección de información
    public final static int INFORMACION_ANCHO = 1650;
    public final static int INFORMACION_ALTO = 715;

    // Dimensiones de la sección del tablero
    public final static int TABLERO_ANCHO = 1650;
    public final static int TABLERO_ALTO = 715;

    // Dimensiones de la sección de los controles
    public final static int CONTROLES_ANCHO = 1920;
    public final static int CONTROLES_ALTO = 270;

    // Desplazamientos de la sección de información
    public final static int INFORMACION_DESPLAZAMIENTO_X = (VENTANA_ANCHO - INFORMACION_ANCHO) / 2;
    public final static int INFORMACION_DESPLAZAMIENTO_Y = (VENTANA_ALTO - INFORMACION_ALTO - CONTROLES_ALTO) / 2;

    // Desplazamientos de la sección de menú
    public final static int MENU_DESPLAZAMIENTO_X = (VENTANA_ANCHO - CONTROLES_ANCHO)/2;
    public final static int MENU_DESPLAZAMIENTO_Y = (VENTANA_ALTO - CONTROLES_ALTO);

    // Desplazamientos para la sección de los botones
    public final static int BOTONES_DESPLAZAMIENTO_X = 0;
    public final static int BOTONES_DESPLAZAMIENTO_Y = 0;

    // Dimensiones de la sección de botones
    public final static int BOTONES_ANCHO = 480;
    public final static int BOTONES_ALTO = VENTANA_ALTO - MENU_DESPLAZAMIENTO_Y - 20;

    // Dimesiones de un botón
    public final static int BOTON_ANCHO = 75;
    public final static int BOTON_ALTO = 75;
    public final static int BOTON_SEPARACION_X = 15;
    public final static int BOTON_SEPARACION_Y = 3;
    public final static int NUM_FRAMES_CAMBIARMODO = 10;
    public final static String[] FRAMES_CAMBIARMODO = {"marcoBoton0.png",
                                                        "marcoBoton1.png",
                                                        "marcoBoton2.png",
                                                        "marcoBoton3.png",
                                                        "marcoBoton4.png",
                                                        "marcoBoton5.png",
                                                        "marcoBoton6.png",
                                                        "marcoBoton7.png",
                                                        "marcoBoton8.png",
                                                        "marcoBoton9.png",
                                                        "marcoBoton10.png"};

    public final static int BOTONES_POR_FILA = 5;
    public final static int BOTONES_COLUMNAS = 3;


    // Dimensiones de la sección de impresión
    public final static int IMPRESION_ANCHO = 768;
    public final static int IMPRESION_ALTO = BOTONES_ALTO;

    // Dimensiones de la sección de jugadores
    public final static int JUGADORES_ANCHO = 672;
    public final static int JUGADORES_ALTO = BOTONES_ALTO;
    public final static int JUGADORES_SEPARACION = 2;

    // Dimensiones de la barra del jugador
    public final static int BARRA_DESPLAZAMIENTO_X = 5;
    public final static int BARRA_DESPLAZAMIENTO_Y = 20;

    public final static int BARRA_DESPLAZAMIENTO_NOMBRE_X = 65;
    public final static int BARRA_DESPLAZAMIENTO_NOMBRE_Y = 19;

    public final static int BARRA_DESPLAZAMIENTO_DINERO_X = 465;
    public final static int BARRA_DESPLAZAMIENTO_DINERO_Y = 19;

    public final static int BARRA_DESPLAZAMIENTO_AVATAR_X = 7;
    public final static int BARRA_DESPLAZAMIENTO_AVATAR_Y = 9;

    public final static int BARRA_DESPLAZAMIENTO_BOTON_X = 620;
    public final static int BARRA_DESPLAZAMIENTO_BOTON_Y = 0;

    public final static int BARRA_DESPLAZAMIENTO_BOTON_DES_X = 590;
    public final static int BARRA_DESPLAZAMIENTO_BOTON_DES_Y = 0;

    public final static int BARRA_JUGADOR_ANCHO = 650;
    public final static int BARRA_JUGADOR_ALTO = 30;
    public final static String BARRA_NOMBRE = "barraJugador.png";
    public final static String BARRA_NOMBRE_TRATO_OSCURO= "barraJugadorTratoOscuro.png";
    public final static String BARRA_NOMBRE_DESCRIBIR_OSCURO = "barraJugadorDescribirOscuro.png";

    // Desplazamientos para la sección de impresión
    public final static int IMPRESION_DESPLAZAMIENTO_X = BOTONES_DESPLAZAMIENTO_X + BOTONES_ANCHO;
    public final static int IMPRESION_DESPLAZAMIENTO_Y = 0;

    // Desplazamientos para la sección de los jugadores
    public final static int JUGADORES_DESPLAZAMIENTO_X = IMPRESION_DESPLAZAMIENTO_X + IMPRESION_ANCHO;
    public final static int JUGADORES_DESPLAZAMIENTO_Y = 14;

    // Desplazamientos del tablero
    public final static int TABLERO_DESPLAZAMIENTO_X = (INFORMACION_ANCHO - TABLERO_ANCHO) / 2;
    public final static int TABLERO_DESPLAZAMIENTO_Y = (INFORMACION_ALTO - TABLERO_ALTO) / 2;

    // Dimensiones de las representaciones de las casillas
    public final static int CASILLA_ANCHO = 150;
    public final static int CASILLA_ALTO = 65;

    // Número de casillas por cada fila (4 filas con 40 casillas en total, comezando desde la casilla de Salida)
    public final static int CASILLAS_POR_FILA = 10;
    // Número de casillas por cada lado del tablero
    public final static int CASILLAS_POR_LADO = 11;

    // Número de lados del tablero
    public final static int NUMERO_LADOS = 4;

    // Nombres de las imágenes de las casillas
    public final static String[] CASILLAS_IMAGENES = {"Salida.png",
                                                      "Platform934.png",
                                                      "Comunidad.png",
                                                      "DiagonAlley.png",
                                                      "SmeagolsTax.png",
                                                      "HogwartsExpress.png",
                                                      "GodricsHollow.png",
                                                      "Suerte.png",
                                                      "Hogsmeade.png",
                                                      "Hogwarts.png",
                                                      "Azkaban.png",
                                                      "Tatooine.png",
                                                      "Servicio1.png",
                                                      "Alderaan.png",
                                                      "Coruscant.png",
                                                      "ImperialDestroyer.png",
                                                      "Endor.png",
                                                      "Comunidad.png",
                                                      "Hoth.png",
                                                      "Mustafar.png",
                                                      "DeathStar.png",
                                                      "Meereen.png",
                                                      "Suerte.png",
                                                      "CasterlyRock.png",
                                                      "Braavos.png",
                                                      "KingsLanding.png",
                                                      "Winterfell.png",
                                                      "NightsWatch.png",
                                                      "Servicio2.png",
                                                      "BeyondTheWall.png",
                                                      "GotoAzkaban.png",
                                                      "TheShire.png",
                                                      "Moria.png",
                                                      "Comunidad.png",
                                                      "HelmsDeep.png",
                                                      "Gwaihir.png",
                                                      "Suerte.png",
                                                      "Isengard.png",
                                                      "SmaugsTax.png",
                                                      "Mordor.png"};

    // Nombre del marco de las casillas
    public final static String CASILLAS_MARCO = "frameCasilla.png";

    // Nombres de las imágenes de los edificios
    public final static String CASA = "casa.png";
    public final static String HOTEL = "hotel.png";
    public final static String PISCINA = "piscina.png";
    public final static String PISTA = "pista.png";

    // Tamaño de las barajas
    public final static int BARAJA_ANCHO = 370;
    public final static int BARAJA_ALTO = 283;

    // Tamaño de las cartas
    public final static int CARTA_ANCHO = 170;
    public final static int CARTA_ALTO = 275;

    // Nombres de las imágenes de las barajas
    public final static String[] BARAJA_SUERTE = {"suerteBaraja0.png",
                                                    "suerteBaraja1.png",
                                                    "suerteBaraja2.png",
                                                    "suerteBaraja3.png",
                                                    "suerteBaraja4.png",
                                                    "suerteBaraja5.png",
                                                    "suerteBaraja6.png",
                                                    "suerteBaraja7.png",
                                                    "suerteBaraja8.png",
                                                    "suerteBaraja9.png",
                                                    "suerteBaraja10.png",
                                                    "suerteBaraja11.png",
                                                    "suerteBaraja12.png",
                                                    "suerteBaraja13.png",
                                                    "suerteBaraja14.png",
                                                    "suerteBaraja15.png",
                                                    "suerteBaraja16.png",
                                                    "suerteBaraja17.png",
                                                    "suerteBaraja18.png",
                                                    "suerteBaraja19.png",
                                                    "suerteBaraja20.png",
                                                    "suerteBaraja21.png",
                                                    "suerteBaraja22.png",
                                                    "suerteBaraja23.png",
                                                    "suerteBaraja24.png",
                                                    "suerteBaraja25.png",
                                                    "suerteBaraja26.png",
                                                    "suerteBaraja27.png",
                                                    "suerteBaraja28.png",
                                                    "suerteBaraja29.png",
                                                    "suerteBaraja30.png",
                                                    "suerteBaraja31.png",
                                                    "suerteBaraja32.png",
                                                    "suerteBaraja33.png",
                                                    "suerteBaraja34.png",
                                                    "suerteBaraja35.png",
                                                    "suerteBaraja36.png",
                                                    "suerteBaraja37.png",
                                                    "suerteBaraja38.png",
                                                    "suerteBaraja39.png",
                                                    "suerteBaraja40.png",
                                                    "suerteBaraja41.png",
                                                    "suerteBaraja42.png",
                                                    "suerteBaraja43.png",
                                                    "suerteBaraja44.png",
                                                    "suerteBaraja45.png",
                                                    "suerteBaraja46.png",
                                                    "suerteBaraja47.png",
                                                    "suerteBaraja48.png",
                                                    "suerteBaraja49.png",
                                                    "suerteBaraja50.png",
                                                    "suerteBaraja51.png",
                                                    "suerteBaraja52.png",
                                                    "suerteBaraja53.png"};

    public final static String[] BARAJA_COMUNIDAD = {"comunidadBaraja0.png",
                                                        "comunidadBaraja1.png",
                                                        "comunidadBaraja2.png",
                                                        "comunidadBaraja3.png",
                                                        "comunidadBaraja4.png",
                                                        "comunidadBaraja5.png",
                                                        "comunidadBaraja6.png",
                                                        "comunidadBaraja7.png",
                                                        "comunidadBaraja8.png",
                                                        "comunidadBaraja9.png",
                                                        "comunidadBaraja10.png",
                                                        "comunidadBaraja11.png",
                                                        "comunidadBaraja12.png",
                                                        "comunidadBaraja13.png",
                                                        "comunidadBaraja14.png",
                                                        "comunidadBaraja15.png",
                                                        "comunidadBaraja16.png",
                                                        "comunidadBaraja17.png",
                                                        "comunidadBaraja18.png",
                                                        "comunidadBaraja19.png",
                                                        "comunidadBaraja20.png",
                                                        "comunidadBaraja21.png",
                                                        "comunidadBaraja22.png",
                                                        "comunidadBaraja23.png",
                                                        "comunidadBaraja24.png",
                                                        "comunidadBaraja25.png",
                                                        "comunidadBaraja26.png",
                                                        "comunidadBaraja27.png",
                                                        "comunidadBaraja28.png",
                                                        "comunidadBaraja29.png",
                                                        "comunidadBaraja30.png",
                                                        "comunidadBaraja31.png",
                                                        "comunidadBaraja32.png",
                                                        "comunidadBaraja33.png",
                                                        "comunidadBaraja34.png",
                                                        "comunidadBaraja35.png",
                                                        "comunidadBaraja36.png",
                                                        "comunidadBaraja37.png",
                                                        "comunidadBaraja38.png",
                                                        "comunidadBaraja39.png",
                                                        "comunidadBaraja40.png",
                                                        "comunidadBaraja41.png",
                                                        "comunidadBaraja42.png",
                                                        "comunidadBaraja43.png",
                                                        "comunidadBaraja44.png",
                                                        "comunidadBaraja45.png",
                                                        "comunidadBaraja46.png",
                                                        "comunidadBaraja47.png",
                                                        "comunidadBaraja48.png",
                                                        "comunidadBaraja49.png",
                                                        "comunidadBaraja50.png",
                                                        "comunidadBaraja51.png",
                                                        "comunidadBaraja52.png",
                                                        "comunidadBaraja53.png"};

    // Nombres de las imágenes de los volteos de las cartas
    public final static String[] VOLTEO_SUERTE = {"cartaSuerteVolteada0.png",
                                                    "cartaSuerteVolteada1.png",
                                                    "cartaSuerteVolteada2.png",
                                                    "cartaSuerteVolteada3.png",
                                                    "cartaSuerteVolteada4.png",
                                                    "cartaSuerteVolteada5.png",
                                                    "cartaSuerteVolteada6.png",
                                                    "cartaSuerteVolteada7.png",
                                                    "cartaSuerteVolteada8.png",
                                                    "cartaSuerteVolteada9.png",
                                                    "cartaSuerteVolteada10.png",
                                                    "cartaSuerteVolteada11.png",
                                                    "cartaSuerteVolteada12.png",
                                                    "cartaSuerteVolteada13.png",
                                                    "cartaSuerteVolteada14.png",
                                                    "cartaSuerteVolteada15.png",
                                                    "cartaSuerteVolteada16.png",
                                                    "cartaSuerteVolteada17.png",
                                                    "cartaSuerteVolteada18.png",
                                                    "cartaSuerteVolteada19.png",
                                                    "cartaSuerteVolteada20.png",
                                                    "cartaSuerteVolteada21.png",
                                                    "cartaSuerteVolteada22.png",
                                                    "cartaSuerteVolteada23.png",
                                                    "cartaSuerteVolteada24.png",
                                                    "cartaSuerteVolteada25.png",
                                                    "cartaSuerteVolteada26.png",
                                                    "cartaSuerteVolteada27.png",
                                                    "cartaSuerteVolteada28.png",
                                                    "cartaSuerteVolteada29.png",
                                                    "cartaSuerteVolteada30.png",
                                                    "cartaSuerteVolteada31.png",
                                                    "cartaSuerteVolteada32.png",
                                                    "cartaSuerteVolteada33.png",
                                                    "cartaSuerteVolteada34.png",
                                                    "cartaSuerteVolteada35.png",
                                                    "cartaSuerteVolteada36.png"};

    public final static String[] VOLTEO_COMUNIDAD = {"cartaComunidadVolteada0.png",
                                                        "cartaComunidadVolteada1.png",
                                                        "cartaComunidadVolteada2.png",
                                                        "cartaComunidadVolteada3.png",
                                                        "cartaComunidadVolteada4.png",
                                                        "cartaComunidadVolteada5.png",
                                                        "cartaComunidadVolteada6.png",
                                                        "cartaComunidadVolteada7.png",
                                                        "cartaComunidadVolteada8.png",
                                                        "cartaComunidadVolteada9.png",
                                                        "cartaComunidadVolteada10.png",
                                                        "cartaComunidadVolteada11.png",
                                                        "cartaComunidadVolteada12.png",
                                                        "cartaComunidadVolteada13.png",
                                                        "cartaComunidadVolteada14.png",
                                                        "cartaComunidadVolteada15.png",
                                                        "cartaComunidadVolteada16.png",
                                                        "cartaComunidadVolteada17.png",
                                                        "cartaComunidadVolteada18.png",
                                                        "cartaComunidadVolteada19.png",
                                                        "cartaComunidadVolteada20.png",
                                                        "cartaComunidadVolteada21.png",
                                                        "cartaComunidadVolteada22.png",
                                                        "cartaComunidadVolteada23.png",
                                                        "cartaComunidadVolteada24.png",
                                                        "cartaComunidadVolteada25.png",
                                                        "cartaComunidadVolteada26.png",
                                                        "cartaComunidadVolteada27.png",
                                                        "cartaComunidadVolteada28.png",
                                                        "cartaComunidadVolteada29.png",
                                                        "cartaComunidadVolteada30.png",
                                                        "cartaComunidadVolteada31.png",
                                                        "cartaComunidadVolteada32.png",
                                                        "cartaComunidadVolteada33.png",
                                                        "cartaComunidadVolteada34.png",
                                                        "cartaComunidadVolteada35.png",
                                                        "cartaComunidadVolteada36.png"};

    // Desplazamientos de las barajas
    public final static int SUERTE_BARAJA_DESPLAZAMIENTO_X = TABLERO_ANCHO - BARAJA_ANCHO - CASILLA_ANCHO - 100 -
            CASILLAS_POR_LADO * 3;
    // El -14 es un ajuste porque en realidad las casillas se imprimen más juntas para evitar que los bordes entre
    // ellas sean enormes
    public final static int SUERTE_BARAJA_DESPLAZAMIENTO_Y = (TABLERO_ALTO - BARAJA_ALTO) / 2 - 14;
    // El ajuste final es por el motivo anterior; además, debe considerarse que la posición dada es la de la esquina
    // superior izquierda de la imagen
    public final static int COMUNIDAD_BARAJA_DESPLAZAMIENTO_X = CASILLA_ANCHO + 100;
    public final static int COMUNIDAD_BARAJA_DESPLAZAMIENTO_Y = (TABLERO_ALTO - BARAJA_ALTO) / 2 - 14;

    // Desplazamiento de las cartas dentro de su respectiva baraja
    public final static int CARTA_SUERTE_DESPLAZAMIENTO_X = BARAJA_ANCHO - CARTA_ANCHO - 8;
    public final static int CARTA_SUERTE_DESPLAZAMIENTO_Y = 0;
    public final static int CARTA_COMUNIDAD_DESPLAZAMIENTO_X = 8;
    public final static int CARTA_COMUNIDAD_DESPLAZAMIENTO_Y = 0;

    // Nombres de las imágenes de los avatares
    public final static String[] AVATARES_IMAGENES = {"autobus.png",
                                                      "barco.png",
                                                      "hamburguesa.png",
                                                      "pajaro.png",
                                                      "pato.png",
                                                      "perro.png"};

    // Nombre de los frames de la animación del modo avanzado
    public final static String[] AVATARES_AVANZADO_FRAMES = {"Destello_0.png",
                                                             "Destello_1.png",
                                                             "Destello_2.png",
                                                             "Destello_3.png"};

    // Dimensiones del marco de información
    public final static int MARCO_INFORMACION_ANCHO = 610;
    public final static int MARCO_INFORMACION_ALTO = 480;

    // Nombres de las imágenes del marco de información
    public final static String MARCO_INFORMACION_IMAGEN_SUPERIOR = "SeccionSuperior.png";
    public final static String MARCO_INFORMACION_IMAGEN_CENTRAL = "SeccionCentral.png";
    public final static String MARCO_INFORMACION_IMAGEN_INFERIOR = "SeccionInferior.png";

    // Desplazamiento del marco de información
    public final static int MARCO_INFORMACION_DESPLAZAMIENTO_X = (TABLERO_ANCHO - MARCO_INFORMACION_ANCHO) / 2;
    public final static int MARCO_INFORMACION_DESPLAZAMIENTO_Y = (TABLERO_ALTO - MARCO_INFORMACION_ALTO ) / 2 - 18;

    // Tamaño de la fuente del marco de información
    public final static int MARCO_INFORMACION_FUENTE_TAMANO = 16;

    // Espacio para cada línea del marco de información
    public final static int MARCO_INFORMACION_LINEA_ALTO = MARCO_INFORMACION_ALTO / MARCO_INFORMACION_FUENTE_TAMANO;

    // Número de líneas que caben en el marco de información
    public final static int MARCO_INFORMACION_NUMERO_LINEAS = MARCO_INFORMACION_ALTO / MARCO_INFORMACION_LINEA_ALTO;

    // Nombre de los frames de la animación del modo avanzado
    public final static String[] MARCO_INFORMACION_ANIMACION_FRAMES = {"Pergamino_0.png",
                                                                       "Pergamino_1.png",
                                                                       "Pergamino_2.png",
                                                                       "Pergamino_3.png",
                                                                       "Pergamino_4.png",
                                                                       "Pergamino_5.png",
                                                                       "Pergamino_6.png",
                                                                       "Pergamino_7.png",
                                                                       "Pergamino_8.png",
                                                                       "Pergamino_9.png",
                                                                       "Pergamino_10.png",
                                                                       "Pergamino_11.png",
                                                                       "Pergamino_12.png",
                                                                       "Pergamino_13.png",
                                                                       "Pergamino_14.png",
                                                                       "Pergamino_15.png",
                                                                       "Pergamino_16.png",
                                                                       "Pergamino_17.png",
                                                                       "Pergamino_18.png",
                                                                       "Pergamino_19.png",
                                                                       "Pergamino_20.png",
                                                                       "Pergamino_21.png",
                                                                       "Pergamino_22.png",
                                                                       "Pergamino_23.png",
                                                                       "Pergamino_24.png",
                                                                       "Pergamino_25.png",
                                                                       "Pergamino_26.png",
                                                                       "Pergamino_27.png",
                                                                       "Pergamino_28.png",
                                                                       "Pergamino_29.png",
                                                                       "Pergamino_30.png",
                                                                       "Pergamino_31.png",
                                                                       "Pergamino_32.png",
                                                                       "Pergamino_33.png",
                                                                       "Pergamino_34.png",
                                                                       "Pergamino_35.png",
                                                                       "Pergamino_36.png",
                                                                       "Pergamino_37.png",
                                                                       "Pergamino_38.png",
                                                                       "Pergamino_39.png",
                                                                       "Pergamino_40.png",
                                                                       "Pergamino_41.png",
                                                                       "Pergamino_42.png",
                                                                       "Pergamino_43.png",
                                                                       "Pergamino_44.png",
                                                                       "Pergamino_45.png",
                                                                       "Pergamino_46.png",
                                                                       "Pergamino_47.png",
                                                                       "Pergamino_48.png",
                                                                       "Pergamino_49.png",
                                                                       "Pergamino_50.png",
                                                                       "Pergamino_51.png",
                                                                       "Pergamino_52.png",
                                                                       "Pergamino_53.png",
                                                                       "Pergamino_54.png",
                                                                       "Pergamino_55.png",
                                                                       "Pergamino_56.png",
                                                                       "Pergamino_57.png",
                                                                       "Pergamino_58.png",
                                                                       "Pergamino_59.png",
                                                                       "Pergamino_60.png",
                                                                       "Pergamino_61.png",
                                                                       "Pergamino_62.png",
                                                                       "Pergamino_63.png",
                                                                       "Pergamino_64.png",
                                                                       "Pergamino_65.png",
                                                                       "Pergamino_66.png",
                                                                       "Pergamino_67.png",
                                                                       "Pergamino_68.png",
                                                                       "Pergamino_69.png",
                                                                       "Pergamino_70.png",
                                                                       "Pergamino_71.png",
                                                                       "Pergamino_72.png",
                                                                       "Pergamino_73.png"};

    // Dimensiones del registro
    public final static int REGISTRO_ANCHO = 737;
    public final static int REGISTRO_ALTO = 230;

    // Desplazamiento del registro
    public final static int REGISTRO_DESPLAZAMIENTO_X = 485;
    public final static int REGISTRO_DESPLAZAMIENTO_Y = 10;

    // Tamaño de la fuente del registro
    public final static int REGISTRO_FUENTE_TAMANO = 12;

    // Dimensiones del editor
    public final static int EDITOR_ANCHO = VENTANA_ANCHO;
    public final static int EDITOR_ALTO = VENTANA_ALTO;

    // Dimensiones de la cuadrícula del editor
    public final static int EDITOR_CUADRICULA_ANCHO = 1620;
    public final static int EDITOR_CUADRICULA_ALTO = 685;

    // Desplazamiento de la cuadrícula del editor
    public final static int EDITOR_CUADRICULA_DESPLAZAMIENTO_X = (EDITOR_ANCHO - EDITOR_CUADRICULA_ANCHO) / 2;
    public final static int EDITOR_CUADRICULA_DESPLAZAMIENTO_Y = (EDITOR_ALTO - EDITOR_CUADRICULA_ALTO) / 2;

    // Nombre de la imagen de cuadrícula del editor
    public final static String EDITOR_CUADRICULA = "Cuadricula.png";

    // Nombre de un fases en blanco para una casilla
    public final static String EDITOR_CASILLA_BLANCO = "CasillaBlanco.png";

    // Nombres de las imágenes de los botones del editor
    public final static String EDITOR_BOTON_ACEPTAR = "aceptar.png";
    public final static String EDITOR_BOTON_ACEPTAR_OSCURO = "aceptarOscuro.png";
    public final static String EDITOR_BOTON_CANCELAR = "cancelar.png";
    public final static String EDITOR_BOTON_CANCELAR_OSCURO = "cancelarOscuro.png";

    // Desplazamientos de los botones del editor
    public final static int EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_X = 1700;
    public final static int EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_Y = 25;
    public final static int EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_X = 1800;
    public final static int EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_Y = 25;

    // Dimensiones del input
    public final static int INPUT_ANCHO = 510;
    public final static int INPUT_ALTO = 60;

    // Desplazamiento del input en el editor
    public final static int INPUT_DESPLAZAMIENTO_EDITOR_X = (VENTANA_ANCHO - INPUT_ANCHO) / 2;
    public final static int INPUT_DESPLAZAMIENTO_EDITOR_Y = (VENTANA_ALTO - INPUT_ALTO) / 2;

    // Desplazamiento del input en el juego
    public final static int INPUT_DESPLAZAMIENTO_JUEGO_X = (TABLERO_ANCHO - MARCO_INFORMACION_ANCHO) / 2+165;
    public final static int INPUT_DESPLAZAMIENTO_JUEGO_Y = (MARCO_INFORMACION_ALTO ) / 2 + 75;

    // Dimensiones del recuadro del input
    public final static int INPUT_RECUADRO_ANCHO = 389;
    public final static int INPUT_RECUADRO_ALTO = 15;

    // Desplazamiento del recuadro del input
    public final static int INPUT_DESPLAZAMIENTO_RECUADRO_X = 10;
    public final static int INPUT_DESPLAZAMIENTO_RECUADRO_Y = 25;

    // Dimensiones del botón de aceptar del input
    public final static int INPUT_BOTON_ANCHO = 25;
    public final static int INPUT_BOTON_ALTO = 25;

    // Desplazamiento del botón de aceptar del input
    public final static int INPUT_DESPLAZAMIENTO_BOTON_X = INPUT_ANCHO - 25;
    public final static int INPUT_DESPLAZAMIENTO_BOTON_Y = 25;

    // Nombre de las imágenes para el input de dinero
    public final static String INPUT_ENTERO_DINERO_IMAGEN = "plantillaEntradaDinero.png";
    public final static String INPUT_ENTERO_DINERO_IMAGEN_OSCURA = "plantillaEntradaDineroOscuro.png";

    // Nombre de las imágenes para el input de turnos
    public final static String INPUT_ENTERO_TURNOS_IMAGEN = "plantillaEntradaTurnos.png";
    public final static String INPUT_ENTERO_TURNOS_IMAGEN_OSCURA = "plantillaEntradaTurnosOscuro.png";

    // Nombre de las imágenes para el input de strings
    public final static String INPUT_STRING_IMAGEN = "plantillaEntrada.png";
    public final static String INPUT_STRING_IMAGEN_OSCURA = "plantillaEntradaOscuro.png";

    // Sonido de click de un botón
    public final static String SONIDO_BOTON = "click.wav";

    // Sonido de la animación del dinero
    public final static String SONIDO_DINERO = "dinero.mp3";

    // Sonido de pasar a modo avanzado
    public final static String SONIDO_AVANZADO = "modoAvanzado.wav";

    // Sonido del pergamino
    public final static String SONIDO_PERGAMINO = "pergamino.wav";

    // Sonido de mostrar una carta
    public final static String SONIDO_CARTA_REVELAR = "revelarCarta.wav";

    // Sonido de barajar
    public final static String SONIDO_CARTA_BARAJAR = "barajar.wav";

    // Sonido de lanzar los dados
    public final static String SONIDO_DADOS = "dados.wav";

    // Música del editor
    public final static String MUSICA_EDITOR = "musicaEditor.mp3";

    public final static int ANIMACION_DINERO_ANCHO = 100;
    public final static int ANIMACION_DINERO_ALTO = 100;

    public final static String[] FRAMES_DINEROMAS = {"dineroMas0.png",
                                                        "dineroMas1.png",
                                                        "dineroMas2.png",
                                                        "dineroMas3.png",
                                                        "dineroMas4.png",
                                                        "dineroMas5.png",
                                                        "dineroMas6.png",
                                                        "dineroMas7.png",
                                                        "dineroMas8.png",
                                                        "dineroMas9.png",
                                                        "dineroMas10.png",
                                                        "dineroMas11.png",
                                                        "dineroMas12.png",
                                                        "dineroMas13.png",
                                                        "dineroMas14.png",
                                                        "dineroMas15.png",
                                                        "dineroMas16.png",
                                                        "dineroMas17.png",
                                                        "dineroMas18.png",
                                                        "dineroMas19.png",
                                                        "dineroMas20.png",
                                                        "dineroMas21.png"};

    public final static String[] FRAMES_DINEROMENOS = {"dineroMenos0.png",
                                                        "dineroMenos1.png",
                                                        "dineroMenos2.png",
                                                        "dineroMenos3.png",
                                                        "dineroMenos4.png",
                                                        "dineroMenos5.png",
                                                        "dineroMenos6.png",
                                                        "dineroMenos7.png",
                                                        "dineroMenos8.png",
                                                        "dineroMenos9.png",
                                                        "dineroMenos10.png",
                                                        "dineroMenos11.png",
                                                        "dineroMenos12.png",
                                                        "dineroMenos13.png",
                                                        "dineroMenos14.png",
                                                        "dineroMenos15.png",
                                                        "dineroMenos16.png",
                                                        "dineroMenos17.png",
                                                        "dineroMenos18.png",
                                                        "dineroMenos19.png",
                                                        "dineroMenos20.png"};

    // Fondos de la fase de bienvenida
    public final static String FASE_BIENVENIDA_FONDO = "bienvenido.png";
    public final static String FASE_BIENVENIDA_ALTERNATIVO = "bienvenidoWithPenin.png";

    // Fondo de la fase de selección del tablero
    public final static String FASE_SELECCION_FONDO = "seleccion.png";

    // Fondos alternativos de la fase de selección
    public final static String FASE_SELECCION_EDITOR = "personalizadoAsignado.png";
    public final static String FASE_SELECCION_PREDETERMINADO = "predeterminadoAsignado.png";

    // Desplazamiento de los sensores en la fase de selección
    public final static int FASE_SELECCION_EDITOR_DESPLAZAMIENTO_X = 192;
    public final static int FASE_SELECCION_EDITOR_DESPLAZAMIENTO_Y = 350;
    public final static int FASE_SELECCION_PREDETERIMADO_DESPLAZAMIENTO_X = 1150;
    public final static int FASE_SELECCION_PREDETERIMADO_DESPLAZAMIENTO_Y = 350;

    // Dimensiones de cada botón de la fase de selección
    public final static int FASE_SELECCION_BOTON_ANCHO = 574;
    public final static int FASE_SELECCION_BOTON_ALTO = 500;

    // Constantes de ancho y alto de los botones de la fase de jugador
    public final static int BOTONFASE_ANCHO = 660;
    public final static int BOTONFASE_ALTO = 170;

    // Nombres de las imagénes de estados de los jugadores
    public final static String BUFO_CARCEL = "bufoCarcel.png";
    public final static String BUFO_CABEZA = "bufoRey.png";
}
