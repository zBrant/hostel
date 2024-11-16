package io.github.zbrant.hostelapi.repositories;

import io.github.zbrant.hostelapi.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
}
