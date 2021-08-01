package com.rest.webservices.restfultodowebservices.service;

import com.rest.webservices.restfultodowebservices.bean.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int idCounter = 0;

    static {
        todos.add(new Todo(++idCounter,"suyash","learn react",new Date(), false));
        todos.add(new Todo(++idCounter,"suyash","learn MicroService",new Date(), false));
        todos.add(new Todo(++idCounter,"suyash","learn AWS",new Date(), false));
    }

    public List<Todo> findAll(){
        return todos;
    }

    public Todo remove(long id){
        Todo todo = findById(id);
        if (todo == null){
            return null;
        }
        todos.remove(todo);
        return todo;
    }

    public Todo findById(long id){
        for (Todo todo : todos){
            if (todo.getId() == id){
                return todo;
            }
        }
        return null;
    }

    public Todo save(Todo todo){
        if(todo.getId() == -1 || todo.getId() == 0){
            todo.setId(++idCounter);
        }else{
            remove(todo.getId());
        }
        todos.add(todo);
        return todo;
    }
}
