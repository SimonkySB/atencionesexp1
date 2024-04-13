package com.simonky.atenciones.service;

import java.util.ArrayList;
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
    public void Iniciar() {
        List<Paciente> pacientes = new ArrayList<>();
        
        for(int i = 0; i < 5; i++) {
            Paciente user = new Paciente();
            user.setNombre("Paciente " + (i+1));
            user.setEmail("paciente" + (i+1) + "@email.com");
            user.setFono("+569 123" + (i+1));
            pacientes.add(user);
        }
        
        this.pacienteRepository.saveAll(pacientes);
    }

    @Override
    public List<AtencionPacienteDto> getAtencionesPacientes(Long id) {
        pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));
        
        return this.atencionesRepository.findByPacienteId(id);
    }

    @Override
    public Atencion createAtencionPaciente(Long id, Atencion atencion) {
        Paciente paciente = this.pacienteRepository.findById(id)
            .orElseThrow(() -> new PacienteNotFoundException(id));

        atencion.setId(null);
        atencion.setPaciente(paciente);

        return this.atencionesRepository.save(atencion);
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
    
}
