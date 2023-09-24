package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login,String> {

    List<Login> getLoginsByUsernameContainingIgnoreCase(String userName);

    Optional<Login> findOneByUsernameAndPassword(String userName, String password);

    Login findByUsername(String userName);

    Optional<Login> findByLoginId(Long loginId);

    Login getLoginByLoginId(Long loginId);

}
