package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.entities.Discount;
import com.edutech.ProyectoFullstack.repositories.DiscountRepository;

@Controller
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping("/discounts")
    public String discounts (Model model){
        List<Discount> discounts = (List<Discount>)discountRepository.findAll();
        model.addAttribute("discounts", discounts);
        return "discount";
    }
}
