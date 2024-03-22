package com.simonky.atenciones;

public class CantidadAtencionPaciente {
    
    private int pacienteId;
    private String pacienteNombre;
    private int cantidad;

    
    public CantidadAtencionPaciente(int pacienteId, String pacienteNombre, int cantidad) {
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.cantidad = cantidad;
    }


    public int getPacienteId() {
        return pacienteId;
    }


    public String getPacienteNombre() {
        return pacienteNombre;
    }


    public int getCantidad() {
        return cantidad;
    }


    
}
