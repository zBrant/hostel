package io.github.zbrant.hostelapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_USER")
public class UserModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "phone_number", nullable = false, length = 20)
  private String phoneNumber;

  @Column(name = "cpf_cnpj", nullable = false, length = 20)
  private String cpfCnpj;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<RoomUserModel> roomUsers;

}
