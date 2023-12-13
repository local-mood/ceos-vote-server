package com.ceos.vote.auth.service;

import com.ceos.vote.auth.jwt.entity.TokenDto;
import com.ceos.vote.exception.ErrorCode;
import com.ceos.vote.exception.CeosException;
import com.ceos.vote.auth.jwt.provider.JwtTokenProvider;
import com.ceos.vote.auth.jwt.entity.LoginRequestDto;
import com.ceos.vote.domain.member.dto.MemberRequestDto;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final String SERVER = "Server";

    // 회원가입
    @Transactional
    public void joinMember(MemberRequestDto requestDto) {

        // 이메일, ID 중복 검사
        if (findUserByEmail(requestDto.getEmail()))
            throw new CeosException(ErrorCode.ALREADY_MEMBER_EMAIL);

        if (findUserByUserid(requestDto.getUserid()))
            throw new CeosException(ErrorCode.ALREADY_MEMBER_ID);

        Member member = requestDto.toMember(passwordEncoder);
        memberRepository.save(member);
    }


    // 로그인
    @Transactional
    public TokenDto login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManager.getObject()
                .authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return generateToken(SERVER, authentication.getName(), getAuthorities(authentication));
    }


    public boolean findUserByEmail(String email) { return memberRepository.existsByEmail(email);}
    public boolean findUserByUserid(String userid) { return memberRepository.existsByUserid(userid);}

}