package edu.baylor.cs.beargo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "username must be a valid email")
    @Column(nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isAdmin = false;

    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    @OneToMany(mappedBy = "sender") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contract> senderContracts = new HashSet<>();

    @OneToMany(mappedBy = "traveler") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contract> travelerContracts = new HashSet<>();

    @OneToMany(mappedBy = "complainedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComplaint> createdComplaints = new HashSet<>();

    @OneToMany(mappedBy = "resolvedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComplaint> reviewedComplaints = new HashSet<>();

    public List<String> getRoles() {
        if (this.isAdmin) {
            return Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        } else {
            return Collections.singletonList("ROLE_USER");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
