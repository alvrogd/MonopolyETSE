package monopoly.jugadores;

public enum TipoAvatar {

    /* Tipos de enumeración */
    pelota("pelota"),
    coche("coche"),
    esfinge("esfinge"),
    sombrero("sombrero"),
    banca("banca");


    /* Atributo */
    private String nombre;


    /* Constructor */
    private TipoAvatar(String nombre) {
        this.nombre = nombre;
    }


    /* Métodos */
    public String getNombre() {
        return nombre;
    }

    public static TipoAvatar toAvatar(String avatar) {
        switch (avatar) {

            case "pelota":
                return (TipoAvatar.pelota);

            case "coche":
                return (TipoAvatar.coche);

            case "esfinge":
                return (TipoAvatar.esfinge);

            case "sombrero":
                return (TipoAvatar.sombrero);

            case "Pelota":
                return (TipoAvatar.pelota);

            case "Coche":
                return (TipoAvatar.coche);

            case "Esfinge":
                return (TipoAvatar.esfinge);

            case "Sombrero":
                return (TipoAvatar.sombrero);

            default:
                return null;

        }
    }
}
