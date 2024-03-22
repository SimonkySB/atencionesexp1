package com.simonky.atenciones;

import java.time.LocalDateTime;


public class Atencion {
    
    private int atencionId;
    private int pacienteId;
    private LocalDateTime fechaAtencion;
    private String observacion;


    public Atencion(int atencionId, int pacienteId, LocalDateTime fechaAtencion, String observacion) {
        this.atencionId = atencionId;
        this.pacienteId = pacienteId;
        this.fechaAtencion = fechaAtencion;
        this.observacion = observacion;
    }


    public int getAtencionId() {
        return atencionId;
    }


    public int getPacienteId() {
        return pacienteId;
    }


    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }


    public String getObservacion() {
        return observacion;
    }


    


    

}
