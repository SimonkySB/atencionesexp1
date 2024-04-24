package com.simonky.atenciones.service;

import java.util.List;

import com.simonky.atenciones.dtos.AtencionPacienteDto;
import com.simonky.atenciones.model.Atencion;
import com.simonky.atenciones.model.Paciente;


public interface PacienteService {
    List<Paciente> getPacientes();
    Paciente getPacienteById(Long id);
    void deletePaciente(Long Id);
    Paciente createPaciente(Paciente entity);
    Paciente updatePaciente(Long Id, Paciente entity);


    List<AtencionPacienteDto> getAtencionesPacientes(Long id);
    AtencionPacienteDto getAtencionPacienteById(Long pacienteId, Long atencionId);
    AtencionPacienteDto createAtencionPaciente(Long id, Atencion atencion);
    void deleteAtencionPaciente(Long id, Long atencionId);
    AtencionPacienteDto updateAtencionPaciente(Long pacienteId, Long atencionId, Atencion atencion);
}
