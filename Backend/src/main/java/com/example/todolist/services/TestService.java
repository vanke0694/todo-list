package com.example.todolist.services;

import com.example.todolist.model.Test;
import com.example.todolist.model.Tutorial;
import com.example.todolist.repos.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public Test create(Test input) {
        input.setId(null);
        return testRepository.save(input);
    }
}






