package mk.ukim.finki.emt.usermanagement.application.service;

import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagement {

    private final UserRepository repository;
    private final PasswordEncoder bcryptEncoder;

    public UserManagement(UserRepository repository,
                          PasswordEncoder bcryptEncoder) {
        this.repository = repository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public User loadUserByUsername(Username username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username.getValue()));
    }

//    public User saveUser() throws IOException {
//
//        newAccount.setPassword(bcryptEncoder.encode(newAccount.getPassword()));
//        return repository.save(newAccount);
//    }

    public void deleteUser(UserId id) {
        this.repository.deleteById(id);
    }
}
