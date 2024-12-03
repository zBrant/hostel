package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.UserModel;
import io.github.zbrant.hostelapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserModel save(UserModel user) {
    if (informationMissing(user)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informações de usuário incompletas.");
    }

    return userRepository.save(user);
  }

  private boolean informationMissing(UserModel user) {
    return Objects.isNull(user.getName()) ||
        Objects.isNull(user.getEmail()) ||
        Objects.isNull(user.getPassword());
  }

  public UserModel findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
  }

  public UserModel findByEmailAndPassword(String email, String password) {
    return userRepository.findByEmailAndPassword(email, password)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Credenciais inválidas."));
  }

}
