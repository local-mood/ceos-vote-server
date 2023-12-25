package com.ceos.vote.domain.member.service;

import com.ceos.vote.common.exception.CeosException;
import com.ceos.vote.common.exception.ErrorCode;
import com.ceos.vote.domain.member.dto.MemberDto;
import com.ceos.vote.domain.member.entity.DevPart;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public List<MemberDto> getMemberList(Integer partId){

    List<MemberDto> memberList = memberRepository.findAllByDevPartOrderByVoteCntDesc(DevPart.of(partId))
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

  public MemberDto getCurrentMemberInfo(Member currentMember) {

    Member member = memberRepository.findById(currentMember.getId()).orElseThrow();

    return MemberDto.builder().member(member).build();
  }

}
