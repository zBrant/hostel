package io.github.zbrant.hostelapi.controllers;

import io.github.zbrant.hostelapi.dtos.RoomDTO;
import io.github.zbrant.hostelapi.models.RoomModel;
import io.github.zbrant.hostelapi.models.RoomRepresentationModelAssembler;
import io.github.zbrant.hostelapi.services.HostelApiFacade;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/rooms")
@AllArgsConstructor
public class RoomController {

  private final HostelApiFacade facadeService;
  private final RoomRepresentationModelAssembler roomModelAssembler;
  private final PagedResourcesAssembler pagedResourcesAssembler;

  @PostMapping
  public ResponseEntity<EntityModel<RoomModel>> saveRoom(@RequestBody @Valid RoomDTO roomDto) {
    RoomModel room = new RoomModel();
    BeanUtils.copyProperties(roomDto, room);
    EntityModel<RoomModel> roomModel = roomModelAssembler.toModel(facadeService.saveRoomWithOwner(room, roomDto.getUserId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(roomModel);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<RoomModel>> updateRoom(@PathVariable Long id, @RequestBody @Valid RoomDTO roomDto) {
    RoomModel room = facadeService.findRoomById(id);

    var roomModel = new RoomModel();
    BeanUtils.copyProperties(roomDto, roomModel);
    roomModel.setId(room.getId());

    EntityModel<RoomModel> roomUpdated = roomModelAssembler.toModel(facadeService.updateRoom(roomModel));
    return ResponseEntity.ok(roomUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
    RoomModel room = facadeService.findRoomById(id);
    facadeService.deleteRoom(room);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<RoomModel>> getRoomById(@PathVariable Long id) {
    EntityModel<RoomModel> roomModel = roomModelAssembler.toModel(facadeService.findRoomById(id));
    return ResponseEntity.ok(roomModel);
  }

  @GetMapping
  public ResponseEntity<PagedModel<EntityModel<RoomModel>>> getAllRooms(
      @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String city) {

    Page<RoomModel> rooms = facadeService.findAllRoomsByCountryAndCity(pageable, country, city);
    PagedModel<EntityModel<RoomModel>> roomModels = pagedResourcesAssembler.toModel(rooms, roomModelAssembler);
    return ResponseEntity.ok(roomModels);
  }

  @Transactional
  @PostMapping("/{id}/photo")
  public ResponseEntity<String> uploadRoomPhoto(@PathVariable("id") Long roomId,
                                                @RequestParam("photo") MultipartFile photo) {
    try {
      RoomModel room = facadeService.findRoomById(roomId);
      if (Objects.isNull(room)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found.");

      String photoPath = facadeService.saveRoomPhoto(photo);

      room.setPhotoPath(photoPath);
      facadeService.updateRoom(room);

      return ResponseEntity.status(HttpStatus.OK).body("Photo uploaded successfully.");
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the photo.");
    }
  }

  @GetMapping("/photo/{roomId}")
  public ResponseEntity<Resource> getPhoto(@PathVariable Long roomId) {
      Resource resource = facadeService.getRoomPhoto(roomId);
      return ResponseEntity.ok()
          .contentType(MediaType.IMAGE_JPEG)
          .body(resource);
  }

}
