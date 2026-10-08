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

import com.edutech.ProyectoFullstack.entities.Usuario;
import com.edutech.ProyectoFullstack.repositories.UsuarioRepository;

@SpringBootTest
public class UsuarioServicesTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImple usuarioServiceImple;

    private Usuario usuario;    

    @BeforeEach
    private void Inicio() {
        usuario = new Usuario(1L, "lola", "lola22@gmail.com","lola1234");
    }
    @Test
    public void findByAllTest() {
        List<Usuario> lista = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(lista);
        List<Usuario> resultado = usuarioServiceImple.findByAll();
        assertEquals(1, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    public void findByIdTest() {
        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(usuario));
        Usuario resultado = usuarioServiceImple.findById(1L).orElse(null);
        assertEquals("lola", resultado.getNombre());
        verify(usuarioRepository).findById(1L);
    }
    
    @Test
    public void saveTest() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario usuarioTest = usuarioServiceImple.save(usuario);
        assertNotNull(usuarioTest);
        assertEquals("lola", usuarioTest.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    public void deleteTest() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Optional <Usuario> resultado = usuarioServiceImple.delete(usuario);
        assertTrue(resultado.isPresent());
        assertEquals("lola", resultado.get().getNombre());
        verify(usuarioRepository).delete(usuario);
    }

}
