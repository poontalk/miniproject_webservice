package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Customer;
import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.itsci.miniproject.repository.CustomerRepository;
import org.itsci.miniproject.repository.LoginRepository;
import org.itsci.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String customerId = generateCustomerId();
        String firstname = map.get("firstName");
        String lastname = map.get("lastName");
        String address = map.get("address");
        String email = map.get("email");
        String mobileNo = map.get("mobileNo");
        String userName = map.get("username");
        String password = map.get("password");
        login.setUsername(userName);
        login.setPassword(this.passwordEncoder.encode(password));
        loginRepository.save(login);

        Set<Authority> authoritySet = null;
        int authorityId = Integer.parseInt("4");
        Authority authority = authorityRepository.findByAuthorityId(authorityId).get();
        login = loginRepository.findByLoginId(login.getLoginId()).get();
        authoritySet = login.getAuthorities();
        authoritySet.add(authority);
        login.setAuthorities(authoritySet);
        loginRepository.save(login);

        Customer customer = new Customer(userId,firstname,lastname,address,email,mobileNo,login,customerId);
        return userRepository.save(customer);
    }

    @Override
    public User updateUser(Map<String, String> map) {
        String UId = map.get("userId");
        String firstname = map.get("firstName");
        String lastname = map.get("lastName");
        String address = map.get("address");
        String email = map.get("email");
        String tel = map.get("mobileNo");
        String Lid = map.get("loginId");
        String username =  map.get("userName");
        String password = map.get("password");
        Login login = loginRepository.getLoginByLoginId(Long.parseLong(Lid));
        login.setUsername(username);
        login.setPassword(this.passwordEncoder.encode(password));
        loginRepository.save(login);

        //User user1 = new User(UId,firstname,lastname,address,email,tel,login);

        User user = userRepository.getUserByUserId(UId);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setAddress(address);
        user.setMobileNo(tel);
        user.setEmail(email);
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

    public long getCustomerCount(){
        try{
            return customerRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateCustomerId(){
        String result = "" + (getCustomerCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "C"+ result;
        return result;
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
    @Override
    public List<User> getFirstNameandLastName() {
        return userRepository.findAllByUserAndBarber();
    }

    @Override
    public User getUserByLoginId(Long loginId) {
        return userRepository.getUserByLoginLoginId(loginId);
    }

    @Override
    public void doEditProfile( Map<String, String> map) {
    String UId = map.get("userId");
    String firstname = map.get("firstName");
    String lastname = map.get("lastName");
    String address = map.get("address");
    String email = map.get("email");
    String tel = map.get("mobileNo");

        userRepository.doEditProfile(UId,firstname,lastname,address,email,tel);
    }
    @Override
    public List<User> getUsersByRole(String role,String role2) {
        return userRepository.findUsersByRole(role,role2);
    }
}
