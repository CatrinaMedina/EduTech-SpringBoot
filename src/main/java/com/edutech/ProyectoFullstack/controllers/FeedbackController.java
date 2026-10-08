package com.edutech.ProyectoFullstack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edutech.ProyectoFullstack.entities.Feedback;
import com.edutech.ProyectoFullstack.repositories.FeedbackRepository;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedbacks")
    public String feedbacks (Model model){
        List<Feedback> feedbacks = (List<Feedback>)feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback";
    }


}
