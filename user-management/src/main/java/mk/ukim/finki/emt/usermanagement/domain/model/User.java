package mk.ukim.finki.emt.usermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.authentication.Username;
import mk.ukim.finki.emt.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.sharedkernel.domain.identity.FullName;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users")
@Where(clause = "deleted=false")
public class User extends AbstractEntity<UserId> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", nullable = false))})
    FullName fullName;

    @Embedded
    Address address;

    String email;

    String mobile;

    @Enumerated
    @AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "username", nullable = false))})
    Username username;

    String password;

    @Enumerated(value = EnumType.STRING)
    UserRole role;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public User() { }

    public User(UserId userId, FullName fullName, Username username, String password, String email, String mobile, Address address){
        super(userId);
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.role = UserRole.USER;
    }

    @Override
    public UserId id() {
        return id;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
}
