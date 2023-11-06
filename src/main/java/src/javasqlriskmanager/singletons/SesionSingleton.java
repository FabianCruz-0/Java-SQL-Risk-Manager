package src.javasqlriskmanager.singletons;

import src.javasqlriskmanager.models.Usuario;

public class SesionSingleton {
    public static final SesionSingleton instance = new SesionSingleton();

    private Usuario usuario;

    public static SesionSingleton getInstance() {
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.usuario = Usuario;
    }
}
