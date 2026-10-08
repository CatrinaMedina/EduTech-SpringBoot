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

import com.edutech.ProyectoFullstack.entities.Incidencia;
import com.edutech.ProyectoFullstack.services.IncidenciaServiceImple;
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
public class IncidenciaRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncidenciaServiceImple incidenciaServiceImple;

    private List<Incidencia> listaIncidencias;

    @Test
    public void verIncidenciasTest() throws Exception {
        when(incidenciaServiceImple.findByAll()).thenReturn(listaIncidencias);
        mockMvc.perform(get("/api/incidencias")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());   
    }

    @Test
    public void verUnIncidenciaTest() {
        Incidencia unIncidencia = new Incidencia(1L, "no hay agua en la sede", "reportado","01/10/2023");
        try {
            when(incidenciaServiceImple.findById(1L)).thenReturn(Optional.of(unIncidencia));
            mockMvc.perform(get("/api/incidencias/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }

    @Test
    public void incidenciaNoExiste() throws Exception{
        when (incidenciaServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/incidencias/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearIncidencia() throws Exception{
        Incidencia unIncidencia = new Incidencia (null, "problemas de conexión a internet","en progreso", "15/11/2023");
        Incidencia otroIncidencia = new Incidencia (5L, "falla en el proyector de la sala 4","resuelto", "22/12/2023");
        when(incidenciaServiceImple.save(any(Incidencia.class))).thenReturn(otroIncidencia);
        mockMvc.perform(post("/api/incidencias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unIncidencia)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarIncidenciaTest() throws Exception {
        Incidencia incidenciaExistente = new Incidencia(1L, "no hay agua en la sede", "reportado", "01/10/2023");
        Incidencia incidenciaModificada = new Incidencia(1L, "no hay agua en la sede", "en progreso", "01/10/2023");
        when(incidenciaServiceImple.findById(1L)).thenReturn(Optional.of(incidenciaExistente));
        when(incidenciaServiceImple.save(any(Incidencia.class))).thenReturn(incidenciaModificada);
        mockMvc.perform(put("/api/incidencias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidenciaModificada)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarIncidenciaTest() throws Exception {
        Incidencia incidenciaAEliminar = new Incidencia(1L, "no hay agua en la sede", "reportado", "01/10/2023");
        when(incidenciaServiceImple.findById(1L)).thenReturn(Optional.of(incidenciaAEliminar));
        when(incidenciaServiceImple.delete(any(Incidencia.class))).thenReturn(Optional.of(incidenciaAEliminar));
        mockMvc.perform(delete("/api/incidencias/1"))
                .andExpect(status().isOk());
    }
}
