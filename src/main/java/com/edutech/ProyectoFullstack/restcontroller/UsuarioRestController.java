package com.edutech.ProyectoFullstack.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.edutech.ProyectoFullstack.entities.Usuario;
import com.edutech.ProyectoFullstack.services.UsuarioService;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Métodos asociados a usuarios")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener lista de usuarios", description = "Devuelve todos los usuarios disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Usuario.class)))
    @GetMapping
    public List<Usuario> mostrarUsuarios(){
        return usuarioService.findByAll();
    }       
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene el detalle de un usuario específico")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class)))  
    @PostMapping
    public ResponseEntity<Usuario> crear (@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(unUsuario));
    }
    @Operation(summary = "Modificar un usuario existente", description = "Actualiza los datos de un usuario específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario modificado correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()){
            Usuario usuarioExiste = usuarioOptional.get();
            usuarioExiste.setNombre(unUsuario.getNombre());
            usuarioExiste.setEmail(unUsuario.getEmail());
            usuarioExiste.setContraseña(unUsuario.getContraseña());
            Usuario usuarioModificado = usuarioService.save(usuarioExiste);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Eliminar usuario", description = "Elimina usuario por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Usuario unUsuario = new Usuario();
        unUsuario.setId(id);
        Optional<Usuario> usuarioOptional = usuarioService.delete(unUsuario);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
