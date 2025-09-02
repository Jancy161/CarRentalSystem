/*
 * package com.hexaware.carrentalsystems.config;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.hexaware.carrentalsystems.entities.User; import
 * com.hexaware.carrentalsystems.repository.IUserRepository;
 * 
 * @Service public class UserInfoUserDetailsService implements
 * UserDetailsService {
 * 
 * @Autowired private IUserRepository userRepository;
 * 
 * 
 * @Override public UserDetails loadUserByUsername(String name) throws
 * UsernameNotFoundException { Optional<User> user =
 * Optional.ofNullable(userRepository.findByName(name)); return
 * user.map(UserInfoUserDetails::new) .orElseThrow(() -> new
 * UsernameNotFoundException("User not found with name: " + name)); }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { List<User> users =
 * userRepository.findByEmail(username); if (users.isEmpty()) { throw new
 * UsernameNotFoundException("User not found with email: " + username); } User
 * user = users.get(0); // Use the first user return new
 * UserInfoUserDetails(user); } }
 */

package com.hexaware.carrentalsystems.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.repository.IUserRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new UserInfoUserDetails(users.get(0));
    }
}
