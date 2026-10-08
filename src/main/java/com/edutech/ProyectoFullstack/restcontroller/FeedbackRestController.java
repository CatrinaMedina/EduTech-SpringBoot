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


import com.edutech.ProyectoFullstack.entities.Feedback;
import com.edutech.ProyectoFullstack.services.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Feedbacks", description = "Métodos asociados a feedbacks")
@RestController
@RequestMapping("api/feedbacks")
public class FeedbackRestController {
    @Autowired
    private FeedbackService feedbackService;

    @Operation(summary = "Obtener lista de feedbacks", description = "Devuelve todos los feedbacks disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de feedbacks retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Feedback.class)))
    @GetMapping
    public List<Feedback> mostrarFeedbacks(){
        return feedbackService.findByAll();
    }
    @Operation(summary = "Obtener feedback por ID", description = "Obtiene el detalle de un feedback específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Feedback encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Feedback.class))),
        @ApiResponse(responseCode = "404", description = "Feedback no encontrado")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Feedback> feedbackOptional = feedbackService.findById(id);
        if (feedbackOptional.isPresent()) {
            return ResponseEntity.ok(feedbackOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Crear un nuevo feedback", description = "Crea un feedback con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Feedback creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Feedback.class)))
    @PostMapping
    public ResponseEntity<Feedback> crear (@RequestBody Feedback unFeedback){
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.save(unFeedback));
    }
    @Operation(summary = "Modificar un feedback", description = "Modifica un feedback con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Feedback modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Feedback.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarFeedback(@PathVariable Long id, @RequestBody Feedback unFeedback){
        Optional<Feedback> feedbackOptional = feedbackService.findById(id);
        if (feedbackOptional.isPresent()){
            Feedback feedbackExiste = feedbackOptional.get();
            feedbackExiste.setComentario(unFeedback.getComentario());
            feedbackExiste.setCalificacion(unFeedback.getCalificacion());
            feedbackExiste.setFecha(unFeedback.getFecha());
            Feedback feedbackModificado = feedbackService.save(feedbackExiste);
            return ResponseEntity.ok(feedbackModificado);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Eliminar un feedback", description = "Elimina un feedback por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Feedback eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Feedback.class))),
        @ApiResponse(responseCode = "404", description = "Feedback no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFeedback(@PathVariable Long id){
        Feedback unFeedback = new Feedback();
        unFeedback.setId(id);
        Optional<Feedback> feedbackOptional = feedbackService.delete(unFeedback);

        if (feedbackOptional.isPresent()) {
            return ResponseEntity.ok(feedbackOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
