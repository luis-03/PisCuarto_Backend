package sSismo.rest.respuesta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class RespuestaLista {
    public static ResponseEntity respuestaLista(Object datos) {
        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.OK.toString());
        rm.setMsg("OK");
        rm.setData(datos);
        return new ResponseEntity<RespuestaModelo>(rm, HttpStatus.OK);
    }

    public static ResponseEntity respuesta(Object datos, String msg) {
        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.OK.toString());
        rm.setMsg(msg);
        rm.setData(datos);
        return new ResponseEntity<RespuestaModelo>(rm, HttpStatus.OK);
    }

    public static ResponseEntity respuestaError(Object datos, String msg) {
        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.CONFLICT.toString());
        rm.setMsg(msg);
        rm.setData(datos);
        return new ResponseEntity<RespuestaModelo>(rm, HttpStatus.CONFLICT);
    }
}
