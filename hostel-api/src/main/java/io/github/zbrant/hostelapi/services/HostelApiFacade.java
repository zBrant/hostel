package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class HostelApiFacade {

  private final RoomService roomService;
  private final UserService userService;
  private final AddressService addressService;
  private final RoomUserService roomUserService;
  private static final Integer MAXUSERROOMS = 5;

  public RoomModel saveRoomWithOwner(RoomModel room, Long userId) {
    UserModel user = userService.findById(userId);
    validateMaxRoomsByUser(user);

    RoomModel savedRoom = roomService.save(room);

    RoomUserModel roomUser = new RoomUserModel();
    roomUser.setRoom(savedRoom);
    roomUser.setUser(user);

    user.getRoomUsers().add(roomUserService.save(roomUser));
    userService.save(user);

    return savedRoom;
  }

  private static void validateMaxRoomsByUser(UserModel user) {
    if (user.getRoomUsers().size() == MAXUSERROOMS)
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Numero maximo de cadastro de quartos permitido atingido");
  }

  public RoomModel updateRoom(RoomModel room) {
    AddressModel address = room.getAddress();

    if (Objects.isNull(address.getId())) addressService.save(address);

    return roomService.save(room);
  }

  public String saveRoomPhoto(MultipartFile photo)  throws IOException {
    return roomService.savePhoto(photo);
  }

  public Resource getRoomPhoto(String path) {
    return roomService.getPhoto(path);
  }

  public UserModel saveUser(UserModel user) {
    return userService.save(user);
  }

  public Page<RoomModel> findAllRooms(Pageable pageable, String country, String city) {
    return roomService.findAll(pageable, country, city);
  }

  public RoomModel findRoomById(Long id) {
    return roomService.findById(id);
  }

  public void deleteRoom(RoomModel room) {
    roomService.delete(room);
  }

  public UserModel findUserByEmailAndPassword(String email, String password) {
    return userService.findByEmailAndPassword(email, password);
  }

  public UserModel findUserById(Long userId) {
    return userService.findById(userId);
  }

}

