package sSismo.controladores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sSismo.modelo.Nodo;
import java.util.List;

public interface NodoRepository extends CrudRepository<Nodo, Integer> {
    @Query("SELECT n FROM Nodo n WHERE n.external_id = ?1")
    Nodo findByExternal_id(String external_id);
    //List<Nodo> findByNodoPadre_External_id(String external_id);
    
}




