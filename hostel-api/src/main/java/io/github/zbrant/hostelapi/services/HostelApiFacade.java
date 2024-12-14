package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    validateUserHasAllCredentials(user);

    AddressModel address = findOrSaveAddress(room.getAddress());
    room.setAddress(address);

    RoomModel savedRoom = roomService.save(room);

    RoomUserModel roomUser = new RoomUserModel();
    roomUser.setRoom(savedRoom);
    roomUser.setUser(user);

    user.getRoomUsers().add(roomUserService.save(roomUser));
    userService.save(user);

    return savedRoom;
  }

  private void validateUserHasAllCredentials(UserModel user) {
    if (Objects.isNull(user.getCpfCnpj()) || Objects.isNull(user.getPhoneNumber())){
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Complete o cadastro para registrar um quarto.");
    }
  }

  private static void validateMaxRoomsByUser(UserModel user) {
    if (user.getRoomUsers().size() == MAXUSERROOMS)
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Numero maximo de cadastro de quartos permitido atingido.");

  }

  public RoomModel updateRoom(RoomModel room) {
    AddressModel address = getAddress(room);

    if (Objects.isNull(address.getId()))
      address = addressService.save(address);

    room.setAddress(address);

    return roomService.update(room);
  }

  private AddressModel getAddress(RoomModel room) {
    return addressService.findById(room.getAddress().getId());
  }

  public AddressModel findOrSaveAddress(AddressModel address) {
    if (Objects.isNull(address.getId())) return addressService.save(address);

    return addressService.findById(address.getId());
  }

  public String saveRoomPhoto(MultipartFile photo)  throws IOException {
    return roomService.savePhoto(photo);
  }

  public Resource getRoomPhoto(Long roomId) {
    var photoPath = roomService.findById(roomId).getPhotoPath();
    return roomService.getPhoto(photoPath);
  }

  public UserModel saveUser(UserModel user) {
    return userService.save(user);
  }

  public Page<RoomModel> findAllRoomsByCountryAndCity(Pageable pageable, String country, String city) {
    return roomService.findAllByCountryAndCity(pageable, country, city);
  }

  public RoomModel findRoomById(Long id) {
    return roomService.findById(id);
  }

  public void deleteRoom(RoomModel room) {
    var roomUser = roomUserService.findByRoom(room);
    var user = userService.findById(roomUser.getUser().getId());

    user.getRoomUsers().remove(roomUser);
    userService.save(user);

    roomService.delete(room);
  }

  public UserModel findUserByEmailAndPassword(String email, String password) {
    return userService.findByEmailAndPassword(email, password);
  }

  public UserModel findUserById(Long userId) {
    return userService.findById(userId);
  }

}

