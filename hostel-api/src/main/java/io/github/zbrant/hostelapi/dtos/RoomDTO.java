package io.github.zbrant.hostelapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RoomDTO {

  private Long id;

  @NotBlank
  @Size(max = 500)
  private String description;

  @NotBlank
  @Size(max = 20)
  private String price;

  @NotNull
  private Integer bedCount;

  @NotNull
  private Boolean rented;

  @NotNull
  private Long userId;  // Relaciona com o User
}

