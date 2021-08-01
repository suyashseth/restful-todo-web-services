package com.rest.webservices.restfultodowebservices.controller;

import com.rest.webservices.restfultodowebservices.bean.Todo;
import com.rest.webservices.restfultodowebservices.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins ="http://localhost:4200")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{username}")
    public List<Todo> getAllTodos(@PathVariable String username){
        return todoService.findAll();
    }

    @GetMapping("/users/{username}/{id}")
    public Todo getAllTodos(@PathVariable String username, @PathVariable long id){
        return todoService.findById(id);
    }

    @PutMapping("/users/{username}/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        Todo todo1 =  todoService.save(todo);
        return  new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}")
    public ResponseEntity<Void> saveTodo(@RequestBody Todo todo){
        Todo todo1 =  todoService.save(todo);

        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(todo1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{username}/{id}")
    public ResponseEntity deleteById(@PathVariable String username,@PathVariable long id){
        Todo todo = todoService.remove(id);
        if (todo !=null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
