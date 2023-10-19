package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> getUsersByFirstNameContainingIgnoreCase(String firstName);
    User getUserByUserId(String userId);
    @Transactional
    @Modifying
    @Query("SELECT u FROM Barber b join User u on b.user.userId = u.userId")
    List<User> findAllByUserAndBarber();

    User getUserByLoginLoginId(Long loginId);

    @Transactional
    @Modifying
    @Query("update User u set u.firstName = :firstname, u.lastName = :lastname, " +
            "u.address = :address, u.email = :email, u.mobileNo = :tel where u.userId = :userId")
    void doEditProfile(@Param("userId") String userId, @Param("firstname") String firstname,
                       @Param("lastname") String lastname, @Param("address") String address,
                       @Param("email") String email, @Param("tel") String tel);
    //@Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM u.login.authorities a WHERE a.role LIKE :role)")
    @Query("SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM u.login.authorities a WHERE a.role LIKE :role OR a.role LIKE :role2)")
    List<User> findUsersByRole(@Param("role") String role,@Param("role2") String role2);


}
