package com.example.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
import com.example.todolist.model.Todo;
import com.example.todolist.services.TodoService;
import com.example.todolist.repos.TodoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    TodoService service;
    @Autowired
    TodoRepository todoRepository;


    //API get all data
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getAllTodo(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Todo> todos = new ArrayList<Todo>();
            Pageable paging = PageRequest.of(page, size);

            Page<Todo> pageTuts;
            if (title == null)
                pageTuts = todoRepository.findAll(paging);
            else
                pageTuts = todoRepository.findByTitleContaining(title, paging);
            todos = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("todos", todos);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Get data by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
        Optional<Todo> todoData = service.findById(id);
        if (todoData.isPresent()) {
            return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //API Create
    @PostMapping("/create")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        try {
            Todo _todo = service.createData(todo);
            return new ResponseEntity<>(_todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //API Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable("id") Long id) {
        Optional<Todo> todoData = service.findById(id);

        if (todoData.isPresent()) {
            Todo _todo = todoData.get();
            _todo.setTitle(todo.getTitle());
            _todo.setDescription(todo.getDescription());
            _todo.setProgress(todo.getProgress());
            //_todo.setTodoDate(todo.getTodoDate());
            //_todo.setLastModifiedDate();
            //_todo.setLastModifiedDate(todo.getLastModifiedDate());

            return new ResponseEntity<>(service.saveMethod(_todo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //API Delete by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") Long id) {
        try {
            service.deleteId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //API Delete all
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllTodo() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
