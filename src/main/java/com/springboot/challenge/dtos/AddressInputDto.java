package com.springboot.challenge.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressInputDto {
  @NotBlank
  @Pattern(regexp = "^\\d{8}$", message="cep must have eight numeric digits")
  private String cep;
}
