package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.RoomUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUserModel, Long> {

  Optional<RoomUserModel> findByRoomId(Long roomId);
}

