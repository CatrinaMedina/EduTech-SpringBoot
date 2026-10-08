package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Discount;
import com.edutech.ProyectoFullstack.repositories.DiscountRepository;

@Service
public class DiscountServiceImple implements DiscountService {
    @Autowired
    private DiscountRepository discountrepository;
    @Override
    @Transactional
    public Optional<Discount> delete (Discount unDiscount){
        Optional<Discount> discountOptional = discountrepository.findById(unDiscount.getId());
        discountOptional.ifPresent(discountDb ->{   
            discountrepository.delete(unDiscount);
        });
        return discountOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Discount> findByAll(){
        return (List<Discount>) discountrepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Discount> findById(Long id) {
        return  discountrepository.findById(id);              
    }

    @Override
    @Transactional
    public Discount save(Discount unDiscount) {
        return discountrepository.save(unDiscount);
    }

}
