package com.example.api.h2.jpa.controller;

import com.example.api.h2.jpa.model.Todo;
import com.example.api.h2.jpa.services.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@Api(value ="API REST To-do")
@CrossOrigin(origins="*")
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    //GET
    @GetMapping
    @ApiOperation(value="Retorna lista de afazeres")
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/{todoId}"})
    @ApiOperation(value="Retorna um afazer único")
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    //POST
    @PostMapping
    @ApiOperation(value="Cria afazeres")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        Todo todo1 = todoService.insert(todo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("todo", "/api/v1/todo/" + todo1.getId().toString());
        return new ResponseEntity<>(todo1, httpHeaders, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping({"/{todoId}"})
    @ApiOperation(value="Atualiza um afazer")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo todo){
        todoService.updateTodo(todoId, todo);
        return new ResponseEntity<>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping({"/{todoId}"})
    @ApiOperation(value="Deleta um afazer")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("todoId") Long todoId) {
        todoService.deleteTodo((todoId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
