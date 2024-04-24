package com.simonky.atenciones.model;


import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern.Flag;

@Entity
@Table(name = "paciente")
public class Paciente extends RepresentationModel<Paciente>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @Column(name = "nombre")
    @NotEmpty(message = "El nombre es requerido.")
    @NotBlank(message = "El nombre es requerido.")
    private String nombre;

    @Column(name = "fono")
    private String fono;

    @Column(name = "email")
    @NotEmpty(message = "El email es requerido.")
    @NotBlank(message = "El email es requerido.")
    @Email(message = "El email es inválido.", flags = { Flag.CASE_INSENSITIVE })
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
