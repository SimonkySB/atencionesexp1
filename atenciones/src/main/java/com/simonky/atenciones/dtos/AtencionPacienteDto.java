package com.simonky.atenciones.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtencionPacienteDto {
    private Long id;
    private LocalDateTime fechaAtencion;
    private BigDecimal valor;
    private String observacion;
    
    public AtencionPacienteDto(Long id, LocalDateTime fechaAtencion, BigDecimal valor, String observacion) {
        this.id = id;
        this.fechaAtencion = fechaAtencion;
        this.valor = valor;
        this.observacion = observacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDateTime fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
}
