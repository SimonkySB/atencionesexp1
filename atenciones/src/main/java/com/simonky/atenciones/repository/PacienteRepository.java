package com.simonky.atenciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simonky.atenciones.model.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{
    
}
