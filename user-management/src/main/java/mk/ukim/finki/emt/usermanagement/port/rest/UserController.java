package mk.ukim.finki.emt.usermanagement.port.rest;

import mk.ukim.finki.emt.usermanagement.application.service.UserManagement;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userManagement.deleteUser(new UserId(id));
    }
}
