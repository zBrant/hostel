package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.AddressModel;
import io.github.zbrant.hostelapi.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;

  public AddressModel save(AddressModel address) {
    return addressRepository.save(address);
  }
}

