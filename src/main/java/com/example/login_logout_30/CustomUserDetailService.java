package com.example.login_logout_30;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private CustomUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = userRepository.findByNameEquals(username).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
        return new CustomUserDetails(customUser);
    }

    public List<CustomUser> list(){
        return userRepository.findAll();
    }
    public void save(CustomUser customUser){
        customUser.setPass(new BCryptPasswordEncoder().encode(customUser.getPass()));
        userRepository.save(customUser);
    }
}
