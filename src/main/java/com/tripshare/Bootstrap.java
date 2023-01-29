package com.tripshare;

import com.tripshare.entity.Role;
import com.tripshare.entity.Trip;
import com.tripshare.entity.User;
import com.tripshare.entity.UserProfile;
import com.tripshare.entity.elasticsearch.TripDocument;
import com.tripshare.repository.RoleRepository;
import com.tripshare.repository.TripRepository;
import com.tripshare.repository.UserRepository;
import com.tripshare.repository.elasticsearch.TripElasticRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

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
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@admin.com");
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName("Admin");
            user.setUserProfile(userProfile);
            user.addRole(roleAdmin);
            user.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
            user = new User();
            user.setUsername("user1");
            user.setEmail("user1@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User1");
            user.setUserProfile(userProfile);
            user.addRole(roleAdmin);
            user.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
            user = new User();
            user.setUsername("user2");
            user.setEmail("user2@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User2");
            user.setUserProfile(userProfile);
            user.addRole(roleAdmin);
            user.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
            user = new User();
            user.setUsername("user3");
            user.setEmail("user3@user.com");
            userProfile = new UserProfile();
            userProfile.setFirstName("User3");
            user.setUserProfile(userProfile);
            user.addRole(roleAdmin);
            user.setEncryptedPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
        }
        long count = tripElasticRepository.count();
        List<Trip> all = tripRepository.findAll();
    }

}
