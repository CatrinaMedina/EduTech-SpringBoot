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

import com.edutech.ProyectoFullstack.entities.Curso;
import com.edutech.ProyectoFullstack.services.CursoServiceImple;
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
public class CursoRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CursoServiceImple cursoServiceImple;

    private List<Curso> listaCursos;

    @Test
    public void verCursosTest() throws Exception{
        when(cursoServiceImple.findByAll()).thenReturn(listaCursos);
        mockMvc.perform(get("/api/cursos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());        
    }

    @Test
    public void verUnCursoTest(){
        Curso unCurso = new Curso(1L, "quinto año", "clase java", 400000);
        try{
            when(cursoServiceImple.findById(1L)).thenReturn(Optional.of(unCurso));
            mockMvc.perform(get("/api/cursos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch (Exception ex){
           fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }

    @Test
    public void cursoNoExiste() throws Exception{
        when (cursoServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/cursos/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearCurso() throws Exception{
        Curso unCurso = new Curso (null, "sexto año", "clase ingles", 350000);
        Curso otroCurso = new Curso(5L, "septimo año", "clase religion", 300000);
        when(cursoServiceImple.save(any(Curso.class))).thenReturn(otroCurso);
        mockMvc.perform(post("/api/cursos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unCurso)))
            .andExpect(status().isCreated());
    }
        
    @Test
    public void modificarCursoTest() throws Exception {
        Curso cursoExistente = new Curso(1L, "quinto año", "clase software", 300000);
        Curso cursoModificado = new Curso(1L, "quinto año actualizado", "clase java avanzada", 450000);
        when(cursoServiceImple.findById(1L)).thenReturn(Optional.of(cursoExistente));
        when(cursoServiceImple.save(any(Curso.class))).thenReturn(cursoModificado);
        mockMvc.perform(put("/api/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cursoModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarCursoTest() throws Exception {
        Curso cursoAEliminar = new Curso(1L, "cuarto año", "clase bases de datos", 400000);
        when(cursoServiceImple.findById(1L)).thenReturn(Optional.of(cursoAEliminar));
        when(cursoServiceImple.delete(any(Curso.class))).thenReturn(Optional.of(cursoAEliminar));
        mockMvc.perform(delete("/api/cursos/1"))
            .andExpect(status().isOk());
    }

}
