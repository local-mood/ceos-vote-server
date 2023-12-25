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

    Member voteMember = memberRepository.findById(memberId).orElseThrow();

    if (voteMember.getDevPart().equals(DevPart.BACKEND) && !currentMember.getVoteFlagBe()){
      currentMember.patchVoteFlagBe();
    }
    else if (voteMember.getDevPart().equals(DevPart.FRONTEND) && !currentMember.getVoteFlagFe()){
      currentMember.patchVoteFlagFe();
    }
    else {
      throw new CeosException(ErrorCode.ALREADY_VOTED_USER);
    }

    voteMember.patchVoteCnt(voteMember.getVoteCnt());
    memberRepository.save(currentMember);

    return MemberDto.builder().member(voteMember).build();

  }

  public MemberDto getCurrentMemberInfo(Member currentMember) {

    Member member = memberRepository.findById(currentMember.getId()).orElseThrow();

    return MemberDto.builder().member(member).build();
  }

}
