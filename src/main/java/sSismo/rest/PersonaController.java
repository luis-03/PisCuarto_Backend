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

import sSismo.controladores.CuentaRepository;
import sSismo.controladores.PersonaRepository;
import sSismo.controladores.RolRepository;
import sSismo.controladores.utiles.Utilidades;
import sSismo.modelo.Cuenta;
import sSismo.modelo.Persona;
import sSismo.modelo.Rol;
import sSismo.rest.modelo_rest.PersonaWS;
import sSismo.rest.respuesta.RespuestaLista;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.HEAD })
public class PersonaController {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired

    /**
     * Método para listar personas y toda la información contenida en la misma
     * 
     * @return
     */
    @GetMapping("/personas")
    public ResponseEntity listar() {
        List<Persona> lista = new ArrayList<>();
        List mapa = new ArrayList<>();
        personaRepository.findAll().forEach((p) -> lista.add(p));
        Integer constante = 0;
        for (Persona p : lista) {
            constante++;
            HashMap aux = new HashMap<>();
            aux.put("apellidos", p.getApellidos());
            aux.put("nombres", p.getNombres());
            aux.put("identificacion", p.getIdentificacion());
            aux.put("direccion", p.getDireccion());
            aux.put("telefono", p.getTelefono());
            aux.put("external", p.getExternal_id());
            mapa.add(aux);
        }
        return RespuestaLista.respuestaLista(mapa);
    }

    /**
     * Método para guardar Personas, con sus cuenta de correo y clave
     * 
     * @param personaWS
     * @return
     */
    @PostMapping("/personas/guardar")
    public ResponseEntity guardar(@Valid @RequestBody PersonaWS personaWS) {
        HashMap mapa = new HashMap<>();
        Persona persona = personaWS.cargarObjeto(null);
        Rol rol = rolRepository.findByNombreRol(personaWS.getTipo_persona().toLowerCase());
        if (rol != null) {
            persona.setCreateAt(new Date());
            persona.setRol(rol);
            if (personaWS.getCuenta() != null) {
                if (personaWS.getTipo_persona().equalsIgnoreCase("cliente")) {
                    mapa.put("evento", "Cliente no puede tener registrar una cuenta");
                    return RespuestaLista.respuestaError(mapa, "ERROR");
                }
                Cuenta cuenta = personaWS.getCuenta().cargarObjeto(null);
                // estado Cuenta
                cuenta.setCreateAt(new Date());
                cuenta.setPersona(persona);
                persona.setCuenta(cuenta);
                cuenta.setClave(Utilidades.clave(personaWS.getCuenta().getClave()));

            }
            personaRepository.save(persona);
            mapa.put("evento", "Se ha registrado correctamente");
            return RespuestaLista.respuesta(mapa, "OK");
        } else {
            mapa.put("evento", "Objeto no encontrado");
            return RespuestaLista.respuesta(mapa, "No se encontro el tipo de persona");
        }
    }

    /**
     * Método para obtener los datos e información de personas de acuerdo a su
     * external id
     * 
     * @param external
     * @return
     */
    @GetMapping("/personas/obtener/{external}")
    public ResponseEntity obtener(@PathVariable String external) {
        Persona p = personaRepository.findByExternal_id(external);
        if (p != null) {
            HashMap aux = new HashMap<>();
            aux.put("Nombres", p.getNombres());
            aux.put("Apellidos", p.getApellidos());
            aux.put("Direccion", p.getDireccion());
            aux.put("Identificacion", p.getIdentificacion());
            aux.put("Telefono", p.getTelefono());
            aux.put("Tipo Identificacion", p.getRol().getNombre());
            aux.put("Cuenta_external", p.getCuenta().getExternal_id());
            return RespuestaLista.respuestaLista(aux);
        } else {
            HashMap mapa = new HashMap<>();
            mapa.put("evento", "Objeto no encontrado");
            return RespuestaLista.respuestaError(mapa, "No se encontro el onjeto desaeado");
        }
    }

    /**
     * Método para obtener la identificacion de una persona en especifico y traer
     * toda su información y datos personales
     * 
     * @param identificacion
     * @return
     */
    @GetMapping("/personas/obtener/identificacion/{identificacion}")
    public ResponseEntity obtenerPorIdentificacion(@PathVariable String identificacion) {
        Persona p = personaRepository.findByIdentificacion(identificacion);
        if (p != null) {
            HashMap aux = new HashMap<>();
            aux.put("Nombres", p.getNombres());
            aux.put("Apellidos", p.getApellidos());
            aux.put("Direccion", p.getDireccion());
            aux.put("Identificacion", p.getIdentificacion());
            aux.put("Telefono", p.getTelefono());
            aux.put("Cuenta_external", p.getCuenta().getId());
            return RespuestaLista.respuestaLista(aux);

        } else {
            HashMap mapa = new HashMap<>();
            mapa.put("evento", "Objeto no encontrado");
            return RespuestaLista.respuesta(mapa, "No se encontro el onjeto desaeado");
        }
    }

    /**
     * Método para editar la información de una persona en especifico
     * 
     * @param externalId
     */
    @PostMapping("/personas/editar/{external}")
    public ResponseEntity editarPersona(@Valid @PathVariable String external, @RequestBody PersonaWS personaWS) {
        HashMap mapa = new HashMap<>();
        Persona persona = personaRepository.findByExternal_id(external);
        Rol rol = rolRepository.findByNombreRol(personaWS.getTipo_persona());
        if (personaWS.getCuenta() != null) {
            personaWS.cargarObjeto(persona);
            Cuenta cuenta = cuentaRepository.findByCorreo(personaWS.getCuenta().getCorreo());
            // estado Cuenta
            cuenta.setUpdateAt(new Date());
            cuenta.setCorreo(personaWS.getCuenta().getCorreo());
            cuenta.setClave(Utilidades.clave(personaWS.getCuenta().getClave()));
            cuenta.setPersona(persona);
            persona.setUpdateAt(new Date());
            persona.setRol(rol);
            persona.setCuenta(cuenta);
            personaRepository.save(persona);
            mapa.put("evento", "Se ha Modificado correctamente");
            return RespuestaLista.respuesta(mapa, "OK");
        } else {
            mapa.put("evento", "Objeto no encontrado");
            return RespuestaLista.respuestaError(mapa, "No se encontro la Persona");
        }
    }

}
