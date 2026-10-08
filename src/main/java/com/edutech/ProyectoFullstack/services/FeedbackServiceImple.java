package com.edutech.ProyectoFullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.ProyectoFullstack.entities.Feedback;
import com.edutech.ProyectoFullstack.repositories.FeedbackRepository;

@Service
public class FeedbackServiceImple implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackrepository;
    @Override
    @Transactional
    public Optional<Feedback> delete (Feedback unFeedback){
        Optional<Feedback> feedbackOptional = feedbackrepository.findById(unFeedback.getId());
        feedbackOptional.ifPresent(feedbackDb ->{   
            feedbackrepository.delete(unFeedback);
        });
        return feedbackOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Feedback> findByAll(){
        return (List<Feedback>) feedbackrepository.findAll();       
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Feedback> findById(Long id) {
        return  feedbackrepository.findById(id);              
    }

    @Override
    @Transactional
    public Feedback save(Feedback unFeedback) {
        return feedbackrepository.save(unFeedback);
    }
}
