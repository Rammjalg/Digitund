package com.digitund.manage.rest;

import com.digitund.manage.data.UserGroupRepo;
import com.digitund.manage.model.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/user-groups")
public class UserGroupRestController {

  private com.digitund.manage.data.UserGroupRepo userGroupRepo;

  @Autowired
  public UserGroupRestController(UserGroupRepo userGroupRepo) {
    this.userGroupRepo = userGroupRepo;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public UserGroup getUserGroup(@PathVariable Long userId) {
    return userGroupRepo.findOne(userId);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(method = RequestMethod.POST)
  public UserGroup createUserGroup(@RequestBody UserGroup userGroup) {
    return userGroupRepo.save(userGroup);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/deleteUsers", method = RequestMethod.DELETE)
  public void deleteUserGroup(@RequestBody UserGroup userGroup) {
    userGroupRepo.delete(userGroup);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @RequestMapping(value = "/{userGroupId}", method = RequestMethod.PATCH)
  public void updateUserGroup(@RequestBody UserGroup userGroup) {
    userGroupRepo.save(userGroup);
  }

}
