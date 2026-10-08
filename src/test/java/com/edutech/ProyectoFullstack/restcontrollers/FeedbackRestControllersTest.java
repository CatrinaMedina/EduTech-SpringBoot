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


import com.edutech.ProyectoFullstack.entities.Feedback;
import com.edutech.ProyectoFullstack.services.FeedbackServiceImple;
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
public class FeedbackRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FeedbackServiceImple feedbackServiceImple;

    private List <Feedback> listaFeedbacks;

    @Test
    public void verFeedbacksTest() throws Exception {
        when(feedbackServiceImple.findByAll()).thenReturn(listaFeedbacks);
        mockMvc.perform(get("/api/feedbacks")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());   
    }

    @Test
    public void verUnFeedbackTest() {
        Feedback unFeedback = new Feedback(1L, "Buen curso", 5,"10/10/2023");
        try {
            when(feedbackServiceImple.findById(1L)).thenReturn(Optional.of(unFeedback));
            mockMvc.perform(get("/api/feedbacks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }


    @Test
    public void feedbackNoExiste() throws Exception{
        when (feedbackServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/feedbacks/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearFeedback() throws Exception{
        Feedback unFeedback = new Feedback (null, "Curso muy completo, aprendí bastante", 5, "01/03/2024");
        Feedback otroFeedback = new Feedback (5L, "El contenido es bueno", 5, "18/01/2025");
        when(feedbackServiceImple.save(any(Feedback.class))).thenReturn(otroFeedback);
        mockMvc.perform(post("/api/feedbacks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unFeedback)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarFeedbackTest() throws Exception {
        Feedback feedbackExistente = new Feedback(1L, "Muy buen curso", 4, "10/05/2024");
        Feedback feedbackModificado = new Feedback(1L, "Excelente contenido, muy didáctico", 5, "12/05/2024");
        when(feedbackServiceImple.findById(1L)).thenReturn(Optional.of(feedbackExistente));
        when(feedbackServiceImple.save(any(Feedback.class))).thenReturn(feedbackModificado);
        mockMvc.perform(put("/api/feedbacks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(feedbackModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarFeedbackTest() throws Exception {
        Feedback feedbackAEliminar = new Feedback(1L, "Curso muy teórico", 3, "03/02/2024");
        when(feedbackServiceImple.findById(1L)).thenReturn(Optional.of(feedbackAEliminar));
        when(feedbackServiceImple.delete(any(Feedback.class))).thenReturn(Optional.of(feedbackAEliminar));
        mockMvc.perform(delete("/api/feedbacks/1"))
                .andExpect(status().isOk());
    }

}
