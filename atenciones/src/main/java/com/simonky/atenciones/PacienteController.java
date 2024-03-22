package com.simonky.atenciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class PacienteController {
    
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Atencion> atenciones = new ArrayList<>();


    public PacienteController() {
        pacientes.add(new Paciente(1, "simon", "email@gmail.com", "123456"));
        pacientes.add(new Paciente(2, "rocio", "email@gmail.com", "123456"));
        pacientes.add(new Paciente(3, "carla", "email@gmail.com", "123456"));

        atenciones.add(new Atencion(1, 1, LocalDateTime.now(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(2, 1, LocalDateTime.now().plusDays(1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(3, 2, LocalDateTime.now(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(4, 2, LocalDateTime.now().plusDays(1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(5, 2, LocalDateTime.now().plusDays(2), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(6, 3, LocalDateTime.now(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(7, 3, LocalDateTime.now().plusDays(1), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
        atenciones.add(new Atencion(8, 3, LocalDateTime.now().plusDays(2), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis maximus urna a ornare. Curabitur nunc leo, faucibus non consequat at, eleifend id augue"));
    }



    @GetMapping("/pacientes")
    public List<Paciente> listarPacientes() {
        return this.pacientes;
    }


    @GetMapping("/pacientes/{id}")
    public Paciente obtenerPacientePorId(@PathVariable int id) {
        Paciente paciente = this.pacientes.stream()
            .filter(u -> u.getPacienteId() == id)
            .findFirst()
            .orElse(null);

        if(paciente == null ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }
   
        return paciente;
    }


    @GetMapping("/pacientes/{id}/atenciones")
    public List<Atencion> listarAtencionesPorPaciente(@PathVariable int id) {
        Paciente paciente = this.pacientes.stream()
            .filter(u -> u.getPacienteId() == id)
            .findFirst()
            .orElse(null);

        if(paciente == null ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }


        return this.atenciones.stream().filter(a -> a.getPacienteId() == id).toList();
    }


    @GetMapping("/atenciones")
    public List<Atencion> listarAtenciones() {
        return this.atenciones;
    }

    @GetMapping("/atenciones/{id}")
    public Atencion obtenerAtencionesPorId(@PathVariable int id) {
        Atencion atencion = this.atenciones.stream()
            .filter(u -> u.getAtencionId() == id)
            .findFirst()
            .orElse(null);

        if(atencion == null ) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Atención no encontrada");
        }
   
        return atencion;
    }

    @GetMapping("/cantidadAtencionesPorPaciente")
    public List<CantidadAtencionPaciente> cantidadAtencionesPorPaciente() {
        List<CantidadAtencionPaciente> data = new ArrayList<>();

        for (Paciente p : pacientes) {
            int cantidad = (int)this.atenciones.stream().filter(a -> a.getPacienteId() == p.getPacienteId()).count();
            data.add(new CantidadAtencionPaciente(p.getPacienteId(), p.getNombre(), cantidad));
        }

        return data;
    }

    
}
