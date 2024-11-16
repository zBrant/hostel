package io.github.zbrant.hostelapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_ROOM")
public class RoomModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_id")
  private Long id;

  @Column(name = "description", nullable = false, length = 500)
  private String description;

  @Column(name = "price", nullable = false)
  private String price;

  @Column(name = "bed_count", nullable = false)
  private int bedCount;

  @Column(name = "rented", nullable = false)
  private boolean rented;

  @OneToOne
  @JoinColumn(name = "address_id", nullable = false)
  private AddressModel address;
}

