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

import com.edutech.ProyectoFullstack.entities.Curso;
import com.edutech.ProyectoFullstack.repositories.CursoRepository;

@SpringBootTest
public class CursoServicesTest {
    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoServiceImple cursoServiceImple;

    private Curso curso;

    @BeforeEach
    private void Inicio() {
        curso = new Curso(1L, "quinto año", "clase java", 400000);
    }

    @Test
    public void findByAllTest(){
        List<Curso> lista = Arrays.asList(curso);
        when(cursoRepository.findAll()).thenReturn(lista);
        List<Curso> resultado = cursoServiceImple.findByAll();
        assertEquals(1, resultado.size());
        verify(cursoRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(cursoRepository.findById(1L)).thenReturn(java.util.Optional.of(curso));
        Curso resultado = cursoServiceImple.findById(1L).orElse(null);
        assertEquals("quinto año", resultado.getTitulo());
        verify(cursoRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);
        Curso cursoTest = cursoServiceImple.save(curso);
        assertNotNull(cursoTest);
        assertEquals("quinto año", cursoTest.getTitulo());
        verify(cursoRepository).save(curso);
    }
    
    @Test
    public void deleteTest() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));
        Optional <Curso> resultado = cursoServiceImple.delete(curso);
        assertTrue(resultado.isPresent());
        assertEquals("quinto año", resultado.get().getTitulo());
        verify(cursoRepository).delete(curso);
    }
}
