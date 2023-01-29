package com.tripshare;

import com.tripshare.entity.Role;
import com.tripshare.entity.Trip;
import com.tripshare.entity.Account;
import com.tripshare.entity.UserProfile;
import com.tripshare.repository.RoleRepository;
import com.tripshare.repository.TripRepository;
import com.tripshare.repository.UserRepository;
import com.tripshare.repository.elasticsearch.TripElasticRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 *
 */
@Component
@AllArgsConstructor
public class Bootstrap {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TripElasticRepository tripElasticRepository;
    private final TripRepository tripRepository;
    /**
     *
     */
    @PostConstruct
    @Transactional
    void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(sdf.format(System.currentTimeMillis()));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        if (userRepository.count() == 0) {
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleAdmin.setPriority(1);
            roleRepository.save(roleAdmin);
            Role roleUser = new Role();
            roleUser.setName("USER");
            roleUser.setPriority(1);
            roleRepository.save(roleUser);
            Account account = new Account();
            account.setUsername("admin");
            account.setEmail("admin@admin.com");
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName("Admin");
            account.setUserProfile(userProfile);
            account.addRole(roleAdmin);
            account.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(account);
            account = new Account();
            account.setUsername("user1");
            account.setEmail("user1@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User1");
            account.setUserProfile(userProfile);
            account.addRole(roleAdmin);
            account.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(account);
            account = new Account();
            account.setUsername("user2");
            account.setEmail("user2@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User2");
            account.setUserProfile(userProfile);
            account.addRole(roleAdmin);
            account.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(account);
            account = new Account();
            account.setUsername("user3");
            account.setEmail("user3@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User3");
            account.setUserProfile(userProfile);
            account.addRole(roleAdmin);
            account.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(account);
        }
        long count = tripElasticRepository.count();
        List<Trip> all = tripRepository.findAll();
    }

}
