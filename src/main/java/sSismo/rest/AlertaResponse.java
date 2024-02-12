package sSismo.rest;

public class AlertaResponse {
    private double intensidad;
    private String alerta;

    // Constructor, getters y setters
    public AlertaResponse(double intensidad, String alerta) {
        this.intensidad = intensidad;
        this.alerta = alerta;
    }

    public double getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(double intensidad) {
        this.intensidad = intensidad;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }
}
