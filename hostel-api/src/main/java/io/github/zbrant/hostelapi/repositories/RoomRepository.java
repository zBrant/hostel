package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.AddressModel;
import io.github.zbrant.hostelapi.models.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
  boolean existsByAddress(AddressModel address);
}

