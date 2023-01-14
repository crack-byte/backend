package com.tripshare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import com.tripshare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.tripshare.entity.User} entity
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

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.userProfile = new UserProfileDto(user.getUserProfile());
	}

}