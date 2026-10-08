package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.entities.Gerente;
import com.edutech.ProyectoFullstack.repositories.GerenteRepository;

@Controller
public class GerenteController {
    @Autowired
    private GerenteRepository gerenteRepository;

    @GetMapping ("/gerentes")
        public String gerentes (Model model){
        List<Gerente> gerentes = (List<Gerente>)gerenteRepository.findAll();
        model.addAttribute("gerentes", gerentes);
        return "gerente";
    }


}
