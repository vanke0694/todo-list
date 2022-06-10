package com.example.todolist.controllers;

import com.example.todolist.model.Test;
import com.example.todolist.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @PostMapping
    public ResponseEntity create(@RequestBody Test input) {
        try {
            return new ResponseEntity(testService.create(input), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.BAD_REQUEST);
        }
    }
}
