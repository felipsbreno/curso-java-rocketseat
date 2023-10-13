package br.com.breno.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.breno.todolist.model.UserModel;
import br.com.breno.todolist.repository.IUserRepository;

@Service
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  public List<UserModel> list() {
    var users = userRepository.findAll();
    return users;
  }

  public UserModel add(UserModel userModel) {
    var passwordHash = hashPassworBeforeSave(userModel.getPassword(), 12);
    userModel.setPassword(passwordHash.toString());

    var userCreate = this.userRepository.save(userModel);
    return userCreate;
  }

  private String hashPassworBeforeSave(String passwordString, Integer cost) {
    var hashPassword = BCrypt.withDefaults().hashToString(cost, passwordString.toCharArray());
    return hashPassword;
  }
}
