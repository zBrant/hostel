package io.github.zbrant.hostelapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_ROOM_USER")
public class RoomUserModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_user_id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserModel.class)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private UserModel user;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomModel.class)
  @JoinColumn(name = "room_id")
  @JsonBackReference
  private RoomModel room;

}

