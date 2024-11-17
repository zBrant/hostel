package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.AddressModel;
import io.github.zbrant.hostelapi.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AddressService {

  private final AddressRepository addressRepository;

  public AddressModel save(AddressModel address) {
    return addressRepository.save(address);
  }

  public AddressModel findById(Long idAdress) {
    return addressRepository.findById(idAdress)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
  }
}

