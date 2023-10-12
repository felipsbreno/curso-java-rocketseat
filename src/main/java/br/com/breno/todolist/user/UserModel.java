package br.com.breno.todolist.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {
  public String username;
  public String name;
  public String email;
}
