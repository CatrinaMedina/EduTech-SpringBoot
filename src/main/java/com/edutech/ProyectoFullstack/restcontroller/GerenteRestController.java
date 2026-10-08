package com.edutech.ProyectoFullstack.restcontroller;

import java.util.List;
import java.util.Optional;

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


import com.edutech.ProyectoFullstack.entities.Gerente;
import com.edutech.ProyectoFullstack.services.GerenteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gerentes", description = "Métodos asociados a gerentes")
@RestController
@RequestMapping("api/gerentes")
public class GerenteRestController {
    @Autowired
    private GerenteService gerenteService;

    @Operation(summary = "Obtener lista de gerentes", description = "Devuelve todos los gerentes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de gerentes retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Gerente.class)))
    @GetMapping
    public List<Gerente> mostrarGerentes(){
        return gerenteService.findByAll();
    }
    @Operation(summary = "Obtener gerente por ID", description = "Obtiene el detalle de un gerente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Gerente> gerenteOptional = gerenteService.findById(id);
        if (gerenteOptional.isPresent()) {
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Crear un nuevo gerente", description = "Crea un gerente con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Gerente creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class)))
    @PostMapping
    public ResponseEntity<Gerente> crear (@RequestBody Gerente unGerente){
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteService.save(unGerente));
    }
    @Operation(summary = "Modificar un gerente", description = "Modifica un gerente con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Gerente modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class)))    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarGerente(@PathVariable Long id, @RequestBody Gerente unGerente){
        Optional<Gerente> gerenteOptional = gerenteService.findById(id);
        if (gerenteOptional.isPresent()){
            Gerente gerenteExiste = gerenteOptional.get();
            gerenteExiste.setNombre(unGerente.getNombre());
            gerenteExiste.setApellido(unGerente.getApellido());
            gerenteExiste.setEmail(unGerente.getEmail());
            gerenteExiste.setEspecialidad(unGerente.getEspecialidad());
            Gerente gerenteModificado = gerenteService.save(gerenteExiste);
            return ResponseEntity.ok(gerenteModificado);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Eliminar un gerente", description = "Elimina un gerente por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gerente eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Gerente.class))),
        @ApiResponse(responseCode = "404", description = "Gerente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarGerente(@PathVariable Long id){
        Gerente unGerente = new Gerente();
        unGerente.setId(id);
        Optional<Gerente> gerenteOptional = gerenteService.delete(unGerente);

        if (gerenteOptional.isPresent()) {
            return ResponseEntity.ok(gerenteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
