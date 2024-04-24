package com.simonky.atenciones.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.simonky.atenciones.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteRepositoryTest {
    
    @Autowired
    private PacienteRepository pacienteRepository;


    @Test
    public void guardarPaciente(){
        Paciente paciente = new Paciente();
       
        paciente.setEmail("email@gmail.com");
        paciente.setFono("123456");
        paciente.setNombre("Simon");
        
        Paciente res = pacienteRepository.save(paciente);

        assertNotNull(res.getId());
        assertEquals("email@gmail.com", res.getEmail());
        assertEquals("123456", res.getFono());
        assertEquals("Simon", res.getNombre());
    }

    @Test
    public void eliminarPaciente() {
        Paciente paciente = new Paciente();
       
        paciente.setEmail("email@gmail.com");
        paciente.setFono("123456");
        paciente.setNombre("Simon");

        
        Paciente res = pacienteRepository.save(paciente);

        assertNotNull(res.getId());

        pacienteRepository.deleteById(res.getId());

        Optional<Paciente> deletedData = pacienteRepository.findById(res.getId());
        assertFalse(deletedData.isPresent());
    }
}
