package org.itsci.miniproject.service;

import org.itsci.miniproject.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers ();
    User getUserbyId(String userId);
    User saveUser (Map<String,String> map);
    User updateUser (Map<String, String> map);
    void deleteUser(String userId);
    List<User> getUsersByFirstNameContainingName(String firstName);
    User getUserByUSerId (String userId);
    List<User> getFirstNameandLastName();
    User getUserByLoginId (Long loginId);
    void doEditProfile(Map<String,String> map);
    List<User> getUsersByRole(String role,String role2);
}
