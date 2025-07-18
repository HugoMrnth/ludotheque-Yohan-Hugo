package fr.eni.ludotheque.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if("bob".equals(username)) {
            UserBuilder userBuilder = User.builder();
            String azerty ="{bcrypt}$2a$10$bRueya.KXfWZ1FEy..jbD.Kyf48uodYBWQ61PMuBFsG0i8U93V.6O";
            userBuilder.username(username).password(azerty).roles("ADMIN");
            userDetails = userBuilder.build();

        }else if("jane".equals(username)) {
            UserBuilder userBuilder = User.builder();
            String qwerty = "{bcrypt}$2a$10$wWj07ftznoUXiAcYWkBvH.P9bRv3mjRXava.u7iaGvjz.gyw2UEqS";
            userBuilder.username(username).password(qwerty);
            userDetails = userBuilder.build();
        }

        else {
            throw new UsernameNotFoundException(username);
        }

        return userDetails;
    }

}
