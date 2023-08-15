package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public List<Authority> getAllAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getAuthorityById(String authorityId) {
        return authorityRepository.getReferenceById(authorityId);
    }

    @Override
    public Authority saveAuthority(Map<String, String> map) {
        String authorityId = map.get("authorityId");
        String role = map.get("role");

        Authority authority = new Authority(Integer.parseInt(authorityId),role);
        return authorityRepository.save(authority);
    }

    @Override
    public Authority updateAuthority(Authority authority) {
        authorityRepository.save(authority);
        return null;
    }

    @Override
    public void deleteAuthority(String authorityId) {
        Authority authority = authorityRepository.getReferenceById(authorityId);
        authorityRepository.delete(authority);
    }

    @Override
    public List<Authority> getAuthoritiesByRoleContainingName(String role) {
        return authorityRepository.getAuthoritiesByRoleContainingIgnoreCase(role);
    }
}
