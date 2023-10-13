package br.com.breno.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.breno.todolist.model.TaskModel;
import br.com.breno.todolist.repository.ITaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository iTaskRepository;

  @GetMapping
  public ResponseEntity<?> list() {
    var tasks = this.iTaskRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody TaskModel taskModel) {
    var task = this.iTaskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }

}
