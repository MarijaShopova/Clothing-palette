package mk.ukim.finki.emt.usermanagement.application.service;

import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.usermanagement.domain.event.UserDeleted;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserManagement {

    private final UserRepository repository;
    private final PasswordEncoder bcryptEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserManagement(UserRepository repository,
                          PasswordEncoder bcryptEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.repository = repository;
        this.bcryptEncoder = bcryptEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<User> findAll() {
        return repository.findAll();
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

    public void deleteUser(UserId userId) {
        User user = repository.findById(userId).orElseThrow(RuntimeException::new);
        user.setDeleted(true);
        repository.save(user);
        applicationEventPublisher.publishEvent(new UserDeleted(userId, Instant.now()));
    }
}
