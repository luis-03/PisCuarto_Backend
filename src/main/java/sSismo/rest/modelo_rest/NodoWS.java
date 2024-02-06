package sSismo.rest.modelo_rest;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import sSismo.modelo.Nodo;

@Getter
@Setter
public class NodoWS {

    private Integer id;
    
    @NotBlank(message = "El nombre es requerido.")
    @Size(max = 50, message = "El nombre debe tener como máximo {max} caracteres.")
    private String nombre;
    
    @Size(max = 255, message = "La descripción debe tener como máximo {max} caracteres.")
    private String descripcion;
    
    @Size(max = 50, message = "La referencia debe tener como máximo {max} caracteres.")
    private String referencia;
    
    @Size(max = 50, message = "La facultad debe tener como máximo {max} caracteres.")
    private String facultad;
    
    @NotNull(message = "La latitud es requerida.")
    private double latitud;
    
    @NotNull(message = "La longitud es requerida.")
    private double longitud;
    
    @NotBlank(message = "El tipo de nodo es requerido.")
    @Size(max = 20, message = "El tipo de nodo debe tener como máximo {max} caracteres.")
    private String tipoDeNodo; // "Nodo de paso", "Zona segura", etc.
    
    private NodoWS nodoPadre;
    private List<NodoWS> nodosConectados;
    private String external_id;
    // Otros campos si es necesario

    public Nodo cargarObjeto(Nodo nodo) {
        if (nodo == null) {
            nodo = new Nodo();
        }
        nodo.setNombre(nombre);
        nodo.setDescripcion(descripcion);
        nodo.setReferencia(referencia);
        nodo.setFacultad(facultad);
        nodo.setLatitud(latitud);
        nodo.setLongitud(longitud);
        nodo.setTipoDeNodo(tipoDeNodo);
        nodo.setExternal_id(UUID.randomUUID().toString());
        
        // Puedes asignar el nodoPadre y nodosConectados según tu lógica específica


        return nodo;
    }
}
