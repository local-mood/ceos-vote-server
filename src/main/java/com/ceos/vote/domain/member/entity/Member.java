package com.ceos.vote.domain.member.entity;

import com.ceos.vote.domain.dev_part.entity.DevPart;
import com.ceos.vote.domain.team.entity.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String userid;
  private String email;
  private String password;

  private Boolean voteFlagMember;
  private Boolean voteFlagTeam;

  private Integer voteCnt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  private Team team;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dev_part_id")
  private DevPart devPart;

  @Builder
  public Member(Long id, String username, String userId, String email, String password, Boolean voteFlagMember, Boolean voteFlagTeam, Integer voteCnt, Team team, DevPart devPart) {
    this.id = id;
    this.username = username;
    this.userid = userId;
    this.email = email;
    this.password = password;
    this.voteFlagMember = voteFlagMember;
    this.voteFlagTeam = voteFlagTeam;
    this.voteCnt = voteCnt;
    this.team = team;
    this.devPart = devPart;
  }
}
