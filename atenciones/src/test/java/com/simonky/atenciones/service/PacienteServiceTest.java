package com.simonky.atenciones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simonky.atenciones.exceptions.PacienteNotFoundException;
import com.simonky.atenciones.model.Paciente;
import com.simonky.atenciones.repository.AtencionRepository;
import com.simonky.atenciones.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {
    
    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacienteRepository pacienteRepositoryTest;

    @Mock
    private AtencionRepository atencionRepositoryTest;

    @Test
    public void guardarPacienteTest(){
        Paciente paciente = new Paciente();
       
        paciente.setEmail("email@gmail.com");
        paciente.setFono("123456");
        paciente.setNombre("Simon");

        when(pacienteRepositoryTest.save(any())).thenReturn(paciente);

        Paciente res = pacienteService.createPaciente(paciente);

        assertEquals("email@gmail.com", res.getEmail());
        assertEquals("123456", res.getFono());
        assertEquals("Simon", res.getNombre());
    }


    @Test
    public void eliminarPacienteExistenteTest() {
        Long id = 1L;
        Paciente paciente = new Paciente();
       
        paciente.setEmail("email@gmail.com");
        paciente.setFono("123456");
        paciente.setNombre("Simon");


        when(pacienteRepositoryTest.findById(id)).thenReturn(Optional.of(paciente));

        pacienteService.deletePaciente(id);

        verify(pacienteRepositoryTest, times(1)).deleteById(id);
    }

    @Test
    public void eliminarPacienteNoExistenteTest() {
        
        Long id = 999L;

        when(pacienteRepositoryTest.findById(id)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> pacienteService.deletePaciente(id));

        verify(pacienteRepositoryTest, never()).deleteById(id);
    }

}
