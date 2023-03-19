package com.diatop.gateway.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal {

  private String name;
  private Set<String> roles = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }
}
