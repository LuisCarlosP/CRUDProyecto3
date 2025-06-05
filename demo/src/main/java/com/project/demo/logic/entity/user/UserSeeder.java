package com.project.demo.logic.entity.user;

import com.project.demo.logic.entity.rol.Role;
import com.project.demo.logic.entity.rol.RoleEnum;
import com.project.demo.logic.entity.rol.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadUsers();
    }

    private void loadUsers() {

        Optional<Role> userRole = roleRepository.findByName(RoleEnum.USER);
        if (userRole.isPresent() && userRepository.findByEmail("user@email.com").isEmpty()) {
            User user = new User();
            user.setName("Luis");
            user.setLastname("Usuario");
            user.setEmail("user@email.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(userRole.get());
            userRepository.save(user);
        }
    }
}