package mk.ukim.finki.emt.usermanagement.port.rest;

import mk.ukim.finki.emt.usermanagement.application.service.UserManagement;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;
import mk.ukim.finki.emt.usermanagement.port.request.UserCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserCreateRequest request) {
        this.userManagement.create(request);
    }

    @PatchMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userManagement.deleteUser(new UserId(id));
    }
}
