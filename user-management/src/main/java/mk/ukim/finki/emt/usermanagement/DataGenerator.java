package mk.ukim.finki.emt.usermanagement;


import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
class DataGenerator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    DataGenerator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void generateData() {
        Set<User> users;
        if (userRepository.findAll().size() == 0) {
            users = new HashSet<>();
            users.add(createUser(new UserId("1"),
                    new FullName(Name.valueOf("First-Name"), Name.valueOf("Last-Name")),
                    new Username("User-Test"),
                    passwordEncoder.encode("password"),
                    "email@email.com",
                    "077777777",
                    new Address("Address", "City", "Country")));
            users.add(createUser(new UserId("2"),
                    new FullName(Name.valueOf("First-Name2"), Name.valueOf("Last-Name2")),
                    new Username("User-Test"),
                    passwordEncoder.encode("password"),
                    "email2@email.com",
                    "077777777",
                    new Address("Address", "City", "Country")));
            userRepository.saveAll(users);
        }
    }

    private User createUser(UserId userId, FullName fullName, Username username, String password, String email, String mobile, Address address) {
        return new User(userId, fullName, username, password, email, mobile, address);
    }


}
