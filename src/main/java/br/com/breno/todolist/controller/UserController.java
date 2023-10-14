package br.com.breno.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.breno.todolist.model.UserModel;
import br.com.breno.todolist.repository.IUserRepository;
import br.com.breno.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<?> list() {
    var userList = userService.list();
    return ResponseEntity.ok().body(userList);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody UserModel userModel) {
    try {
      var username = this.userRepository.findByUsername(userModel.getUsername());

      if (username != null) {
        return ResponseEntity.badRequest().body("Usuário com esse username já está em uso!");
      }

      var userCreate = userService.create(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);

    } catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex.getMessage());
      return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }
}
