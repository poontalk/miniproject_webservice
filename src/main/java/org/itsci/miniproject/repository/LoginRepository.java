package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LoginRepository extends JpaRepository<Login,String> {

    List<Login> getLoginsByUsernameContainingIgnoreCase (String userName);
}
