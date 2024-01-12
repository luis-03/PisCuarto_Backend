package sSismo.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sSismo.controladores.CuentaRepository;
import sSismo.controladores.utiles.Utilidades;
import sSismo.modelo.Cuenta;
import sSismo.rest.modelo_rest.CuentaWS;
import sSismo.rest.respuesta.RespuestaLista;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD })
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @PostMapping("/inicio_sesion")
    /**
     * metodo para iniciar sesion
     * @param cuentaWS
     * @return una lista con la respuesta del servidor al inicio de sesion
     */
    public ResponseEntity inicioSesion(@Valid @RequestBody CuentaWS cuentaWS){
        HashMap mapa = new HashMap<>();
        Cuenta cuenta = cuentaRepository.findByCorreo(cuentaWS.getCorreo());
        if(cuenta != null){
            if(Utilidades.verificar(cuentaWS.getClave(), cuenta.getClave())){
                mapa.put("token", token(cuenta));
                mapa.put("external", cuenta.getExternal_id());
                mapa.put("correo", cuenta.getCorreo());
                mapa.put("nombre", cuenta.getPersona().getNombres());
                mapa.put("rol", cuenta.getPersona().getRol().getNombre());
                return RespuestaLista.respuesta(mapa, "OK");
            }else{
                mapa.put("evento", "Cuenta no encontrada");
                return RespuestaLista.respuestaError(mapa, "No se encontró la cuenta con sus credenciales");
            }
        }else{
            mapa.put("evento", "Cuenta no encontrada");
            return RespuestaLista.respuestaError(mapa, "No se encontró la cuenta con sus credenciales");
        }
    }
    /**
     * Metodo para creacion del token
     */
    private String token(Cuenta cuenta){
        String secretKey = "Test#";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(cuenta.getPersona().getRol().getNombre());
        String token = Jwts.builder().setId(cuenta.getExternal_id()).setSubject(cuenta.getCorreo())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+600000000))
        .claim("authorities", grantedAuthorities.stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()))
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
    
}