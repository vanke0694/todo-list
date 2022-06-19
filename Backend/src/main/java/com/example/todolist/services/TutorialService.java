package com.example.todolist.services;

import com.example.todolist.model.Tutorial;
import com.example.todolist.repos.TutorialRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {
    @Autowired
    TutorialRepository tutorialRepository;


    public Optional<Tutorial> findById(long id) {
        return tutorialRepository.findById(id);
    }

    public void deleteId(long id){
         tutorialRepository.deleteById(id);
    }
    public void deleteAll(){
        tutorialRepository.deleteAll();
    }

    public Tutorial saveMethod(Tutorial tutorial){
        return  tutorialRepository.save(tutorial);
    }

    public Tutorial createData(Tutorial tutorial) {
        Tutorial ab = new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false);
        Tutorial _tutorial = tutorialRepository
                .save(ab);
        return  _tutorial;
    }
    public List<Tutorial> findAll(){
        return tutorialRepository.findAll();
    }

//    public List<Tutorial> findByTitleContaining(String title){
//        return tutorialRepository.findByTitleContaining(title);
//    }

//    public List<Tutorial> findByPublished(){
//        return tutorialRepository.findByPublished(true);
//    }
}


