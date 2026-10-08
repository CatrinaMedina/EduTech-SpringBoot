package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Incidencia;
import com.edutech.ProyectoFullstack.repositories.IncidenciaRepository;

@Service
public class IncidenciaServiceImple implements IncidenciaService{

    @Autowired
    private IncidenciaRepository incidenciarepository;

    @Override
    @Transactional
    public Optional<Incidencia> delete (Incidencia unIncidencia){
        Optional<Incidencia> incidenciaOptional = incidenciarepository.findById(unIncidencia.getId());
        incidenciaOptional.ifPresent(incidenciaDb ->{   
            incidenciarepository.delete(unIncidencia);
        });
        return incidenciaOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incidencia> findByAll(){
        return (List<Incidencia>) incidenciarepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Incidencia> findById(Long id) {
        return  incidenciarepository.findById(id);              
    }

    @Override
    @Transactional
    public Incidencia save(Incidencia unIncidencia) {
        return incidenciarepository.save(unIncidencia);
    }


}
