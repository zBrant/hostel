package io.github.zbrant.hostelapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDTO {

  @NotBlank
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 100)
  private String password;
}
