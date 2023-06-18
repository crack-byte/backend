package com.tripshare.entity;

import com.tripshare.dto.UserProfileDto;
import com.tripshare.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity {

    private static final long serialVersionUID = -6983695384824760314L;
    @Id
    @TableGenerator
            (
                    name = "user_profile_gen",
                    table = "user_profile_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "user_profile",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_profile_gen")
    private long id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public UserProfile(UserProfileDto userProfileDto) {
        this.firstName = userProfileDto.getFirstName();
        this.lastName = userProfileDto.getLastName();
        this.profileImageUrl = userProfileDto.getProfileImageUrl();
        if (userProfileDto.getGender() != null)
            this.gender = GenderEnum.valueOf(userProfileDto.getGender());
    }

    public UserProfile() {
    }

}
