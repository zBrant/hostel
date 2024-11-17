package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.RoomModel;
import io.github.zbrant.hostelapi.models.RoomUserModel;
import io.github.zbrant.hostelapi.repositories.RoomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RoomUserService {

  private final RoomUserRepository roomUserRepository;

  public RoomUserModel save(RoomUserModel roomUser) {
    return roomUserRepository.save(roomUser);
  }

  public RoomUserModel findByRoom(RoomModel room) {
    return roomUserRepository.findByRoomId(room.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
  }
}
