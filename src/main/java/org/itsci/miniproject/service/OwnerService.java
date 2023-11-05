package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Owner;

import java.util.List;

public interface OwnerService {

    List<Owner> getListOwner();
    Owner showShopProfile(String ownerId);

    Owner editShopProfile(Owner owner);
}
