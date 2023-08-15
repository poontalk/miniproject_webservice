package org.itsci.miniproject.service;

import org.itsci.miniproject.model.User;
import org.itsci.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
@Autowired
    private UserRepository userRepository;

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

        String userId = map.get("userId");
        String firstname = map.get("firstName");
        String lastname = map.get("lastName");
        String address = map.get("address");
        String email = map.get("email");
        String mobileNo = map.get("mobileNo");

        User user = new User(userId,firstname,lastname,address,email,mobileNo);

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




}
