package mk.ukim.finki.emt.usermanagement.port.request;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;
import mk.ukim.finki.emt.usermanagement.domain.model.UserId;

import javax.validation.constraints.NotNull;

@Getter
public class UserCreateRequest {

    @NotNull
    UserId userId;

    @NotNull
    FullName fullName;

    @NotNull
    Address address;

    @NotNull
    String email;

    @NotNull
    String mobile;

    @NotNull
    Username username;

    @NotNull
    String password;
}
