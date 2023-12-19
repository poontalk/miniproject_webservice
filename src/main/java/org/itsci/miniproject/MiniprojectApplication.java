package org.itsci.miniproject;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.model.ReserveDetail;
import org.itsci.miniproject.repository.AuthorityRepository;
import org.itsci.miniproject.repository.ReserveDetailRepository;
import org.itsci.miniproject.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MiniprojectApplication implements CommandLineRunner {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private ReserveDetailRepository reserveDetailRepository;
    @Autowired
    private ReserveRepository reserveRepository;

    public static void main(String[] args) {
        SpringApplication.run(MiniprojectApplication.class, args);
    }

    @Override
    public void run(String... args)throws Exception{
        int id = 1;
        Authority a = new Authority(id, "admin");
        authorityRepository.save(a);
        id = 2;
        Authority b = new Authority(id, "owner");
        authorityRepository.save(b);
        id = 3;
        Authority c = new Authority(id, "barber");
        authorityRepository.save(c);
        id = 4;
        Authority d = new Authority(id, "customer");
        authorityRepository.save(d);
        id = 5;
        Authority e = new Authority(id, "user");
        authorityRepository.save(e);

    }
}
