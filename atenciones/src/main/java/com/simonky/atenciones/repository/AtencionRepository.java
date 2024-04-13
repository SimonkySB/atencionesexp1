package com.simonky.atenciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simonky.atenciones.dtos.AtencionPacienteDto;
import com.simonky.atenciones.model.Atencion;

public interface AtencionRepository extends JpaRepository<Atencion, Long>{
    
    @Query("SELECT new com.simonky.atenciones.dtos.AtencionPacienteDto(a.id, a.fechaAtencion, a.valor, a.observacion) FROM Atencion a WHERE a.paciente.id = :pacienteId")
    List<AtencionPacienteDto> findByPacienteId(Long pacienteId);
}
