package io.github.zbrant.hostelapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "address_id")
  private Long id;

  @Column(name = "city", nullable = false, length = 100)
  private String city;

  @Column(name = "state", nullable = false, length = 100)
  private String state;

  @Column(name = "cep", nullable = false, length = 20)
  private String CEP;

  @Column(name = "country", nullable = false, length = 100)
  private String country;

  @Column(name = "number", nullable = false)
  private int number;
}

