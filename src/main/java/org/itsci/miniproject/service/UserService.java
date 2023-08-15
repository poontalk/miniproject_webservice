package org.itsci.miniproject.service;

import org.itsci.miniproject.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

List<User> getAllUsers ();

User getUserbyId(String userId);

User saveUser (Map<String,String> map);

User updateUser (User user);

void deleteUser(String userId);

List<User> getUsersByFirstNameContainingName(String firstName);

}
