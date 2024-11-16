package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.AddressModel;
import io.github.zbrant.hostelapi.models.RoomModel;
import io.github.zbrant.hostelapi.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;

  public RoomModel save(RoomModel room) {
    if (roomRepository.existsByAddress(room.getAddress())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um quarto registrado neste endereço.");
    }

    return roomRepository.save(room);
  }

  public Page<RoomModel> findAll(Pageable pageable, String country, String city) {
    return roomRepository.findAll(pageable);
  }

  public RoomModel findById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));
  }

  public void delete(RoomModel room) {
    roomRepository.delete(room);
  }
}
