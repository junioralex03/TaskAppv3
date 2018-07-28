package ado.edu.itla.taskapp.repositorio;

import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;

public interface TareaRepositorio {

    boolean guardar(Tarea tarea);
    Tarea buscar(int id);
    List<Tarea> buscarAsignadaA(Usuario usuario);
    List<Tarea> buscarCreadaPor(Usuario usuario);

}
