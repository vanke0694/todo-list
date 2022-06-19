package com.example.todolist.controllers;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.todolist.repos.TutorialRepository;
import com.example.todolist.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.todolist.model.Tutorial;


@RestController
@RequestMapping("/tutorial")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;
    @Autowired
    TutorialRepository tutorialRepository;

    //API get all data
    @GetMapping("/tutorials")
    public ResponseEntity<Map<String, Object>> getAllTutorials(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            Pageable paging = PageRequest.of(page, size);

            Page<Tutorial> pageTuts;
            if (title == null)
                pageTuts = tutorialRepository.findAll(paging);
            else
                pageTuts = tutorialRepository.findByTitleContaining(title, paging);
            tutorials = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Get data by ID
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        Optional<Tutorial> tutorialData = tutorialService.findById(id);
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //API Create Data
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialService.createData(tutorial);
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Update
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@RequestBody Tutorial tutorial, @PathVariable("id") Long id) {
        Optional<Tutorial> tutorialData = tutorialService.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialService.saveMethod(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //API Delete by ID
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
        try {
            tutorialService.deleteId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Delete all
    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Get data by publish
    @GetMapping("/tutorials/published")
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            Pageable paging = PageRequest.of(page, size);

            Page<Tutorial> pageTuts = tutorialRepository.findByPublished(true, paging);
            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}