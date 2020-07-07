package mk.ukim.finki.emt.usermanagement.domain.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private User user;

    private final String token;

    private Long tokenExpirationDate;

    public AuthenticationResponse(User user, String token, Long tokenExpirationDate) {
        this.user = user;
        this.token = token;
        this.tokenExpirationDate = tokenExpirationDate;
    }
}
