package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores;

public enum TipoFuncionFase{
    anadirJugador(640, 350),
    coche(1075, 550, true, anadirJugador),
    esfinge(195, 350, true, anadirJugador),
    sombrero(1075, 350, true, anadirJugador),
    pelota(195, 350, true, anadirJugador),
    iniciarJuego(640, 640);

    private final int posicionX;
    private final int posicionY;

    // Booleano para indicar si se accede a través de otros botones
    private boolean pagina;

    // En caso de que pagina sea true, de que función se accede
    private TipoFuncionFase funcionRaiz;

    TipoFuncionFase(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.funcionRaiz = null;
        this.pagina = false;
    }

    TipoFuncionFase(int posicionX, int posicionY, boolean pagina, TipoFuncionFase funcionRaiz){
        this(posicionX, posicionY);
        this.pagina = pagina;
        this.funcionRaiz = funcionRaiz;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public boolean isPagina() {
        return pagina;
    }

    public void setPagina(boolean pagina) {
        this.pagina = pagina;
    }

    public TipoFuncionFase getFuncionRaiz() {
        return funcionRaiz;
    }

    public void setFuncionRaiz(TipoFuncionFase funcionRaiz) {
        this.funcionRaiz = funcionRaiz;
    }
}
