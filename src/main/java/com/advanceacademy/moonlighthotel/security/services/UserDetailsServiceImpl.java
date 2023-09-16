package com.advanceacademy.moonlighthotel.security.services;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
    User user = userRepository.findByFirstName(firstName)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + firstName));

    return UserDetailsImpl.build(user);
  }

}
