package com.tripshare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tripshare.entity.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link UserProfile} entity
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileDto {

    private long id;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private String gender;

    public UserProfileDto(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.profileImageUrl = userProfile.getProfileImageUrl();
    }

}
