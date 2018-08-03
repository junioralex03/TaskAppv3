package ado.edu.itla.taskapp.entidad;

public class UsuarioActivo extends Usuario{
    private static final UsuarioActivo ourInstance = new UsuarioActivo();

    private UsuarioActivo() {

    }

    public static UsuarioActivo getInstance(Usuario usuario) {
        ourInstance.setNombre(usuario.getNombre());
        ourInstance.setEmail(usuario.getEmail());
        ourInstance.setId(usuario.getId());
        ourInstance.setTipoUsuario(usuario.getTipoUsuario());
        ourInstance.setContrasena(usuario.getContrasena());

        return ourInstance;
    }
    public static UsuarioActivo getInstance() {
        return ourInstance;
    }
}


