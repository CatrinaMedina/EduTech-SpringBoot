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

import com.edutech.ProyectoFullstack.entities.Usuario;
import com.edutech.ProyectoFullstack.services.UsuarioServiceImple;
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
public class UsuarioRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioServiceImple usuarioServiceImple;

    private List<Usuario> listaUsuarios;

    @Test
    public void verUsuariosTest() throws Exception {
        when(usuarioServiceImple.findByAll()).thenReturn(listaUsuarios);
        mockMvc.perform(get("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());   
    }

    @Test
    public void verUnUsuarioTest() {
        Usuario unUsuario = new Usuario(1L, "lola", "lola22@gmail.com","lola1234");
        try {
            when(usuarioServiceImple.findById(1L)).thenReturn(Optional.of(unUsuario));
            mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }


    @Test
    public void usuarioNoExiste() throws Exception{
        when (usuarioServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/usuarios/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuario() throws Exception{
        Usuario unUsuario = new Usuario (null, "marcos","marcos98@gmail.com", "marcos5678");
        Usuario otroUsuario = new Usuario (5L, "camila","camila.lopez@gmail.com", "camila456");
        when(usuarioServiceImple.save(any(Usuario.class))).thenReturn(otroUsuario);
        mockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unUsuario)))
            .andExpect(status().isCreated());
    }

    @Test
    public void modificarUsuarioTest() throws Exception {
        Usuario usuarioExistente = new Usuario(1L, "lola", "lola22@gmail.com", "lola1234");
        Usuario usuarioModificado = new Usuario(1L, "lola", "lola22@gmail.com", "nuevaClaveSegura");
        when(usuarioServiceImple.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioServiceImple.save(any(Usuario.class))).thenReturn(usuarioModificado);
        mockMvc.perform(put("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        Usuario usuarioAEliminar = new Usuario(1L, "lola", "lola22@gmail.com", "lola1234");
        when(usuarioServiceImple.findById(1L)).thenReturn(Optional.of(usuarioAEliminar));
        when(usuarioServiceImple.delete(any(Usuario.class))).thenReturn(Optional.of(usuarioAEliminar));
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());
    }
}
