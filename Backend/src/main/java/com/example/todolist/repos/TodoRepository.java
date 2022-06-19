package com.example.todolist.repos;

import com.example.todolist.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findByTitleContaining(String title, Pageable pageable);
}
