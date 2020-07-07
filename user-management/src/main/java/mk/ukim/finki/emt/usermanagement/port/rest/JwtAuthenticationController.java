package mk.ukim.finki.emt.usermanagement.port.rest;

import mk.ukim.finki.emt.usermanagement.application.service.AuthenticationService;
import mk.ukim.finki.emt.usermanagement.domain.model.AuthenticationRequest;
import mk.ukim.finki.emt.usermanagement.domain.model.AuthenticationResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class JwtAuthenticationController {

    private final AuthenticationService service;

    public JwtAuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        this.service.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return this.service.generateToken(authenticationRequest.getUsername());
    }
}
