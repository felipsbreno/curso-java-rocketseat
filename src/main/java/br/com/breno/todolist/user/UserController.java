package br.com.breno.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @GetMapping
  public List<UserModel> list() {
    return this.userRepository.findAll();
  }

  @PostMapping
  public UserModel create(@RequestBody UserModel userModel) {
    var userCreate = this.userRepository.save(userModel);
    return userCreate;
  }
}
