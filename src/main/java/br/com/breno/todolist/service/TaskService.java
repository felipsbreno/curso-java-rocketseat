package br.com.breno.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.breno.todolist.model.TaskModel;
import br.com.breno.todolist.repository.ITaskRepository;

@Service
public class TaskService {

  @Autowired
  ITaskRepository taskRepository;

  public List<TaskModel> list() {
    var tasks = taskRepository.findAll();
    return tasks;
  }

  public TaskModel create(TaskModel taskModel) {
    var task = taskRepository.save(taskModel);
    return task;
  }

}
