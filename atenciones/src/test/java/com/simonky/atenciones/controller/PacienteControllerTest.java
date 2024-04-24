package com.simonky.atenciones.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.simonky.atenciones.model.Paciente;
import com.simonky.atenciones.service.PacienteServiceImpl;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteServiceImpl pacienteServiceMock;

    @Test
    public void listarPacientesTest() throws Exception {
        Paciente paciente1 = new Paciente();
       
        paciente1.setEmail("email1@gmail.com");
        paciente1.setFono("123456");
        paciente1.setNombre("Simon1");

        Paciente paciente2 = new Paciente();
       
        paciente2.setEmail("email2@gmail.com");
        paciente2.setFono("123456");
        paciente2.setNombre("Simon2");


        List<Paciente> lista = Arrays.asList(paciente1, paciente2);
        when(pacienteServiceMock.getPacientes()).thenReturn(lista);

        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].email").value("email1@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].nombre").value("Simon1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[0].fono").value("123456"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].email").value("email2@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].nombre").value("Simon2"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.pacienteList[1].fono").value("123456"))
            ;
    }

    @Test
    public void eliminarPacienteTest() throws Exception {
        Long id = 1L;

        doNothing().when(pacienteServiceMock).deletePaciente(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}", id))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(pacienteServiceMock, times(1)).deletePaciente(id);

    }
}
