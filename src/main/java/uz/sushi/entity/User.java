package uz.sushi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @ManyToOne
    private Address address;

    @Column
    private Boolean female;

    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Product> favorites;

    public User() {
        enabled=true;
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions();
    }

    @Override
    public String getUsername() {
        return phoneNumber != null ? phoneNumber : email;
    }

    /**
     * For UserDetails
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
