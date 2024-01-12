package sSismo.rest.modelo_rest;

import java.util.UUID;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import sSismo.modelo.Cuenta;
@Getter
@Setter

public class CuentaWS {
    @Email(message = "Debe insertar un correo")
    private String correo;
    @NotBlank(message = "La clave es requerida")
    @NotNull(message = "La clave no puede ser nula")
    @NotEmpty(message = "La clave no puede estar vacia")
    private String clave;
    /**
     * metodo para cargar el correspondiente objeto cuenta con todos sus datos
     * @param cuenta
     * @return la cuenta con todos sus datos ingresados
     */
    public Cuenta cargarObjeto(Cuenta cuenta){
        if(cuenta == null){
            cuenta = new Cuenta();
        }
        
        cuenta.setCorreo(correo);
        cuenta.setExternal_id(UUID.randomUUID().toString());
        cuenta.setUpdateAt(new Date());
        return cuenta;
    }
}
