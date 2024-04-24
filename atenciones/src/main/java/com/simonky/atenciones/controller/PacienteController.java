package com.simonky.atenciones.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    
    
    @Autowired
    private PacienteService pacienteService;
    

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Paciente>>> getPacientes() {
        var pacientes = this.pacienteService.getPacientes();
        return ResponseEntity.ok(pacienteCollectionHateoas(pacientes)); 
    }


    @PostMapping
    public ResponseEntity<EntityModel<Paciente>> createPaciente(@Valid @RequestBody Paciente entity) {
        var pacienteRes = this.pacienteService.createPaciente(entity);
        EntityModel<Paciente> res = this.pacienteHateoas(pacienteRes);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Paciente>> getPacienteById(@PathVariable("id") Long id) {
        var paciente = this.pacienteService.getPacienteById(id);
        EntityModel<Paciente> res = this.pacienteHateoas(paciente);
        return ResponseEntity.ok(res);
    }

    
    
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Paciente>> updatePaciente(@PathVariable("id") Long id, @Valid @RequestBody Paciente entity) {
        var pacienteRes = this.pacienteService.updatePaciente(id, entity);
        EntityModel<Paciente> res = this.pacienteHateoas(pacienteRes);
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable("id") Long id) {
        this.pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{id}/atenciones")
    public ResponseEntity<CollectionModel<EntityModel<AtencionPacienteDto>>> getAtencionesPacientes(@PathVariable("id") Long id) {
        var atenciones = this.pacienteService.getAtencionesPacientes(id);
        return ResponseEntity.ok(atencionCollectionHateoas(id, atenciones));
    }

    @PostMapping("/{id}/atenciones")
    public ResponseEntity<EntityModel<AtencionPacienteDto>> createAtencionPaciente(@PathVariable("id") Long id, @Valid @RequestBody Atencion atencion) {
        var atencionRes = this.pacienteService.createAtencionPaciente(id, atencion);
        return ResponseEntity.ok(atencionHateoas(id, atencionRes));
    }

    @GetMapping("/{id}/atenciones/{atencionId}")
    public ResponseEntity<EntityModel<AtencionPacienteDto>> getAtencionPacientesById(@PathVariable("id") Long id, @PathVariable("atencionId") Long atencionId) {
        var atencionRes = this.pacienteService.getAtencionPacienteById(id, atencionId);
        return ResponseEntity.ok(atencionHateoas(id, atencionRes));
    }

    @PutMapping("/{id}/atenciones/{atencionId}")
    public ResponseEntity<EntityModel<AtencionPacienteDto>> updateAtencionPaciente(@PathVariable("id") Long id,  @PathVariable("atencionId") Long atencionId, @Valid @RequestBody Atencion atencion) {
        var atencionRes = this.pacienteService.updateAtencionPaciente(id, atencionId, atencion);
        return ResponseEntity.ok(atencionHateoas(id, atencionRes));
    }
    

    @DeleteMapping("/{id}/atenciones/{atencionId}")
    public ResponseEntity<Void> deleteAtencionPaciente(@PathVariable("id") Long id, @PathVariable("atencionId") Long atencionId) {
        pacienteService.deleteAtencionPaciente(id, atencionId);
        return ResponseEntity.noContent().build();
    }
    

    
    private EntityModel<Paciente> pacienteHateoas(Paciente entity){
        return EntityModel.of(entity,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(entity.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacientes()).withRel("pacientes")
        );
    }

    private CollectionModel<EntityModel<Paciente>> pacienteCollectionHateoas(List<Paciente> pacientes){
        List<EntityModel<Paciente>> dataRes = pacientes.stream()
            .map(p -> EntityModel.of(p,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(p.getId())).withSelfRel()
            )).collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacientes());
        CollectionModel<EntityModel<Paciente>> recursos = CollectionModel.of(dataRes, linkTo.withRel("pacientes"));
        return recursos;
    }

    private EntityModel<AtencionPacienteDto> atencionHateoas(Long pacienteId, AtencionPacienteDto entity){
        return EntityModel.of(entity,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAtencionPacientesById(pacienteId, entity.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAtencionesPacientes(pacienteId)).withRel("atenciones")
        );
    }

    private CollectionModel<EntityModel<AtencionPacienteDto>> atencionCollectionHateoas(Long pacienteId, List<AtencionPacienteDto> atenciones) {
        List<EntityModel<AtencionPacienteDto>> dataRes = atenciones.stream()
        .map(p -> EntityModel.of(p,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAtencionPacientesById(pacienteId, p.getId())).withSelfRel()
        )).collect(Collectors.toList());
    
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAtencionesPacientes(pacienteId));
        CollectionModel<EntityModel<AtencionPacienteDto>> recursos = CollectionModel.of(dataRes, linkTo.withRel("atenciones"));
        return recursos;
    }
    


}
