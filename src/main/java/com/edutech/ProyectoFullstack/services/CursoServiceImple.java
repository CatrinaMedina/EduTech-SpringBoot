package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Curso;
import com.edutech.ProyectoFullstack.repositories.CursoRepository;

@Service
public class CursoServiceImple implements CursoService{

    @Autowired
    private CursoRepository cursorepository;
    @Override
    @Transactional
    public Optional<Curso> delete (Curso unCurso){
        Optional<Curso> cursoOptional = cursorepository.findById(unCurso.getId());
        cursoOptional.ifPresent(cursoDb ->{   
            cursorepository.delete(unCurso);
        });
        return cursoOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findByAll(){
        return (List<Curso>) cursorepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return  cursorepository.findById(id);              
    }

    @Override
    @Transactional
    public Curso save(Curso unCurso) {
        return cursorepository.save(unCurso);
    }
}
