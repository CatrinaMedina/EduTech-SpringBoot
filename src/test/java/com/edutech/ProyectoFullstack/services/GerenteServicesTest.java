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

import com.edutech.ProyectoFullstack.entities.Gerente;
import com.edutech.ProyectoFullstack.repositories.GerenteRepository;

@SpringBootTest
public class GerenteServicesTest {
    @Mock
    private GerenteRepository gerenteRepository;
     
    @InjectMocks
    private GerenteServiceImple gerenteServiceImple;

    private Gerente gerente;

    @BeforeEach
    private void Inicio() {
        gerente = new Gerente(1L, "juan", "perez", "juanperez@gmail.com","gerente de proyectos");
    }
    @Test
    public void findByAllTest() {
        List<Gerente> lista = Arrays.asList(gerente);
        when(gerenteRepository.findAll()).thenReturn(lista);
        List<Gerente> resultado = gerenteServiceImple.findByAll();
        assertEquals(1, resultado.size());
        verify(gerenteRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(gerenteRepository.findById(1L)).thenReturn(java.util.Optional.of(gerente));
        Gerente resultado = gerenteServiceImple.findById(1L).orElse(null);
        assertEquals("juan", resultado.getNombre());
        verify(gerenteRepository).findById(1L);
    }

    @Test
    public void saveTest() {
        when(gerenteRepository.save(any(Gerente.class))).thenReturn(gerente);
        Gerente gerenteTest = gerenteServiceImple.save(gerente);
        assertNotNull(gerenteTest);
        assertEquals("juan", gerenteTest.getNombre());  
        verify(gerenteRepository).save(gerente);
    }

    @Test
    public void deleteTest() {
        when(gerenteRepository.findById(1L)).thenReturn(Optional.of(gerente));
        Optional <Gerente> resultado = gerenteServiceImple.delete(gerente);
        assertTrue(resultado.isPresent());
        assertEquals("juan", resultado.get().getNombre());
        verify(gerenteRepository).delete(gerente);
    }

}
