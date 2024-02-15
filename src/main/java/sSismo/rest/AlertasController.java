package sSismo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sSismo.servicios.SismoService;
import sSismo.utilidades.GeneradorAlertas;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.HEAD })
public class AlertasController {

    @Autowired
    private SismoService sismoService;

    @Autowired
    private GeneradorAlertas generadorAlertas;

    @GetMapping("/alertas")
    public AlertaResponse obtenerAlertaSismo() {
        double intensidadSismo = sismoService.obtenerIntensidadSismo();
        String fecha = sismoService.obtenerFecha();
        String hora = sismoService.obtenerHora();
        String alerta = generadorAlertas.generarAlerta(intensidadSismo);
        return new AlertaResponse(intensidadSismo, fecha, hora, alerta);
    }
}
