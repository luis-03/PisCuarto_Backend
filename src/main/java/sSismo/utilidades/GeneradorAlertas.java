package sSismo.utilidades;

import org.springframework.stereotype.Component;

// Anotación para indicar que esta clase es un componente gestionado por Spring
@Component
public class GeneradorAlertas {

    // Método para generar alertas basadas en la intensidad del sismo
    public String generarAlerta(double intensidadSismo) {
        // Comprobación de la intensidad del sismo y generación de la alerta correspondiente
        if (intensidadSismo < 2.0) {
            // Si la intensidad es menor que 2.0, devuelve la siguiente alerta
            return "Se ha detectado un pequeño temblor. No se esperan daños.";
        } else if (intensidadSismo < 4.0) {
            // Si la intensidad está entre 2.0 y 3.9, devuelve la siguiente alerta
            return "Se ha registrado un leve temblor. Podrías sentirlo, pero no debería causar daños significativos.";
        } else if (intensidadSismo < 6.0) {
            // Si la intensidad está entre 4.0 y 4.9, devuelve la siguiente alerta
            return "Se ha producido un temblor moderado. Asegúrate de estar en un lugar seguro y mantente alerta.";
        } else if (intensidadSismo < 7.0) {
            // Si la intensidad está entre 6.0 y 6.9, devuelve la siguiente alerta
            return "Un terremoto de magnitud moderada ha ocurrido. Prepárate para posibles réplicas y evalúa tu entorno en busca de daños.";
        } else if (intensidadSismo < 8.0) {
            // Si la intensidad está entre 7.0 y 7.9, devuelve la siguiente alerta
            return "Un fuerte terremoto ha ocurrido. Busca refugio inmediatamente y mantente alejado de ventanas y objetos que puedan caer.";
        } else if (intensidadSismo < 9.0) {
            // Si la intensidad está entre 8.0 y 8.9, devuelve la siguiente alerta
            return "Se ha producido un terremoto mayor. Busca refugio en un lugar seguro y sigue las instrucciones de las autoridades locales.";
        } else {
            // Si la intensidad es 9.0 o superior, devuelve la siguiente alerta
            return "Se ha registrado un terremoto extremadamente fuerte. Busca refugio de inmediato y prepárate para posibles réplicas y daños generalizados.";
        }
    }
}
