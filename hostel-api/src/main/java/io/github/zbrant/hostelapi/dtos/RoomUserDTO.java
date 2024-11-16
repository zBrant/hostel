package io.github.zbrant.hostelapi.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@Builder
public class RoomUserDTO {

  private Long id;

  @NotNull
  private Long userId;

  @NotNull
  private Long roomId;
}

