package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

  Optional<UserModel> findByEmailAndPassword(String email, String password);

}

