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

import com.edutech.ProyectoFullstack.entities.Incidencia;
import com.edutech.ProyectoFullstack.repositories.IncidenciaRepository;

@SpringBootTest
public class IncidenciaServicesTest {
    @Mock
    private IncidenciaRepository incidenciaRepository;

    @InjectMocks
    private IncidenciaServiceImple incidenciaServiceImple;  

    private Incidencia incidencia;  

    @BeforeEach
    private void Inicio() {
        incidencia = new Incidencia(1L, "no hay agua en la sede", "reportado", "01/10/2023");
    }

    @Test
    public void findByAllTest() {
        List<Incidencia> lista = Arrays.asList(incidencia);
        when(incidenciaRepository.findAll()).thenReturn(lista);
        List<Incidencia> resultado = incidenciaServiceImple.findByAll();
        assertEquals(1, resultado.size());
        verify(incidenciaRepository).findAll();
    }
    @Test
    public void findByIdTest() {
        when(incidenciaRepository.findById(1L)).thenReturn(java.util.Optional.of(incidencia));
        Incidencia resultado = incidenciaServiceImple.findById(1L).orElse(null);
        assertEquals("no hay agua en la sede", resultado.getDescripcion());
        verify(incidenciaRepository).findById(1L);
    }
    
    @Test
    public void saveTest() {
        when(incidenciaRepository.save(any(Incidencia.class))).thenReturn(incidencia);
        Incidencia incidenciaTest = incidenciaServiceImple.save(incidencia);
        assertNotNull(incidenciaTest);
        assertEquals("no hay agua en la sede", incidenciaTest.getDescripcion());
        verify(incidenciaRepository).save(incidencia);
    }

    @Test
    public void deleteTest() {
        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(incidencia));
        Optional <Incidencia> resultado = incidenciaServiceImple.delete(incidencia);
        assertTrue(resultado.isPresent());
        assertEquals("no hay agua en la sede", resultado.get().getDescripcion());
        verify(incidenciaRepository).delete(incidencia);
    }

}
