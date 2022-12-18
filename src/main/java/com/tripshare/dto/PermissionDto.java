package com.tripshare.dto;

import com.tripshare.entity.Permission;
import lombok.Data;

/**
 * A DTO for the {@link Permission} entity
 */
@Data
public class PermissionDto {

	private final long id;
	private final String name;
	private final String description;
	private final int order;

}