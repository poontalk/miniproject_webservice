package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

List<User> getUsersByFirstNameContainingIgnoreCase (String firstName);
}
