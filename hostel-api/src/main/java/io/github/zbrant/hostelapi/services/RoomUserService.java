package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.RoomUserModel;
import io.github.zbrant.hostelapi.repositories.RoomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomUserService {

  private final RoomUserRepository roomUserRepository;

  public RoomUserModel save(RoomUserModel roomUser) {
    return roomUserRepository.save(roomUser);
  }
}
