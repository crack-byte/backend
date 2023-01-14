package com.tripshare;

import com.tripshare.entity.Role;
import com.tripshare.entity.User;
import com.tripshare.entity.UserProfile;
import com.tripshare.repository.RoleRepository;
import com.tripshare.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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

    /**
     *
     */
    @PostConstruct
    @Transactional
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        if(userRepository.count() == 0) {
            Role role = new Role();
            role.setName("USER");
            role.setPriority(1);
            roleRepository.save(role);
            User user = new User();
            user.setUsername("user");
            user.setEmail("admin@admin.com");
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName("Admin");
            user.setUserProfile(userProfile);
            user.addRole(role);
            user.setEncryptedPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }

}
