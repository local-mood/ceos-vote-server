package com.ceos.vote.domain.team.service;

import com.ceos.vote.domain.team.dto.TeamDto;
import com.ceos.vote.domain.team.repository.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

  private final TeamRepository teamRepository;

  public List<TeamDto> getTeamList(){

    List<TeamDto> teamList = teamRepository.findAllByOrderByVoteCntDesc()
      .stream()
      .map(TeamDto::new)
      .collect(Collectors.toList());

    return teamList;
  }

}
