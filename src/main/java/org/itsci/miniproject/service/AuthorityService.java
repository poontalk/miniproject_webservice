package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;

import java.util.List;
import java.util.Map;


public interface AuthorityService {

    List<Authority> getAllAuthority();

    Authority getAuthorityById(String authorityId);

    Authority saveAuthority(Map<String,String> map);

    Authority updateAuthority(Authority authority);

    void deleteAuthority(String authorityId);

    List<Authority> getAuthoritiesByRoleContainingName(String role);

}
