package com.ceos.vote.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevPart {
  BACKEND("백엔드"),
  FRONTEND("프론트엔드");

  private final String partName;
}