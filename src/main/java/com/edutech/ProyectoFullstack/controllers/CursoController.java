package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.entities.Curso;
import com.edutech.ProyectoFullstack.repositories.CursoRepository;

@Controller
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping ("/cursos")
    public String cursos (Model model){
        List<Curso> cursos = (List<Curso>)cursoRepository.findAll();
        model.addAttribute("cursos", cursos);
        return "curso";
    }

}
