package mk.ukim.finki.emt.usermanagement.port.rest;

import mk.ukim.finki.emt.usermanagement.application.service.UserManagement;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserManagement userManagement;

    public UserController(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userManagement.findAll();
    }
}
