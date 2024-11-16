package io.github.zbrant.hostelapi.models;

import io.github.zbrant.hostelapi.controllers.RoomController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoomRepresentationModelAssembler implements SimpleRepresentationModelAssembler<RoomModel> {

  @Override
  public void addLinks(EntityModel<RoomModel> resource) {
    Long id = Objects.requireNonNull(resource.getContent()).getId();
    resource.add(linkTo(methodOn(RoomController.class).getRoomById(id)).withSelfRel());
    resource.add(linkTo(methodOn(RoomController.class).deleteRoom(id)).withRel("delete"));
  }

  @Override
  public void addLinks(CollectionModel<EntityModel<RoomModel>> resources) {
    resources.add(linkTo(methodOn(RoomController.class).getAllRooms(null, null, null)).withSelfRel());
  }
}
