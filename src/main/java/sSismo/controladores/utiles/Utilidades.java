package sSismo.controladores.utiles;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Utilidades {
    public static String clave (String clave){
        return BCrypt.hashpw(clave, BCrypt.gensalt());
    }
    public static Boolean verificar (String clave, String hash){
        return BCrypt.checkpw(clave, hash);
    }

}
