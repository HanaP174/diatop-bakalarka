package com.example.gateway.controller;

import com.example.gateway.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GatewayController {

  @RequestMapping("/user")
  @ResponseBody
  public UserPrincipal user(Principal user) {
    UserPrincipal userPrincipal = new UserPrincipal();
    userPrincipal.setName(user.getName());
    userPrincipal.setRoles(AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
    return userPrincipal;
  }
}
