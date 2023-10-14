package br.com.breno.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.breno.todolist.model.TaskModel;
import br.com.breno.todolist.service.TaskService;
import br.com.breno.todolist.utils.ExceptionUtils;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public ResponseEntity<?> list() {
    try {
      var tasks = taskService.list();
      return ResponseEntity.ok().body(tasks);
    } catch (Exception ex) {
      return ExceptionUtils.getException(ex);
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody TaskModel taskModel) {
    try {
      var task = taskService.create(taskModel);
      return ResponseEntity.created(null).body(task);
    } catch (Exception ex) {
      return ExceptionUtils.getException(ex);
    }
  }
}
