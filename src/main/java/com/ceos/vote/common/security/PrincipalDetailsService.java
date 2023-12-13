package com.ceos.vote.common.security;

import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member user = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if (user != null) {
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            return principalDetails;
        }
        return null;
    }

}