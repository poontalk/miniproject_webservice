package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginRepository loginRepository;
    @Override
    public List<Login> getAllLogins() {
        return loginRepository.findAll();
    }

    @Override
    public Login getLoginById(String loginId) {
        return loginRepository.getReferenceById(loginId);
    }

    @Override
    public Login saveLogin(Map<String, String> map) {
        String userName = map.get("username");
        String password = map.get("password");

        Login login = new Login(userName,password);

        return loginRepository.save(login);
    }

    @Override
    public Login updateLogin(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public void deleteLogin(String loginId) {
        Login login = loginRepository.getReferenceById(loginId);
        loginRepository.delete(login);
    }

    @Override
    public List<Login> getLoginsByUserNameContainingName(String userName) {
        return loginRepository.getLoginsByUsernameContainingIgnoreCase(userName);
    }
}
