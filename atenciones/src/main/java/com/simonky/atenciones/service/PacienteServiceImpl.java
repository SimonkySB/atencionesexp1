package com.simonky.atenciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonky.atenciones.dtos.AtencionPacienteDto;
import com.simonky.atenciones.exceptions.AtencionNotFoundException;
import com.simonky.atenciones.exceptions.PacienteNotFoundException;
import com.simonky.atenciones.model.Atencion;
import com.simonky.atenciones.model.Paciente;
import com.simonky.atenciones.repository.AtencionRepository;
import com.simonky.atenciones.repository.PacienteRepository;


@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AtencionRepository atencionesRepository;


    @Override
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente getPacienteById(Long id) {
        return pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));
    }

    @Override
    public void deletePaciente(Long id) {
        pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));

        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente createPaciente(Paciente entity) {
        entity.setId(null);
        return pacienteRepository.save(entity);
    }

    @Override
    public Paciente updatePaciente(Long id, Paciente nuevoPaciente) {
        Paciente paciente = this.pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));

        paciente.setEmail(nuevoPaciente.getEmail());
        paciente.setFono(nuevoPaciente.getFono());
        paciente.setNombre(nuevoPaciente.getNombre());
        return pacienteRepository.save(paciente);
    }

    
    @Override
    public List<AtencionPacienteDto> getAtencionesPacientes(Long id) {
        pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));
        
        return this.atencionesRepository.findByPacienteId(id);
    }

     @Override
    public AtencionPacienteDto getAtencionPacienteById(Long pacienteId, Long atencionId) {
        var atencion = this.atencionesRepository.findById(atencionId)
            .stream()
            .filter(a -> a.getPaciente().getId() == pacienteId)
            .findFirst()
            .orElseThrow(() -> new AtencionNotFoundException(atencionId));
        return fromEntity(atencion);
    }

    @Override
    public AtencionPacienteDto createAtencionPaciente(Long id, Atencion atencion) {
        Paciente paciente = this.pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));

        atencion.setId(null);
        atencion.setPaciente(paciente);

        return fromEntity(this.atencionesRepository.save(atencion));
    }

    @Override
    public void deleteAtencionPaciente(Long id, Long atencionId) {
        Atencion atencion = this.atencionesRepository.findById(atencionId)
            .orElseThrow(() -> new AtencionNotFoundException(id));

        if(atencion.getPaciente().getId() != id) {
            throw new AtencionNotFoundException(id);
        }

        this.atencionesRepository.delete(atencion);
    }

    @Override
    public AtencionPacienteDto updateAtencionPaciente(Long pacienteId, Long atencionId, Atencion atencion) {
        Atencion atencionDb = this.atencionesRepository.findById(atencionId)
            .orElseThrow(() -> new AtencionNotFoundException(atencionId));

        if(atencionDb.getPaciente().getId() != pacienteId) {
            throw new AtencionNotFoundException(atencionId);
        }

        atencionDb.setFechaAtencion(atencion.getFechaAtencion());
        atencionDb.setObservacion(atencion.getObservacion());
        atencionDb.setValor(atencion.getValor());
        

        return fromEntity(this.atencionesRepository.save(atencionDb));
    }

 

    public AtencionPacienteDto fromEntity(Atencion atencion){
        return new AtencionPacienteDto(atencion.getId(), atencion.getFechaAtencion(), atencion.getValor(), atencion.getObservacion());
    }
}
