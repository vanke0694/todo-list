package com.example.todolist.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TodoList")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "create_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "todo_date")
    private Date todoDate;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private Integer progress;

    public Todo() {
    }
    //constructor
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }
    //constructor
    public Todo(String title, String description, Integer progress) {
        this.createDate = new Date();
        this.title = title;
        this.description=description;
        this.progress=progress;
        //this.todoDate=todoDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }



}
