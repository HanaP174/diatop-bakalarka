package com.diatop.gateway.controller;

import com.diatop.gateway.model.UserPrincipal;
import com.diatop.gateway.service.UserService;
import com.diatop.model.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GatewayController {

  @Autowired
  private UserService userService;

  @RequestMapping("/user")
  @ResponseBody
  public UserPrincipal user(Principal user) {
    UserPrincipal userPrincipal = new UserPrincipal();
    userPrincipal.setName(user.getName());
    userPrincipal.setRoles(AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
    return userPrincipal;
  }

  @PostMapping("/addUser")
  public void addUser(@RequestBody UserDto user) {
    userService.addUser(user);
  }
}
