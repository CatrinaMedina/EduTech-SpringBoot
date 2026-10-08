package com.edutech.ProyectoFullstack.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.edutech.ProyectoFullstack.entities.Feedback;
import com.edutech.ProyectoFullstack.repositories.FeedbackRepository;

@SpringBootTest
public class FeedbackServicesTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImple feedbackServiceImple;  

    private Feedback feedback;  

    @BeforeEach
    private void Inicio() {
        feedback = new Feedback(1L, "Buen curso", 5, "10/10/2023");
    } 

    @Test   
    public void findByAllTest() {
        List<Feedback> lista = Arrays.asList(feedback);
        when(feedbackRepository.findAll()).thenReturn(lista);
        List<Feedback> resultado = feedbackServiceImple.findByAll();
        assertEquals(1, resultado.size());
        verify(feedbackRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(feedbackRepository.findById(1L)).thenReturn(java.util.Optional.of(feedback));
        Feedback resultado = feedbackServiceImple.findById(1L).orElse(null);
        assertEquals("Buen curso", resultado.getComentario());
        verify(feedbackRepository).findById(1L);
    }

    @Test
    public void saveTest() {    
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);
        Feedback feedbackTest = feedbackServiceImple.save(feedback);
        assertNotNull(feedbackTest);
        assertEquals("Buen curso", feedbackTest.getComentario());
        verify(feedbackRepository).save(feedback);
    }  
    
    @Test
    public void deleteTest() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        Optional <Feedback> resultado = feedbackServiceImple.delete(feedback);
        assertTrue(resultado.isPresent());
        assertEquals("Buen curso", resultado.get().getComentario());
        verify(feedbackRepository).delete(feedback);
    }

}
