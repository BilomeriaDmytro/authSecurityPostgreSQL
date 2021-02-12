package pet.authSecurityPosgreSQL.service.Implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pet.authSecurityPosgreSQL.dto.UserDTO;
import pet.authSecurityPosgreSQL.model.AccountStatus;
import pet.authSecurityPosgreSQL.model.Role;
import pet.authSecurityPosgreSQL.model.User;
import pet.authSecurityPosgreSQL.repository.RoleRepository;
import pet.authSecurityPosgreSQL.repository.UserRepository;
import pet.authSecurityPosgreSQL.service.UserService;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceIplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setStatus(AccountStatus.ACTIVE);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        log.info("User with username - {} successfully registered. Account status - {}",user.getUsername(),user.getStatus());
        userRepository.save(user);
    }

    @Override
    public void changeStatus(User user, AccountStatus status) {
        user.setStatus(status);
        log.info("{}'s status is changed to {}", user.getUsername(), status);
    }
}
