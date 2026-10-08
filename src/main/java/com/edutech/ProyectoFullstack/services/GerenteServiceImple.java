package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Gerente;
import com.edutech.ProyectoFullstack.repositories.GerenteRepository;

@Service
public class GerenteServiceImple implements GerenteService {
    @Autowired
    private GerenteRepository gerenterepository;
    @Override
    @Transactional
    public Optional<Gerente> delete (Gerente unGerente){
        Optional<Gerente> gerenteOptional = gerenterepository.findById(unGerente.getId());
        gerenteOptional.ifPresent(gerenteDb ->{   
            gerenterepository.delete(unGerente);
        });
        return gerenteOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gerente> findByAll(){
        return (List<Gerente>) gerenterepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Gerente> findById(Long id) {
        return  gerenterepository.findById(id);              
    }

    @Override
    @Transactional
    public Gerente save(Gerente unGerente) {
        return gerenterepository.save(unGerente);
    }    



}
