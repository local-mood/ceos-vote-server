package com.ceos.vote.domain.dev_part.repository;

import com.ceos.vote.domain.dev_part.entity.DevPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevPartRepository extends JpaRepository<DevPart, Long> {
}