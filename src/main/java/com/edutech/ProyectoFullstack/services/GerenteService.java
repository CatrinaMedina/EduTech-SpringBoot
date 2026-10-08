package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import com.edutech.ProyectoFullstack.entities.Gerente;

public interface GerenteService {
    List<Gerente> findByAll();
    
    Optional<Gerente> findById(Long id);

    Gerente save (Gerente unGerente);

    Optional<Gerente> delete  (Gerente unGerente);
}
