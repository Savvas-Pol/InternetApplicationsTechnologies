//package com.savvas.jobapp.security;
//
//import com.savvas.jobapp.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//import static java.util.Collections.emptyList;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        String username = email;
//
//        Optional<com.savvas.jobapp.model.User> entity = userRepository.findByEmail(username);
//        if (!entity.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        String password = entity.get().getPassword();
//
//        return new User(username, password, emptyList());
//    }
//}
