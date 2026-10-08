package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.entities.Incidencia;
import com.edutech.ProyectoFullstack.repositories.IncidenciaRepository;

@Controller
public class IncidenciaController {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @GetMapping("/incidencias")
    public String incidencias (Model model){
        List<Incidencia> incidencias = (List<Incidencia>)incidenciaRepository.findAll();
        model.addAttribute("incidencias", incidencias);
        return "incidencia";
    }


}
