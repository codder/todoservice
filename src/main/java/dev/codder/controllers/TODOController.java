package dev.codder.controllers;

import dev.codder.models.TODOModel;
import dev.codder.services.TODOStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Daniil on 2/25/2016.
 */

@RestController
@RequestMapping("/api")
public class TODOController {

    @Autowired
    TODOStorageService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Void> testConnect() {
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ResponseEntity<List<TODOModel>> listAllTasks() {
        List<TODOModel> tasks = todoService.getAll();
        if(tasks.isEmpty()){
            return new ResponseEntity<List<TODOModel>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TODOModel>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TODOModel> getTask(@PathVariable("id") long id) {
        System.out.println("Fetching Task with id " + id);
        TODOModel task = todoService.getById(id);
        if (task == null) {
            System.out.println("Task with id " + id + " not found");
            return new ResponseEntity<TODOModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TODOModel>(task, HttpStatus.OK);
    }



    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public ResponseEntity<TODOModel> createTask(@RequestBody TODOModel todo, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Task " + todo.getTitle());

        TODOModel task = todoService.save(todo);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/todo/{id}").buildAndExpand(todo.getId()).toUri());
        return new ResponseEntity<TODOModel>(task, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TODOModel> updateTask(@PathVariable("id") long id, @RequestBody TODOModel task) {
        System.out.println("Updating Task " + id);

        TODOModel currentTask = todoService.getById(id);

        if (currentTask==null) {
            System.out.println("TODOModel with id " + id + " not found");
            return new ResponseEntity<TODOModel>(HttpStatus.NOT_FOUND);
        }
        task.setId(id);
        todoService.update(task);

        return new ResponseEntity<TODOModel>(currentTask, HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TODOModel> deleteTask(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Task with id " + id);

        TODOModel task = todoService.getById(id);
        if (task == null) {
            System.out.println("Unable to delete. Task with id " + id + " not found");
            return new ResponseEntity<TODOModel>(HttpStatus.NOT_FOUND);
        }

        todoService.delete(id);
        return new ResponseEntity<TODOModel>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/todos", method = RequestMethod.DELETE)
    public ResponseEntity<TODOModel> deleteAllTasks() {
        System.out.println("Deleting Completed Tasks");

        todoService.deleteCompleted();
        return new ResponseEntity<TODOModel>(HttpStatus.NO_CONTENT);
    }
}
