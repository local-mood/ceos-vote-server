package com.ceos.vote.domain.team.repository;

import com.ceos.vote.domain.team.entity.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

  List<Team> findAllByOrderByVoteCntDesc();

}
