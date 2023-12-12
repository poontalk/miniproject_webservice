package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Owner;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.repository.OwnerRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.itsci.miniproject.response.ReportIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Owner editShopProfile(Map<String,String> map) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime openTime = LocalDateTime.parse("2023-01-01"+" "+map.get("openTime"), formatter);
        LocalDateTime closeTime = LocalDateTime.parse("2023-01-01"+" "+map.get("closeTime"), formatter);
        LocalDateTime dayOff = map.get("dayOff").equals("") ? null : LocalDateTime.parse(map.get("dayOff")+" "+"00:00", formatter);
        List<Owner> ownerList = ownerRepository.findAll();
        Owner owner = ownerRepository.getReferenceById(ownerList.get(0).getOwnerId());
        owner.setShopName(map.get("shopName"));
        owner.setOpenTime(openTime);
        owner.setCloseTime(closeTime);
        owner.setDayOff(dayOff);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner addWeekend(String weekend) {
        List<Owner> ownerList = ownerRepository.findAll();
        Owner owner = ownerRepository.getReferenceById(ownerList.get(0).getOwnerId());
        owner.setWeekend(weekend);
        return ownerRepository.save(owner);
    }

}
