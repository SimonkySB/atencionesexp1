package com.simonky.atenciones;


public class Paciente {
    private int pacienteId;
    private String nombre;
    private String email;
    private String fono;

    public Paciente(int pacienteId, String nombre, String email, String fono) {
        this.pacienteId = pacienteId;
        this.nombre = nombre;
        this.email = email;
        this.fono = fono;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getFono() {
        return fono;
    }
}
