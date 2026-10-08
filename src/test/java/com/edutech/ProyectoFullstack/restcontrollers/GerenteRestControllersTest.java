package com.edutech.ProyectoFullstack.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.edutech.ProyectoFullstack.entities.Gerente;
import com.edutech.ProyectoFullstack.services.GerenteServiceImple;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@AutoConfigureMockMvc
public class GerenteRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
    private GerenteServiceImple gerenteServiceImple;

    private List<Gerente> listaGerentes;
    
    @Test
    public void verGerentesTest() throws Exception {
        when(gerenteServiceImple.findByAll()).thenReturn(listaGerentes);
        mockMvc.perform(get("/api/gerentes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());   
    }

    @Test
    public void verUnGerenteTest() {
        Gerente unGerente = new Gerente(1L, "juan", "perez","juanperez@gmail.com", "gerente de proyectos");
        try {
            when(gerenteServiceImple.findById(1L)).thenReturn(Optional.of(unGerente));
            mockMvc.perform(get("/api/gerentes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }


    @Test
    public void gerenteNoExiste() throws Exception{
        when (gerenteServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/gerentes/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearGerente() throws Exception{
        Gerente unGerente = new Gerente (null, "valentina","rojas", "valentinarojas@gmail.com", "Gestión de Proyectos Tecnológicos");
        Gerente otroGerente = new Gerente (5L, "tomas","herrera", "tomasherrera@gmail.com", "Liderazgo y Estrategia Organizacional");
        when(gerenteServiceImple.save(any(Gerente.class))).thenReturn(otroGerente);
        mockMvc.perform(post("/api/gerentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unGerente)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarGerenteTest() throws Exception {
        Gerente gerenteExistente = new Gerente(1L, "juan", "perez", "juanperez@gmail.com", "gerente de proyectos");
        Gerente gerenteModificado = new Gerente(1L, "juan", "perez", "juanperez@gmail.com", "gerente general");
        when(gerenteServiceImple.findById(1L)).thenReturn(Optional.of(gerenteExistente));
        when(gerenteServiceImple.save(any(Gerente.class))).thenReturn(gerenteModificado);
        mockMvc.perform(put("/api/gerentes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gerenteModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarGerenteTest() throws Exception {
        Gerente gerenteAEliminar = new Gerente(1L, "juan", "perez", "juanperez@gmail.com", "gerente de proyectos");
        when(gerenteServiceImple.findById(1L)).thenReturn(Optional.of(gerenteAEliminar));
        when(gerenteServiceImple.delete(any(Gerente.class))).thenReturn(Optional.of(gerenteAEliminar));
        mockMvc.perform(delete("/api/gerentes/1"))
            .andExpect(status().isOk());
    }
}
