package com.springboot.challenge.controllers;

import com.springboot.challenge.dtos.AddressInputDto;
import com.springboot.challenge.dtos.AddressOutputDto;
import com.springboot.challenge.models.AddressModel;
import com.springboot.challenge.services.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@Validated
public class AddressController {

  @Autowired
  AddressService addressService;

  @GetMapping("/address/{cep}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<AddressOutputDto> getAddress(@PathVariable(value = "cep") @NotBlank(message = "cep is mandatory")
                                                     @Pattern(regexp = "^\\d{8}$", message = "must have 8 numeric digits")
                                                       String cep) {
    AddressOutputDto addressOutputDto = addressService.getAddressFromViaCep(cep);
    return ResponseEntity.ok(addressOutputDto);
  }

  @PostMapping("/address")
  public ResponseEntity<Object> saveProduct(@RequestBody @Valid AddressInputDto addressInputDto) {
    try {
      AddressModel addressModel = addressService.save(addressInputDto.getCep());
      return ResponseEntity.status(HttpStatus.CREATED).body(addressModel);
    } catch (Exception e) {
      log.error("save address fails", e);
      return ResponseEntity.badRequest().body("save address fails");
    }
  }

  @GetMapping("/address/db/{cep}")
  public ResponseEntity<Object> getAddressFromDB(@PathVariable(value = "cep") String cep) {
    try {
      AddressModel addressModel = addressService.getAddressFromDB(cep);
      if (Objects.isNull(addressModel)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("address not found.");
      }

      return ResponseEntity.ok(addressModel);
    } catch (Exception e) {
      log.error("search address fails", e);
      return ResponseEntity.badRequest().body("search address fails");
    }
  }

  @GetMapping("/address/db/city/{city}")
  public ResponseEntity<Object> getAddressFromCity(@PathVariable(value = "city") String city) {
    try {
      List<AddressModel> addressModelList = addressService.getAddressFromCity(city);
      if (Objects.isNull(addressModelList) || addressModelList.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("address not found.");
      }

      return ResponseEntity.ok(addressModelList);
    } catch (Exception e) {
      log.error("search address fails", e);
      return ResponseEntity.badRequest().body("search address fails");
    }
  }
}
