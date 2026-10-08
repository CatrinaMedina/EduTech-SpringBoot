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


import com.edutech.ProyectoFullstack.entities.Discount;
import com.edutech.ProyectoFullstack.services.DiscountServiceImple;
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
public class DiscountRestControllersTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DiscountServiceImple discountServiceImple;

    private List<Discount> listaDiscounts;

    @Test
    public void verDiscountsTest() throws Exception{
        when(discountServiceImple.findByAll()).thenReturn(listaDiscounts);
        mockMvc.perform(get("/api/cursos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());        
    }

    @Test
    public void verUnDiscountTest(){
        Discount unDiscount = new Discount(1L, "Descuento10",10,"01/01/2023","31/12/2023");
        try{
            when(discountServiceImple.findById(1L)).thenReturn(Optional.of(unDiscount));
            mockMvc.perform(get("/api/discounts/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch (Exception ex){
           fail("El testing ha lanzado un error " + ex.getMessage());
        }
    }

    @Test
    public void discountNoExiste() throws Exception{
        when (discountServiceImple.findById(10L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/discounts/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    public void crearDiscount() throws Exception{
        Discount unDiscount = new Discount (null, "Descuento22", 30, "01/03/2024","20/11/2024");
        Discount otroDiscount = new Discount (5L, "Descuento1", 25, "18/01/2025","18/02/2025");
        when(discountServiceImple.save(any(Discount.class))).thenReturn(otroDiscount);
        mockMvc.perform(post("/api/discounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unDiscount)))
            .andExpect(status().isCreated());
    }
    
    @Test
    public void modificarDiscountTest() throws Exception {
        Discount discountExistente = new Discount(1L, "Descuento50", 60, "01/01/2023", "31/12/2023");
        Discount discountModificado = new Discount(1L, "Descuento15", 15, "01/01/2023", "31/12/2024");
        when(discountServiceImple.findById(1L)).thenReturn(Optional.of(discountExistente));
        when(discountServiceImple.save(any(Discount.class))).thenReturn(discountModificado);
        mockMvc.perform(put("/api/discounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(discountModificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarDiscountTest() throws Exception {
        Discount discountAEliminar = new Discount(1L, "Descuento10", 10, "01/01/2023", "31/12/2023");
        when(discountServiceImple.findById(1L)).thenReturn(Optional.of(discountAEliminar));
        when(discountServiceImple.delete(any(Discount.class))).thenReturn(Optional.of(discountAEliminar));
        mockMvc.perform(delete("/api/discounts/1"))
                .andExpect(status().isOk());
    }
}

