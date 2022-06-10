package com.example.todolist.repos;

import com.example.todolist.model.Todo;
import com.example.todolist.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByTitleContaining(String title);
}
