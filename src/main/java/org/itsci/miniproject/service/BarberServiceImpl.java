package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Barber;
import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.itsci.miniproject.repository.BarberRepository;
import org.itsci.miniproject.repository.LoginRepository;
import org.itsci.miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BarberServiceImpl implements BarberService{
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Barber> getAllBarber() {
        return barberRepository.findAll();
    }

    @Override
    public Barber getBarberById(String barberId) {
        return barberRepository.getReferenceById(barberId);
    }



    @Override
    public Barber saveBarber(Map<String,String> map) {
        Login login = new Login();
        String userId = map.get("userId");
        User user2 = userRepository.getUserByUserId(userId);
        String barberId = generateBarberId();
        String barberStatus = "ว่าง";

        Set<Authority> authoritySet = null;
        int authorityId = Integer.parseInt("3");
        Authority authority = authorityRepository.findByAuthorityId(authorityId).get();
        login = user2.getLogin();
        authoritySet = login.getAuthorities();
        authoritySet.add(authority);
        login.setAuthorities(authoritySet);
        loginRepository.save(login);
        Barber barber = new Barber(barberId,barberStatus,user2);

        return barberRepository.save(barber);
    }

    @Override
    public Barber updateBarber(Barber barber) {
        return barberRepository.save(barber);
    }

    @Override
    public void deleteBarber(String barberId) {
        Barber barber = barberRepository.getReferenceById(barberId);
        barberRepository.delete(barber);
    }

    @Override
    public void deleteByTableId(String barberId) {
        barberRepository.deleteByTableId(barberId);
    }


    public long getBarberCount(){
        try{
            return barberRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateBarberId(){
        String result = "" + (getBarberCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "B"+ result;
        return result;
    }
}
