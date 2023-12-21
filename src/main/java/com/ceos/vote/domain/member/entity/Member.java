package com.ceos.vote.domain.member.entity;

import com.ceos.vote.domain.team.entity.Team;
import jakarta.persistence.*;
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

  @Enumerated(EnumType.STRING)
  private DevPart devPart;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public Member(Long id, String username, String userid, String email, String password, Boolean voteFlagMember, Boolean voteFlagTeam, Integer voteCnt, Team team, DevPart devPart) {
    this.id = id;
    this.username = username;
    this.userid = userid;
    this.email = email;
    this.password = password;
    this.voteFlagMember = voteFlagMember;
    this.voteFlagTeam = voteFlagTeam;
    this.voteCnt = voteCnt;
    this.team = team;
    this.devPart = devPart;
    this.role = Role.ROLE_USER;

  }

  public void patchVoteFlagTeam(){
    this.voteFlagTeam = Boolean.TRUE;
  }

  public void patchVoteFlagMember(){
    this.voteFlagMember = Boolean.TRUE;
  }

  public void patchVoteCnt(Integer voteCnt){
    this.voteCnt = voteCnt+1;
  }

}
