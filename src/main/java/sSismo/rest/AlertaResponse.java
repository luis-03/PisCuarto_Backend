package sSismo.rest;

public class AlertaResponse {
    private double intensidad;
    private String fecha;
    private String hora;
    private String alerta;

    // Constructor, getters y setters
    public AlertaResponse(double intensidad, String fecha, String hora, String alerta) {
        this.intensidad = intensidad;
        this.fecha = fecha;
        this.hora = hora;
        this.alerta = alerta;
    }

    public double getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(double intensidad) {
        this.intensidad = intensidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }
}
