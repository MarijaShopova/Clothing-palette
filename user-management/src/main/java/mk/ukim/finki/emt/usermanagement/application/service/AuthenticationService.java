package mk.ukim.finki.emt.usermanagement.application.service;

import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.usermanagement.configuration.JwtTokenUtil;
import mk.ukim.finki.emt.usermanagement.port.response.AuthenticationResponse;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserManagement userManagement;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JwtTokenUtil jwtTokenUtil,
                                 JwtUserDetailsService userDetailsService,
                                 UserManagement userManagement) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userManagement = userManagement;
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public AuthenticationResponse generateToken(String username) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        final Date tokenExpirationDate = jwtTokenUtil.getExpirationDateFromToken(token);
        final User user = userManagement.loadUserByUsername(Username.valueOf(username));
        return new AuthenticationResponse(user, token, tokenExpirationDate.getTime());
    }
}
