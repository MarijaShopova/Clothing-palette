package mk.ukim.finki.emt.usermanagement;


import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;
import mk.ukim.finki.emt.sharedkernel.domain.identity.Name;
import mk.ukim.finki.emt.sharedkernel.domain.measurement.Quantity;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
class DataGenerator {

    private final UserRepository userRepository;

    DataGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostConstruct
    @Transactional
    public void generateData() {
        Set<User> users;
        if (userRepository.findAll().size() == 0) {
            users = new HashSet<>();
            users.add(createVariant(new UserId("1"),new FullName(Name.valueOf("First-Name"),Name.valueOf("Last-Name")),new Username("User-Test")));
            userRepository.saveAll(users);
        }

    }

    private User createVariant(UserId userId, FullName fullName, Username username) {
        return new User(userId,fullName,username);
    }


}