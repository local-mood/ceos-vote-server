package com.ceos.vote.domain.member.entity;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevPart {
  BACKEND(1),
  FRONTEND(2);

  private final Integer partId;

  public static final Map<Integer, String> ID_MAP = Collections.unmodifiableMap(
    Stream.of(values())
      .collect(Collectors.toMap(DevPart::getPartId, DevPart::name))
  );

  public static DevPart of(Integer partId) {
    return DevPart.valueOf(ID_MAP.get(partId));
  }

}