package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> getUsersByFirstNameContainingIgnoreCase(String firstName);
    User getUserByUserId(String userId);
    @Transactional
    @Modifying
    @Query("SELECT u FROM Barber b join User u on b.user.userId = u.userId")
    List<User> findAllByUserAndBarber();

    User getUserByLoginLoginId(Long loginId);
}
