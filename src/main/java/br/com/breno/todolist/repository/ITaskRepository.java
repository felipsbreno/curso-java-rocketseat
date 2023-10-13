package br.com.breno.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.breno.todolist.model.TaskModel;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

}
