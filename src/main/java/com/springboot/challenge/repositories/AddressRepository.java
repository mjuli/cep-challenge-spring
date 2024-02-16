package com.springboot.challenge.repositories;

import com.springboot.challenge.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, String> {
  List<AddressModel> findByLocalidade(String city);
}
