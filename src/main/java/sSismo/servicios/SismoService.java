package sSismo.servicios;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SismoService {

    public double obtenerIntensidadSismo() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/datos";
        String respuesta = restTemplate.getForObject(url, String.class);
        // Parsear el JSON y obtener el valor de la intensidad del sismo
        double intensidadSismo = parsearRespuesta(respuesta);
        return intensidadSismo;
    }

    private double parsearRespuesta(String respuesta) {
        // Parsear el JSON y obtener el valor de la intensidad del sismo
        JSONObject jsonObject = new JSONObject(respuesta);
        return jsonObject.getDouble("dato");
    }
}
