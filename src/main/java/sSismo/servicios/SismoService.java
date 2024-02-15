package sSismo.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SismoService {

    public double obtenerIntensidadSismo() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/datos";
        String respuesta = restTemplate.getForObject(url, String.class);

        // Parsear el JSON y obtener el valor de la intensidad del sismo
        double intensidadSismo = parsearIntensidad(respuesta);
        return intensidadSismo;
    }

    public String obtenerFecha() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/datos";
        String respuesta = restTemplate.getForObject(url, String.class);

        // Parsear el JSON y obtener la fecha del sismo
        String fecha = parsearFecha(respuesta);
        return fecha;
    }

    public String obtenerHora() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/datos";
        String respuesta = restTemplate.getForObject(url, String.class);

        // Parsear el JSON y obtener la hora del sismo
        String hora = parsearHora(respuesta);
        return hora;
    }

    private double parsearIntensidad(String respuesta) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(respuesta);
            return Double.parseDouble(jsonNode.get("dato").asText());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private String parsearFecha(String respuesta) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(respuesta);
            return jsonNode.get("fecha").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String parsearHora(String respuesta) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(respuesta);
            return jsonNode.get("hora").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
