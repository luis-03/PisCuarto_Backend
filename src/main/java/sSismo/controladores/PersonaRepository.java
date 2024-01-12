package sSismo.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sSismo.modelo.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer>{
    /**
     * 
     * @param external_id
     * Este método se lo utliza para buscar en la base de datos los external_id de la persona
     * @return
     */
    @Query("SELECT l from persona l WHERE l.external_id = ?1")
    Persona findByExternal_id(String external_id);

    /**
     * 
     * @param identificacion
     * Este método se lo utliza para buscar en la base de datos las identificaciones de la persona
     * @return
     */
    @Query("SELECT l from persona l WHERE l.identificacion = ?1")
    Persona findByIdentificacion(String identificacion);

}
