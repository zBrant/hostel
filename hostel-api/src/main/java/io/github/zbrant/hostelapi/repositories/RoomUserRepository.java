package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.RoomUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserRepository extends JpaRepository<RoomUserModel, Long> {
}

