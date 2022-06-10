package com.example.todolist.services;

import com.example.todolist.model.Todo;
import com.example.todolist.model.Tutorial;
import com.example.todolist.repos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public Todo createData(Todo todo) {
        Todo a = new Todo(todo.getTitle(), todo.getDescription(), todo.getProgress());
//        a.setTitle(todo.getTitle());
//        a.setDescription(todo.getDescription());
//        a.setProgress(todo.getProgress());

        Todo _todo= todoRepository.save(a);
        return _todo;
    }

    public Optional<Todo> findById(long id) {
        return todoRepository.findById(id);
    }
    public Todo saveMethod(Todo todo){
        return  todoRepository.save(todo);
    }

    public void deleteId(long id){
        todoRepository.deleteById(id);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }
    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public List<Todo> findByTitleContaining(String title){
        return todoRepository.findByTitleContaining(title);
    }
}

