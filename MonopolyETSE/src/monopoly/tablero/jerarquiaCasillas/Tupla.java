package monopoly.tablero.jerarquiaCasillas;

public class Tupla {

    private Object objeto;
    private Integer posicion;

    public Tupla(Object objeto, Integer posicion) {
        this.objeto = objeto;
        this.posicion = posicion;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }
}
