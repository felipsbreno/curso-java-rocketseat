package br.com.breno.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @GetMapping
  public ResponseEntity<?> list() {
    var userList = this.userRepository.findAll();
    return ResponseEntity.ok().body(userList);
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody UserModel userModel) {
    var username = this.userRepository.findByUsername(userModel.getUsername());

    if (username != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário com esse username já está em uso!");
    }

    var passwordHash = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHash.toString());

    var userCreate = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
  }
}
