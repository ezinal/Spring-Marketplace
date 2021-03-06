package com.tr.obss.jss.jss_marketplace.dto;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JwtResponse {

  private String token;

  @Default
  private String type = "Bearer";

  private Long id;

  private String username;

  private String email;

  private List<String> roles;
}
