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


import com.edutech.ProyectoFullstack.entities.Incidencia;
import com.edutech.ProyectoFullstack.services.IncidenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Incidencias", description = "Métodos asociados a incidencias")
@RestController
@RequestMapping ("api/incidencias")
public class IncidenciaRestController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Operation(summary = "Obtener lista de incidencias", description = "Devuelve todos las incidencias disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de incidencias retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Incidencia.class)))
    @GetMapping
    private List<Incidencia> mostrarIncidencias(){
        return incidenciaService.findByAll();
    }
    @Operation(summary = "Obtener incidencia por ID", description = "Obtiene el detalle de una incidencia específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia encontrada",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class))),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<?> verDetalle (@PathVariable Long id){
        Optional<Incidencia> incidenciaOptional = incidenciaService.findById(id);
        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Crear una nueva incidencia", description = "Crea una incidencia con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Incidencia creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))
    @PostMapping
    public ResponseEntity<Incidencia> crear (@RequestBody Incidencia unIncidencia){
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaService.save(unIncidencia));
    }
    @Operation(summary = "Modificar una incidencia", description = "Modifica una incidencia con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Incidencia modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class)))    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarTicket(@PathVariable Long id, @RequestBody Incidencia unIncidencia){
        Optional<Incidencia> incidenciaOptional = incidenciaService.findById(id);
        if (incidenciaOptional.isPresent()){
            Incidencia incidenciaExiste = incidenciaOptional.get();
            incidenciaExiste.setDescripcion(unIncidencia.getDescripcion());
            incidenciaExiste.setEstado(unIncidencia.getEstado());
            incidenciaExiste.setFecha(unIncidencia.getFecha());
            Incidencia incidenciaModificado = incidenciaService.save(incidenciaExiste);
            return ResponseEntity.ok(incidenciaModificado);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Eliminar una incidencia", description = "Elimina una incidencia por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia eliminada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Incidencia.class))),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIncidencia(@PathVariable Long id){
        Incidencia unIncidencia = new Incidencia();
        unIncidencia.setId(id);
        Optional<Incidencia> incidenciaOptional = incidenciaService.delete(unIncidencia);

        if (incidenciaOptional.isPresent()) {
            return ResponseEntity.ok(incidenciaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }




}
