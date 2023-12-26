package com.ceos.vote.domain.member.dto;

import com.ceos.vote.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "멤버 dto")
public class MemberDto {

  private Long id;
  private String name;
  private String teamName;
  private Integer voteCnt;
  private Boolean isCandidate;

  @Builder
  public MemberDto(Member member) {
    this.id = member.getId();
    this.name = member.getUsername();
    this.teamName = member.getTeam().getName();
    this.voteCnt = member.getVoteCnt();
    this.isCandidate = member.getIsCandidate();
  }

}

