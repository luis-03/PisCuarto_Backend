package sSismo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sSismo.servicios.SismoService;
import sSismo.utilidades.GeneradorAlertas;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.HEAD })
public class AlertasController {

    @Autowired
    private SismoService sismoService;

    @Autowired
    private GeneradorAlertas generadorAlertas;

    private double intensidadActual;
    private Timer timer;

    @GetMapping("/alertas")
    public AlertaResponse obtenerAlertaSismo() {
        double nuevaIntensidad = sismoService.obtenerIntensidadSismo();
        String fecha = sismoService.obtenerFecha();
        String hora = sismoService.obtenerHora();
        
        if (nuevaIntensidad > intensidadActual) {
            intensidadActual = nuevaIntensidad;
            // Reiniciar el temporizador para 30 segundos
            reiniciarTimer();
        }

        String alerta = generadorAlertas.generarAlerta(intensidadActual);
        return new AlertaResponse(intensidadActual, fecha, hora, alerta);
    }

    // Método para reiniciar el temporizador
    private void reiniciarTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Al cumplirse el tiempo, la intensidad actual se reinicia a cero
                intensidadActual = 0;
            }
        }, 30000); // 30 segundos en milisegundos
    }
}
