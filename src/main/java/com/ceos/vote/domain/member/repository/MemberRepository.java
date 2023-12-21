package com.ceos.vote.domain.member.repository;

import com.ceos.vote.domain.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserid(String userid);

    List<Member> findAllByOrderByVoteCntDesc();

}
