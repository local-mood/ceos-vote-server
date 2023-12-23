package com.ceos.vote.common.dto;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto<T> implements Serializable {

  private Integer code;
  private String code_desc;
  private T data;

  public static <T> ResponseEntity<ResponseDto<T>> ok(T data) {
    return ResponseEntity.ok().body(new ResponseDto<T>(200, "OK", data));
  }

  public static <T> ResponseEntity<ResponseDto<T>> ok(HttpHeaders headers, T data) {
    return ResponseEntity.ok().headers(headers).body(new ResponseDto<T>(200, "OK", data));
  }

}
