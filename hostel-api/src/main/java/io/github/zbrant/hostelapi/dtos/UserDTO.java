package io.github.zbrant.hostelapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UserDTO {

  private Long id;

  @NotBlank
  @Size(max = 100)
  private String name;

  @NotBlank
  @Size(max = 20)
  private String phoneNumber;

  @NotBlank
  @Size(max = 20)
  private String cpfCnpj;

  @NotBlank
  @Email
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 100)
  private String password;

  private List<RoomUserDTO> roomUsers;  // Lista de RoomUser associados
}

