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

import com.edutech.ProyectoFullstack.entities.Discount;
import com.edutech.ProyectoFullstack.repositories.DiscountRepository;

@SpringBootTest
public class DiscountServicesTest {
    @Mock   
    private DiscountRepository discountRepository;

    @InjectMocks
    private DiscountServiceImple discountServiceImple;

    private Discount discount;  

    @BeforeEach
    private void Inicio() { 
        discount = new Discount(1L, "Descuento10", 10, "01/01/2023", "31/12/2023");     
    }
    @Test           
    public void findByAllTest() {
        List<Discount> lista = Arrays.asList(discount);
        when(discountRepository.findAll()).thenReturn(lista);
        List<Discount> resultado = discountServiceImple.findByAll();
        assertEquals(1, resultado.size());        
        verify(discountRepository).findAll();
    }
    @Test
    public void findByIdTest() { 
        when(discountRepository.findById(1L)).thenReturn(java.util.Optional.of(discount));
        Discount resultado = discountServiceImple.findById(1L).orElse(null);
        assertEquals("Descuento10", resultado.getCodigo());
        verify(discountRepository).findById(1L);
    }
    @Test
    public void saveTest() { 
        when(discountRepository.save(any(Discount.class))).thenReturn(discount);
        Discount discountTest = discountServiceImple.save(discount);
        assertNotNull(discountTest);
        assertEquals("Descuento10", discountTest.getCodigo());
        verify(discountRepository).save(discount);
    }

    @Test
    public void deleteTest() {
        when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));
        Optional <Discount> resultado = discountServiceImple.delete(discount);
        assertTrue(resultado.isPresent());
        assertEquals("Descuento10", resultado.get().getCodigo());
        verify(discountRepository).delete(discount);
    }
}
