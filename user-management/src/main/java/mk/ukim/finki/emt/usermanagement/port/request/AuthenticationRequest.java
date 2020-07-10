package mk.ukim.finki.emt.usermanagement.port.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class AuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;

    private String password;

    public AuthenticationRequest() { }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
