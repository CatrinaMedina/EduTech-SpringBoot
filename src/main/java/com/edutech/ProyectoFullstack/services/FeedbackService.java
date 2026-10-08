package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import com.edutech.ProyectoFullstack.entities.Feedback;

public interface FeedbackService {
    List<Feedback> findByAll();
    
    Optional<Feedback> findById(Long id);

    Feedback save (Feedback unFeedback);

    Optional<Feedback> delete  (Feedback unFeedback);
}
