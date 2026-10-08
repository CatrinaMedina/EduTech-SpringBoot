package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import com.edutech.ProyectoFullstack.entities.Incidencia;

public interface IncidenciaService {
    List<Incidencia> findByAll();

    Optional<Incidencia> findById(Long id);

    Incidencia save (Incidencia unIncidencia);

    Optional<Incidencia> delete  (Incidencia unIncidencia);
}
