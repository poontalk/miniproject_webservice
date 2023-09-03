package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.itsci.miniproject.repository.LoginRepository;
import org.itsci.miniproject.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
        Login logins = new Login(userName,password);
        return loginRepository.save(logins);
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

    @Override
    public Login findByUsername(String userName){
        return loginRepository.findByUsername(userName);
    }

    @Override
    public Login assignAuthorityToLogin(Long loginId, Integer authorityId) {
        Set<Authority> authoritySet = null;
        Login login = loginRepository.findByLoginId(loginId).get();
        Authority authority = authorityRepository.findByAuthorityId(authorityId).get();
        authoritySet = login.getAuthorities();
        authoritySet.add(authority);
        login.setAuthorities(authoritySet);
        return loginRepository.save(login);
    }

    @Override
    public Login getLoginByLoginId(Long loginId) {
        return loginRepository.getLoginByLoginId(loginId);
    }

    @Override
    public LoginResponse loginUser(Login login) {
        String msg = "";

        Login login1 = loginRepository.findByUsername(login.getUsername());

        if (login1 != null){
            String password = login.getPassword();
            String encodePassword = login1.getPassword();
            if (password.equals(encodePassword)){
                Optional<Login> login2 = loginRepository.findOneByUsernameAndPassword(login.getUsername(),login.getPassword());
               if(login2.isPresent()){
                    return new LoginResponse("Login Success " ,true);
               }else {
                    return new LoginResponse("Login failed", false);
               }
            }else {
                return new LoginResponse("password Not Match", false);
            }
        }
        else{
            return new LoginResponse("Email not exits",false);
        }
    }


}
