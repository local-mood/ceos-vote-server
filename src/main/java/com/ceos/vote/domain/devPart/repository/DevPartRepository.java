package com.ceos.vote.domain.devPart.repository;

import com.ceos.vote.domain.devPart.entity.DevPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevPartRepository extends JpaRepository<DevPart, Long> {
}