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
    public final static int CONTROLES_ALTO = 250;
    
    // Desplazamientos de la sección de información
    public final static int INFORMACION_DESPLAZAMIENTO_X = (VENTANA_ANCHO - INFORMACION_ANCHO) / 2;
    public final static int INFORMACION_DESPLAZAMIENTO_Y = (VENTANA_ALTO - INFORMACION_ALTO - CONTROLES_ALTO) / 2;
    
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
    
    // Nombres de las imágenes de las casillas
    public final static String[] CASILLAS_IMAGENES = {"Isengard.png",
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
                                                      "Servicio.png",
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
                                                      "Servicio.png",
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
    
    // Nombres de las imágenes de los edificios
    public final static String CASA = "casa.png";
    public final static String HOTEL = "hotel.png";
    public final static String PISCINA = "piscina.png";
    public final static String PISTA = "pista.png";
    
    // Tamaño de las cartas
    public final static int CARTA_ANCHO = 170;
    public final static int CARTA_ALTO = 275;
    
    // Nombres de las imágenes de las cartas
    public final static String CARTA_SUERTE = "Suerte.png";
    public final static String CARTA_COMUNIDAD = "Comunidad.png";
    public final static String CARTA_REVERSO = "Reverso.png";
    
    // Desplazamientos de las cartas
    public final static int SUERTE_DESPLAZAMIENTO_X = CASILLA_ANCHO + 100;
    // El -14 es un ajuste porque en realidad las casillas se imprimen más juntas para evitar que los bordes entre
    // ellas sean enormes
    public final static int SUERTE_DESPLAZAMIENTO_Y = (TABLERO_ALTO - CARTA_ALTO) / 2 - 14;
    // El ajuste final es por el motivo anterior; además, debe considerarse que la posición dada es la de la esquina
    // superior izquierda de la imagen
    public final static int COMUNIDAD_DESPLAZAMIENTO_X = TABLERO_ANCHO -CARTA_ANCHO - CASILLA_ANCHO - 100 -
            CASILLAS_POR_LADO * 3;
    public final static int COMUNIDAD_DESPLAZAMIENTO_Y = (TABLERO_ALTO - CARTA_ALTO) / 2 - 14;
    
    // Nombres de las imágenes de los avatares
    public final static String[] AVATARES_IMAGENES = {"Avion.png",
                                                      "Coche.png",
                                                      "Esfinge.png",
                                                      "Hamburguesa.png",
                                                      "Pelota.png",
                                                      "Sombrero.png"};
    
    // Nombre de los frames de la animación del modo avanzado
    public final static String[] AVATARES_AVANZADO_FRAMES = {"Destello_0.png",
                                                             "Destello_1.png",
                                                             "Destello_2.png",
                                                             "Destello_3.png"};
}
