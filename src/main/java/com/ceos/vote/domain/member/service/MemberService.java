package com.ceos.vote.domain.member.service;

import com.ceos.vote.common.exception.CeosException;
import com.ceos.vote.common.exception.ErrorCode;
import com.ceos.vote.domain.member.dto.MemberDto;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public List<MemberDto> getMemberList(){

    List<MemberDto> memberList = memberRepository.findAllByOrderByVoteCntDesc()
      .stream()
      .map(MemberDto::new)
      .collect(Collectors.toList());

    return memberList;
  }

  @Transactional
  public MemberDto voteMember(Member currentMember, Long memberId) {

    Boolean voteFlag = currentMember.getVoteFlagMember();

    if (!voteFlag) {
      Member member = memberRepository.findById(memberId).orElseThrow();
      member.patchVoteCnt(member.getVoteCnt());
      currentMember.patchVoteFlagMember();
      memberRepository.save(currentMember);

      return MemberDto.builder().member(member).build();
    }
    else {
      throw new CeosException(ErrorCode.ALREADY_VOTED_USER);
    }

  }

}
