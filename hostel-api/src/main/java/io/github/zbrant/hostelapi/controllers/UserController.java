package io.github.zbrant.hostelapi.controllers;

import io.github.zbrant.hostelapi.dtos.LoginDTO;
import io.github.zbrant.hostelapi.dtos.UserDTO;
import io.github.zbrant.hostelapi.models.UserModel;
import io.github.zbrant.hostelapi.models.UserRepresentationModelAssembler;
import io.github.zbrant.hostelapi.services.HostelApiFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

  private final HostelApiFacade facadeService;
  private final UserRepresentationModelAssembler userModelAssembler;

  @PostMapping("/register")
  public ResponseEntity<EntityModel<UserModel>> registerUser(@RequestBody @Valid UserDTO userDto) {
    UserModel user = new UserModel();
    BeanUtils.copyProperties(userDto, user);
    EntityModel<UserModel> userModel = userModelAssembler.toModel(facadeService.saveUser(user));
    return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
  }

  @PostMapping("/login")
  public ResponseEntity<EntityModel<UserModel>> loginUser(@RequestBody @Valid LoginDTO loginDto) {
    UserModel user = facadeService.findUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
    EntityModel<UserModel> userModel = userModelAssembler.toModel(user);
    return ResponseEntity.status(HttpStatus.FOUND).body(userModel);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logoutUser() {
    return ResponseEntity.ok("Logout realizado com sucesso.");
  }

  @PutMapping("/update")
  public ResponseEntity<EntityModel<UserModel>> updateUser(@RequestBody @Valid UserDTO userDto) {
    UserModel user = facadeService.findUserById(userDto.getId());

    BeanUtils.copyProperties(userDto, user);
    EntityModel<UserModel> userModel = userModelAssembler.toModel(facadeService.saveUser(user));

    return ResponseEntity.ok(userModel);
  }
}
