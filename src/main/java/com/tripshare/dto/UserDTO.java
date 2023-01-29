package com.tripshare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import com.tripshare.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Account} entity
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String username;
    private String email;
    private String password;
    @NotNull
    private UserProfileDto userProfile;
    private List<RoleDto> permissions;

    public UserDTO() {
        this.permissions = new ArrayList<>();
    }

    public UserDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.userProfile = new UserProfileDto(account.getUserProfile());
    }

}
