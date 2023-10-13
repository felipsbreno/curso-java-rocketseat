package br.com.breno.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.breno.todolist.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByUsername(String username);
}
