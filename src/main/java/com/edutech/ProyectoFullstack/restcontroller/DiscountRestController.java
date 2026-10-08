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


import com.edutech.ProyectoFullstack.entities.Discount;
import com.edutech.ProyectoFullstack.services.DiscountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Discounts", description="Métodos asociados a discounts")
@RestController
@RequestMapping("api/discounts")
public class DiscountRestController {
    @Autowired
    private DiscountService discountService;  

    @Operation(summary = "Obtener lista de discounts", description = "Devuelve todos los discounts disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de discounts retornada correctamente",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Discount.class)))
    @GetMapping
    public List<Discount> mostrarDiscounts(){
        return discountService.findByAll();
    }
    @Operation(summary = "Obtener discount por ID", description = "Obtiene el detalle de un discount específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Discount encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class))),
        @ApiResponse(responseCode = "404", description = "Discount no encontrado")
    })
    @GetMapping ("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Discount> discountOptional = discountService.findById(id);
        if (discountOptional.isPresent()) {
            return ResponseEntity.ok(discountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Crear un nuevo discount", description = "Crea un discount con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Discount creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class)))
    @PostMapping
    public ResponseEntity<Discount> crear (@RequestBody Discount unDiscount){
        return ResponseEntity.status(HttpStatus.CREATED).body(discountService.save(unDiscount));
    }
    @Operation(summary = "Modificar un discount", description = "Modifica un discount con los datos proporcionados")
    @ApiResponse(responseCode = "200", description = "Discount modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarDiscount(@PathVariable Long id, @RequestBody Discount unDiscount){
        Optional<Discount> discountOptional = discountService.findById(id);
        if (discountOptional.isPresent()){
            Discount discountExiste = discountOptional.get();
            discountExiste.setCodigo(unDiscount.getCodigo());
            discountExiste.setPorcentaje(unDiscount.getPorcentaje());
            discountExiste.setFechainicio(unDiscount.getFechainicio());
            discountExiste.setFechafin(unDiscount.getFechafin());
            Discount discountModificado = discountService.save(discountExiste);
            return ResponseEntity.ok(discountModificado);
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Eliminar un discount", description = "Elimina un discount por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Discount eliminado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Discount.class))),
        @ApiResponse(responseCode = "404", description = "Discount no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDiscount(@PathVariable Long id){
        Discount unDiscount = new Discount();
        unDiscount.setId(id);
        Optional<Discount> discountOptional = discountService.delete(unDiscount);

        if (discountOptional.isPresent()) {
            return ResponseEntity.ok(discountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }  



}
