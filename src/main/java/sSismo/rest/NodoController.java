package sSismo.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import java.util.UUID;


import sSismo.controladores.NodoRepository;
import sSismo.modelo.Nodo;
import sSismo.rest.modelo_rest.NodoWS;
import sSismo.rest.respuesta.RespuestaLista;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.HEAD })
public class NodoController {

    @Autowired
    private NodoRepository nodoRepository;

    @PostMapping("/nodos/guardar")
    public ResponseEntity<HashMap<String, Object>> guardarNodo(@Valid @RequestBody NodoWS nodoWS) {
        HashMap<String, Object> mapa = new HashMap<>();
        Nodo nodo = new Nodo();

        nodo.setNombre(nodoWS.getNombre());
        nodo.setDescripcion(nodoWS.getDescripcion());
        nodo.setReferencia(nodoWS.getReferencia());
        nodo.setFacultad(nodoWS.getFacultad());
        nodo.setLatitud(nodoWS.getLatitud());
        nodo.setLongitud(nodoWS.getLongitud());
        nodo.setTipoDeNodo(nodoWS.getTipoDeNodo());
        nodo.setNodoPadre(null); // Ajusta según tu lógica
        nodo.setExternal_id(UUID.randomUUID().toString());
        // Configurar la relación autoreferencial y nodos conectados si es necesario
        // nodo.setNodoPadre(nodoWS.getNodoPadre());
        // nodo.setNodosConectados(nodoWS.getNodosConectados());

        nodoRepository.save(nodo);
        mapa.put("evento", "Se ha registrado correctamente");
        return RespuestaLista.respuesta(mapa, "OK");
    }
    

    @GetMapping("/nodos")
    public ResponseEntity<List<HashMap<String, Object>>> listarNodos() {
        List<Nodo> listaNodos = new ArrayList<>();
        List<HashMap<String, Object>> listaMapaNodos = new ArrayList<>();

        nodoRepository.findAll().forEach((nodo) -> listaNodos.add(nodo));

        for (Nodo nodo : listaNodos) {
            HashMap<String, Object> aux = new HashMap<>();
            aux.put("nombre", nodo.getNombre());
            aux.put("descripcion", nodo.getDescripcion());
            aux.put("referencia", nodo.getReferencia());
            aux.put("facultad", nodo.getFacultad());
            aux.put("latitud", nodo.getLatitud());
            aux.put("longitud", nodo.getLongitud());
            aux.put("tipoDeNodo", nodo.getTipoDeNodo());
            aux.put("external_registro", nodo.getExternal_id());
            aux.put("nodos_conectados", nodo.getNodosConectados());
            aux.put("nodoPadre", nodo.getNodoPadre());

            // Configurar la relación autoreferencial y nodos conectados si es necesario
            // aux.put("nodoPadre", nodo.getNodoPadre());
            // aux.put("nodosConectados", nodo.getNodosConectados());

            listaMapaNodos.add(aux);
        }
        return RespuestaLista.respuestaLista(listaMapaNodos);
    }

    @GetMapping("/nodos/{external}")
    public ResponseEntity obtenerNodo(@PathVariable String external) {
        Nodo nodo = nodoRepository.findByExternal_id(external);

        if (nodo != null) {
            HashMap aux = new HashMap<>();
            aux.put("nombre", nodo.getNombre());
            aux.put("descripcion", nodo.getDescripcion());
            aux.put("referencia", nodo.getReferencia());
            aux.put("facultad", nodo.getFacultad());
            aux.put("latitud", nodo.getLatitud());
            aux.put("longitud", nodo.getLongitud());
            aux.put("tipoDeNodo", nodo.getTipoDeNodo());
            aux.put("nodoPadre_external_id", nodo.getNodoPadre());

            // Configurar la relación autoreferencial y nodos conectados si es necesario
            // aux.put("nodoPadre", nodo.getNodoPadre());
            // aux.put("nodosConectados", nodo.getNodosConectados());


            return RespuestaLista.respuestaLista(aux);
        } else {
            HashMap<String, Object> mapa = new HashMap<>();
            mapa.put("evento", "Nodo no encontrado");
            return RespuestaLista.respuestaError(mapa, "No se encontró el nodo");
        }
    }

    @PostMapping("/nodos/editar/{external}")
    public ResponseEntity editarNodo(@Valid @PathVariable String external,
            @RequestBody NodoWS nodoWS) {
        HashMap mapa = new HashMap<>();
        Nodo nodo = nodoRepository.findByExternal_id(external);

        if (nodo != null) {
            nodo.setNombre(nodoWS.getNombre());
            nodo.setDescripcion(nodoWS.getDescripcion());
            nodo.setReferencia(nodoWS.getReferencia());
            nodo.setFacultad(nodoWS.getFacultad());
            nodo.setLatitud(nodoWS.getLatitud());
            nodo.setLongitud(nodoWS.getLongitud());
            nodo.setTipoDeNodo(nodoWS.getTipoDeNodo());
            nodo.setNodoPadre(nodo.getNodoPadre()); // Ajusta según tu lógica
            nodo.setExternal_id(nodo.getExternal_id());

            // Configurar la relación autoreferencial y nodos conectados si es necesario
            // nodo.setNodoPadre(nodoWS.getNodoPadre());
            // nodo.setNodosConectados(nodoWS.getNodosConectados());

            nodoRepository.save(nodo);
            mapa.put("evento", "Se ha modificado correctamente");
            return RespuestaLista.respuestaLista(mapa);
        } else {
            mapa.put("evento", "Nodo no encontrado");
            return RespuestaLista.respuestaError(mapa, "No se encontró el nodo");
        }
    }

    @PostMapping("/nodos/conectar/{external_id_padre}/{external_id_hijo}")
    public ResponseEntity<HashMap<String, Object>> conectarNodos(@PathVariable String external_id_padre,
            @PathVariable String external_id_hijo) {
        HashMap<String, Object> mapa = new HashMap<>();

        Nodo nodoPadre = nodoRepository.findByExternal_id(external_id_padre);
        Nodo nodoHijo = nodoRepository.findByExternal_id(external_id_hijo);

        if (nodoPadre != null && nodoHijo != null) {
            nodoHijo.setNodoPadre(nodoPadre);
            nodoRepository.save(nodoHijo);

            mapa.put("evento", "Conexión establecida correctamente");
            return RespuestaLista.respuestaLista(mapa);
        } else {
            mapa.put("evento", "Nodo(s) no encontrado(s)");
            return RespuestaLista.respuestaError(mapa, "No se encontraron uno o ambos nodos");
        }
    }
}
