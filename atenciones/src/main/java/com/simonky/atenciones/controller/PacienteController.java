package com.simonky.atenciones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simonky.atenciones.dtos.AtencionPacienteDto;
import com.simonky.atenciones.model.Atencion;
import com.simonky.atenciones.model.Paciente;
import com.simonky.atenciones.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RequestMapping(value = "/pacientes")
@RestController
@Validated
public class PacienteController {
    

    private PacienteService pacienteService;
    
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
        this.pacienteService.Iniciar();
    }



    @GetMapping
    public List<Paciente> getPacientes() {
        return this.pacienteService.getPacientes();
    }


    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@Valid @RequestBody Paciente entity) {
        return ResponseEntity.ok(this.pacienteService.createPaciente(entity));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.pacienteService.getPacienteById(id));
    }

    
    
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable("id") Long id, @Valid @RequestBody Paciente entity) {
        return ResponseEntity.ok(this.pacienteService.updatePaciente(id, entity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable("id") Long id) {
        this.pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/atenciones")
    public ResponseEntity<List<AtencionPacienteDto>> getAtencionesPacientes(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.pacienteService.getAtencionesPacientes(id));
    }

    @PostMapping("/{id}/atenciones")
    public ResponseEntity<Atencion> createAtencionPaciente(@PathVariable("id") Long id, @Valid @RequestBody Atencion atencion) {
        return ResponseEntity.ok(this.pacienteService.createAtencionPaciente(id, atencion));
    }

    @DeleteMapping("/{id}/atenciones/{atencionId}")
    public ResponseEntity<Void> deleteAtencionPaciente(@PathVariable("id") Long id, @PathVariable("atencionId") Long atencionId) {
        pacienteService.deleteAtencionPaciente(id, atencionId);
        return ResponseEntity.noContent().build();
    }
    
    


}
