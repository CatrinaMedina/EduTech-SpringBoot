package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.repositories.UsuarioRepository;
import com.edutech.ProyectoFullstack.entities.Usuario;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping ("/usuarios")
    public String usuarios (Model model){
        List<Usuario> usuarios = (List<Usuario>)usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario";
    }

}
