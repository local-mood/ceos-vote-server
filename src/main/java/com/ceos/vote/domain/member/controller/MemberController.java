package com.ceos.vote.domain.member.controller;

import com.ceos.vote.auth.CurrentUser;
import com.ceos.vote.common.dto.ResponseDto;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("")
  public ResponseEntity<?> getMemberList() {

    return ResponseDto.ok(memberService.getMemberList());
  }

  @PatchMapping("/{memberId}/vote")
  public ResponseEntity<?> voteMember(
    @CurrentUser Member member,
    @PathVariable("memberId") Long memberId
  ) {
    return ResponseDto.ok(memberService.voteMember(member, memberId));
  }

}
