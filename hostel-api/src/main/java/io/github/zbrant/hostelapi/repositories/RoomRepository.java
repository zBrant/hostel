package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.AddressModel;
import io.github.zbrant.hostelapi.models.RoomModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {
  boolean existsByAddress(AddressModel address);

  @Query("SELECT r FROM RoomModel r " +
      "JOIN r.address ad " +
      "WHERE (COALESCE(:country, '') = '' OR lower(ad.country) LIKE LOWER(CONCAT('%', :country, '%'))) " +
      "AND (COALESCE(:city, '') = '' OR lower(ad.city) LIKE LOWER(CONCAT('%', :city, '%')))")
  Page<RoomModel> findAllByCountryAndCity(String country, String city, Pageable pageable);

}

