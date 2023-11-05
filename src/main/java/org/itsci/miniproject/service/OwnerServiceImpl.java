package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Owner;
import org.itsci.miniproject.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;


    @Override
    public List<Owner> getListOwner() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner showShopProfile(String ownerId) {
        return ownerRepository.getReferenceById(ownerId);
    }

    @Override
    public Owner editShopProfile(Owner owner) {
        return ownerRepository.save(owner);
    }
}
