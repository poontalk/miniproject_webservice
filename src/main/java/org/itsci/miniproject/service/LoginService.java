package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.User;

import java.util.List;
import java.util.Map;

public interface LoginService {

    List<Login> getAllLogins();

    Login getLoginById(String loginId);

    Login saveLogin(Map<String ,String> map);

    Login updateLogin(Login login);

    void deleteLogin(String loginId);

    List<Login> getLoginsByUserNameContainingName (String userName);
}
