package com.springboot.challenge.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.challenge.dtos.AddressOutputDto;
import com.springboot.challenge.exceptions.AddressNotFoundException;
import com.springboot.challenge.exceptions.AddressServiceException;
import com.springboot.challenge.models.AddressModel;
import com.springboot.challenge.repositories.AddressRepository;
import com.springboot.challenge.services.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

  @Value("${viacep.url}")
  private String baseUrl;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  ObjectMapper mapper;

  @Override
  public AddressOutputDto getAddressFromViaCep(String cep) throws AddressServiceException {
    try {
      ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(baseUrl, JsonNode.class, cep);
      JsonNode response = responseEntity.getBody();

      if (response.has("erro")) {
        throw new AddressNotFoundException(cep);
      }

      return mapper.convertValue(response, AddressOutputDto.class);
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      throw new AddressServiceException("Error accessing the address service.");
    } catch (HttpMessageConversionException e) {
      throw new AddressServiceException("Error converting address service response.");
    } catch (RestClientException e) {
      throw new AddressServiceException("Communication error with the address service.");
    }
  }
  @Override
  public AddressModel save(String cep) throws AddressServiceException {
    AddressOutputDto addressOutputDto = getAddressFromViaCep(cep);
    var addressModel = new AddressModel();
    BeanUtils.copyProperties(addressOutputDto, addressModel);
    return addressRepository.save(addressModel);
  }

  @Override
  public AddressModel getAddressFromDB(String cep) {
    return addressRepository.findById(cep).orElse(null);
  }

  @Override
  public List<AddressModel> getAddressFromCity(String city) {
    return addressRepository.findByLocalidade(city);
  }
}
