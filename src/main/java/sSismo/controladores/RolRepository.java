package sSismo.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sSismo.modelo.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer> {
    /**
     * 
     * @param nombre
     * Este meétodo se utiliza para buscar en la base de datos el nombre de rol de la persona
     * @return
     */
    @Query("SELECT r FROM Rol r WHERE r.nombre = ?1")
    Rol findByNombreRol(String nombre);
}
