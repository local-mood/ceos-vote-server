package com.ceos.vote.domain.team.service;

import com.ceos.vote.common.exception.CeosException;
import com.ceos.vote.common.exception.ErrorCode;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.repository.MemberRepository;
import com.ceos.vote.domain.team.dto.TeamDto;
import com.ceos.vote.domain.team.entity.Team;
import com.ceos.vote.domain.team.repository.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

  private final TeamRepository teamRepository;
  private final MemberRepository memberRepository;

  public List<TeamDto> getTeamList(){

    List<TeamDto> teamList = teamRepository.findAllByOrderByVoteCntDesc()
      .stream()
      .map(TeamDto::new)
      .collect(Collectors.toList());

    return teamList;
  }

  @Transactional
  public TeamDto voteTeam(Member currentMember, Long teamId) {

    Boolean voteFlag = currentMember.getVoteFlagTeam();

    if (!voteFlag) {
      Team team = teamRepository.findById(teamId).orElseThrow();
      team.patchVoteCnt(team.getVoteCnt());
      currentMember.patchVoteFlagTeam();
      memberRepository.save(currentMember);

      return TeamDto.builder().team(team).build();
    }
    else {
      throw new CeosException(ErrorCode.ALREADY_VOTED_USER);
    }

  }

}
