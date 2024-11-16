package io.github.zbrant.hostelapi.models;

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

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private RoomModel room;
}

