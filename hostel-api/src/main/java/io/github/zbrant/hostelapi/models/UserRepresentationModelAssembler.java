package io.github.zbrant.hostelapi.models;

import io.github.zbrant.hostelapi.controllers.UserController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationModelAssembler implements SimpleRepresentationModelAssembler<UserModel> {

  @Override
  public void addLinks(EntityModel<UserModel> resource) {
    Long id = Objects.requireNonNull(resource.getContent()).getId();
    resource.add(linkTo(methodOn(UserController.class).updateUser(null)).withSelfRel());
    resource.add(linkTo(methodOn(UserController.class).logoutUser()).withRel("logout"));
  }

  @Override
  public void addLinks(CollectionModel<EntityModel<UserModel>> resources) {
    resources.add(linkTo(methodOn(UserController.class).registerUser(null)).withRel("register"));
  }
}
