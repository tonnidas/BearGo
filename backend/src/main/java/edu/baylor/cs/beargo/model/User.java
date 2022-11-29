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

    @Column(nullable = false)
    private String fullname;

    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    @Column
    private Long imageId;

    @OneToMany(mappedBy = "sender") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contract> senderContracts = new HashSet<>();

    @OneToMany(mappedBy = "traveler") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Contract> travelerContracts = new HashSet<>();

    @OneToMany(mappedBy = "complainedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComplaint> createdComplaints = new HashSet<>();

    @OneToMany(mappedBy = "complainedByUser") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private List<UserComplaint> createdComplaintsOfUser = new ArrayList<>();

    @OneToMany(mappedBy = "resolvedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComplaint> reviewedComplaints = new HashSet<>();

    @OneToMany(mappedBy = "commentedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComment> createdComments = new HashSet<>();

    // User mapped to notification entity
    @OneToMany(mappedBy = "notifyUser") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Notification> notifiedUser = new HashSet<>();

    @OneToMany(mappedBy = "fromUser") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Message> msgFrom = new HashSet<>();

    @OneToMany(mappedBy = "toUser") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Message> msgTo = new HashSet<>();

    @OneToMany(mappedBy = "postedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<BlogPost> blogPosts = new HashSet<>();

    @OneToMany(mappedBy = "reviewedBy") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ReviewAndRating> receivedReviews = new HashSet<>();

    @OneToMany(mappedBy = "reviewedTo") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ReviewAndRating> givenReviews = new HashSet<>();

    @ManyToMany(mappedBy = "interestedPeoples") // inverse-side
    private Set<ProductPost> interestedPosts = new HashSet<>();

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
