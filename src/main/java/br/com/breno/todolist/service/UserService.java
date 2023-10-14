package br.com.breno.todolist.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.breno.todolist.model.UserModel;
import br.com.breno.todolist.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

  @Autowired
  private IUserRepository userRepository;

  public List<UserModel> list() {
    var users = userRepository.findAll();
    return users;
  }

  public UserModel create(UserModel userModel) throws NoSuchAlgorithmException {
    SecretKey secretKey = generatePrivateKey();
    byte[] hashPassword = ecryptedPasswordBeforeSave(secretKey, userModel.getPassword());
    userModel.setPassword(hashPassword.toString());

    var userCreate = this.userRepository.save(userModel);
    return userCreate;
  }

  private byte[] ecryptedPasswordBeforeSave(SecretKey secretKey, String password) {
    try {
      Integer mode = Cipher.ENCRYPT_MODE;
      Cipher cipher;
      cipher = Cipher.getInstance("AES");
      cipher.init(mode, secretKey);
      return cipher.doFinal(password.getBytes());
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex.getMessage());
      return ex.getMessage().getBytes();
    }
  }

  private String decryptedPassword(SecretKey secretKey, String password) {
    try {
      Integer mode = Cipher.DECRYPT_MODE;
      Cipher cipher;
      cipher = Cipher.getInstance("AES");
      cipher.init(mode, secretKey);
      return cipher.doFinal(password.getBytes()).toString();
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex.getMessage());
      return ex.getMessage();
    }
  }

  private SecretKey generatePrivateKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey;
  }
}
