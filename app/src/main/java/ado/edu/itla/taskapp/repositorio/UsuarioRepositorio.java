package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;

public interface UsuarioRepositorio {

    boolean guardar(Usuario usuario);
    Usuario buscar(int id);
    Usuario buscar(String username);
    List<Usuario> buscarTecnicos(String nombre);
}
