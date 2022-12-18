package com.tripshare.dto;

import com.tripshare.entity.UserProfile;
import com.tripshare.enums.GenderEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserProfile} entity
 */
@Data
public class UserProfileDto implements Serializable {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String profileImageUrl;
    private final GenderEnum gender;
}