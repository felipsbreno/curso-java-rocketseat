package br.com.breno.todolist.utils;

import org.springframework.http.ResponseEntity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class ExceptionUtils {
  public ResponseEntity<?> getException(Exception ex) {
    ex.printStackTrace();
    log.error(ex.getMessage());
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  public byte[] getExceptionByte(Exception ex) {
    ex.printStackTrace();
    log.error(ex.getMessage());
    return ex.getMessage().getBytes();
  }
}
