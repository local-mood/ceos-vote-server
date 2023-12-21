package com.ceos.vote.domain.team.dto;

import com.ceos.vote.domain.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "팀 dto")
public class TeamDto {

  private Long id;
  private String name;
  private String description;
  private Integer voteCnt;

  @Builder
  public TeamDto(Team team) {
    this.id = team.getId();
    this.name = team.getName();
    this.description = team.getDescription();
    this.voteCnt = team.getVoteCnt();
  }

}
