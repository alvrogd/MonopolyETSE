package aplicacionGUI.editor.filas;

public enum TipoFila {

    este(1, 1, 2, 1, 0, 5, 1, 1),
    norte(0, 1, 2, 0, 1, 6, 1, 1),
    oeste(1, 1, 2, 0, 1, 6, 0, 1),
    sur(1, 1, 2, 1, 0, 5, 1, 1);



    /* Atributos */

    private final int numeroComunidad;
    private final int numeroEspecial;
    private final int numeroGrupos;
    private final int numeroImpuestos;
    private final int numeroServicios;
    private final int numeroSolares;
    private final int numeroSuerte;
    private final int numeroTransportes;



    /* Constructor */

    TipoFila(int numeroComunidad, int numeroEspecial, int numeroGrupos, int numeroImpuestos, int numeroServicios, int
            numeroSolares, int numeroSuerte, int numeroTransportes) {

        this.numeroComunidad = numeroComunidad;
        this.numeroEspecial = numeroEspecial;
        this.numeroGrupos = numeroGrupos;
        this.numeroImpuestos = numeroImpuestos;
        this.numeroServicios = numeroServicios;
        this.numeroSolares = numeroSolares;
        this.numeroSuerte = numeroSuerte;
        this.numeroTransportes = numeroTransportes;
    }



    /* Getters y setters */

    public int getNumeroComunidad() {
        return numeroComunidad;
    }

    public int getNumeroEspecial() {
        return numeroEspecial;
    }

    public int getNumeroGrupos() {
        return numeroGrupos;
    }

    public int getNumeroImpuestos() {
        return numeroImpuestos;
    }

    public int getNumeroServicios() {
        return numeroServicios;
    }

    public int getNumeroSolares() {
        return numeroSolares;
    }

    public int getNumeroSuerte() {
        return numeroSuerte;
    }

    public int getNumeroTransportes() {
        return numeroTransportes;
    }
}
