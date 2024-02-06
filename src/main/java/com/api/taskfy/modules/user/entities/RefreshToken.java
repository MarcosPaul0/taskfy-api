package com.api.taskfy.modules.user.entities;

import java.time.LocalDateTime;

import com.api.taskfy.constants.Entities;
import com.api.taskfy.constants.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = Tables.REFRESH_TOKEN)
@Entity(name = Entities.REFRESH_TOKEN)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class RefreshToken {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;
}
