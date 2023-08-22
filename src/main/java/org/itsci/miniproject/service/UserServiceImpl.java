package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.itsci.miniproject.repository.LoginRepository;
import org.itsci.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

@Override
    public List<User> getAllUsers() {
    return  userRepository.findAll();
}

    @Override
    public User getUserbyId(String userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public User saveUser(Map<String, String> map) {
        Login login = new Login();
        String userId = generateUserId();
        String firstname = map.get("firstName");
        String lastname = map.get("lastName");
        String address = map.get("address");
        String email = map.get("email");
        String mobileNo = map.get("mobileNo");
        String userName = map.get("username");
        String password = map.get("password");
        login.setUsername(userName);
        login.setPassword(password);
        loginRepository.save(login);

        Set<Authority> authoritySet = null;
        int authorityId = Integer.parseInt("4");
        Authority authority = authorityRepository.findByAuthorityId(authorityId).get();
        login = loginRepository.findByLoginId(login.getLoginId()).get();
        authoritySet = login.getAuthorities();
        authoritySet.add(authority);
        login.setAuthorities(authoritySet);
        loginRepository.save(login);

        User user = new User(userId,firstname,lastname,address,email,mobileNo,login);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.getReferenceById(userId);
        userRepository.delete(user);
    }

    @Override
    public List<User> getUsersByFirstNameContainingName(String firstName) {
        return userRepository.getUsersByFirstNameContainingIgnoreCase(firstName);
    }

    public long getAccountCount(){
    try{
        return userRepository.count();
    }catch (Exception e){
        return 0;
        }
    }

    public String generateUserId(){
    String result = "" + (getAccountCount() + 1);
    while (result.length() < 4){
        result = "0" + result;
    }
    result = "U"+ result;
    return result;
    }
}
