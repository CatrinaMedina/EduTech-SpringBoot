package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import com.edutech.ProyectoFullstack.entities.Discount;

public interface DiscountService {
    List<Discount> findByAll();
    
    Optional<Discount> findById(Long id);

    Discount save (Discount unDiscount);

    Optional<Discount> delete  (Discount unDiscount);
}
