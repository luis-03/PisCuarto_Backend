package sSismo.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sSismo.modelo.Cuenta;

public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {
    @Query("SELECT c from Cuenta c WHERE c.external_id = ?1")
    Cuenta findByExternal_id(String external_id);

    @Query("SELECT c from Cuenta c WHERE c.correo = ?1")
    Cuenta findByCorreo(String correo);

    @Query("SELECT c from Cuenta c WHERE c.correo like ?1")
    Cuenta findByCorreoAlternativo(String correo);

    
    
}
