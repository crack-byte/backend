package com.tripshare.entity;

import com.tripshare.dto.UserProfileDto;
import com.tripshare.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class UserProfile extends BaseEntity {

	private static final long serialVersionUID = -6983695384824760314L;
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
