package com.springboot.challenge.services;

import com.springboot.challenge.dtos.AddressOutputDto;
import com.springboot.challenge.exceptions.AddressServiceException;
import com.springboot.challenge.models.AddressModel;

import java.util.List;

public interface AddressService {
  public AddressOutputDto getAddressFromViaCep(String cep) throws AddressServiceException;

  AddressModel save(String cep) throws AddressServiceException;

  AddressModel getAddressFromDB(String cep);

  List<AddressModel> getAddressFromCity(String city);
}
