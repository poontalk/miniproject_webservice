package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Owner;

import java.util.List;
import java.util.Map;

public interface OwnerService {

    List<Owner> getListOwner();
    Owner showShopProfile(String ownerId);

    Owner saveReserve(Map<String,String> map);

    Owner editShopProfile(Map<String,String> map);

    Owner addWeekend(String weekend);
}
