package com.edutech.ProyectoFullstack.repositories;

import org.springframework.data.repository.CrudRepository;

import com.edutech.ProyectoFullstack.entities.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

}
