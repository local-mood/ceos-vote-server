package com.ceos.vote.domain.team.controller;

import com.ceos.vote.auth.CurrentUser;
import com.ceos.vote.common.dto.ResponseDto;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/team")
public class TeamController {

  private final TeamService teamService;

  @GetMapping("")
  public ResponseEntity<?> getTeamList() {

    return ResponseDto.ok(teamService.getTeamList());
  }

  @PatchMapping("/{teamId}/vote")
  public ResponseEntity<?> voteTeam(
    @CurrentUser Member member,
    @PathVariable("teamId") Long teamId
  ) {
    return ResponseDto.ok(teamService.voteTeam(member, teamId));
  }

}
