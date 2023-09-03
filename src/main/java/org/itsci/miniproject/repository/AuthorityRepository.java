package org.itsci.miniproject.repository;

import jakarta.transaction.Transactional;
import org.itsci.miniproject.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,String> {

    List<Authority> getAuthoritiesByRoleContainingIgnoreCase(String role);

    Optional<Authority> findByAuthorityId(Integer authorityId);
}
