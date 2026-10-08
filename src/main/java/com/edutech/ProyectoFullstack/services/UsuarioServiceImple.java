package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Usuario;
import com.edutech.ProyectoFullstack.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImple implements UsuarioService{


    @Autowired
    private UsuarioRepository usuariorepository;

    @Override
    @Transactional
    public Optional<Usuario> delete (Usuario unUsuario){
        Optional<Usuario> usuarioOptional = usuariorepository.findById(unUsuario.getId());
        usuarioOptional.ifPresent(usuarioDb ->{   
            usuariorepository.delete(unUsuario);
        });
        return usuarioOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() {
        return (List<Usuario>) usuariorepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return  usuariorepository.findById(id);              
    }

    @Override
    @Transactional
    public Usuario save(Usuario unUsuario) {
        return usuariorepository.save(unUsuario);
    }

}
