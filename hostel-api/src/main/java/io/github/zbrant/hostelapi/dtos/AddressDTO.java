package io.github.zbrant.hostelapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Setter
@Getter
@Builder
public class AddressDTO {

  @NotBlank
  @Size(max = 100)
  private String city;

  @NotBlank
  @Size(max = 100)
  private String state;

  @NotBlank
  @Size(max = 20)
  private String CEP;

  @NotBlank
  @Size(max = 100)
  private String country;

  @NotNull
  private Integer number;
}
