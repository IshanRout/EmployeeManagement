package com.main.service;

import com.main.Repository.UserRepository;
import com.main.dto.UserDTO;
import com.main.model.Role;
import com.main.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends UserService {

        private UserRepository userRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        public UserServiceImpl(UserRepository userRepository) {
            super();
            this.userRepository = userRepository;
        }

        @Override
        public UserEntity save(UserDTO registrationDto) {
            UserEntity user = new UserEntity(registrationDto.getFirstName(),
                    registrationDto.getLastName(), registrationDto.getEmail(),
                    passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

            return userRepository.save(user);
        }


        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserEntity user = userRepository.findByEmail(username);
            if(user == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }

        private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        }
    }

