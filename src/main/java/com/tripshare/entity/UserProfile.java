package com.tripshare.entity;

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

	@Column(nullable = false)
	private String firstName;
	private String lastName;
	private String profileImageUrl;
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

}
