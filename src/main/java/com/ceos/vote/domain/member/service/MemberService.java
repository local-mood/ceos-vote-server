package com.ceos.vote.domain.member.service;

import com.ceos.vote.domain.member.dto.MemberDto;
import com.ceos.vote.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
