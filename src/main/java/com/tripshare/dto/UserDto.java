package com.tripshare.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.tripshare.entity.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final long id;
    private final String username;
    private final String email;
    private final String password;
    private final UserProfileDto userProfile;
    private final List<PermissionDto> permissions;
}