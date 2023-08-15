package org.itsci.miniproject.repository;

import org.itsci.miniproject.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,String> {

    List<Authority> getAuthoritiesByRoleContainingIgnoreCase(String role);
}
