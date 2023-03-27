package com.tripshare.entity;

import com.tripshare.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private static final long serialVersionUID = -9141886501561769867L;
    @Id
    @TableGenerator
            (
                    name = "accounts_gen",
                    table = "accounts_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "accounts",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "accounts_gen")
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    private boolean active;

    private boolean expired;

    private boolean locked;
    @OneToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER
    )
    private UserProfile userProfile;

    @ManyToMany(
        cascade = CascadeType.DETACH,
        fetch = FetchType.EAGER
    )
    @JoinTable(name = "user_roles")
    private List<Role> roles;


    public Account() {
        this.active = true;
        this.roles = new ArrayList<>(0);
    }

    public Account(UserDTO dto) {
        this();
        this.username = dto.getUsername();
        this.encryptedPassword = dto.getPassword();
        this.email = dto.getEmail();
        this.userProfile = new UserProfile(dto.getUserProfile());
    }

    public void addRole(@NonNull Role role) {
        this.roles.add(role);
    }
}
