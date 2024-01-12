package sSismo.validaciones;

import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sSismo.rest.respuesta.RespuestaModelo;

@ControllerAdvice
public class ValidateModels extends ResponseEntityExceptionHandler {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        RespuestaModelo rm = new RespuestaModelo();
        rm.setCode(HttpStatus.BAD_REQUEST.toString());
        rm.setMsg("ERROR");
        HashMap mapa = new HashMap<>();

        for (ObjectError error : ex.getAllErrors()) {

            mapa.put(ex.getFieldError().getField() + "_" + error.getCode(), error.getDefaultMessage());

        }

        rm.setData(mapa);
        return new ResponseEntity(rm, HttpStatus.OK);
    }
}