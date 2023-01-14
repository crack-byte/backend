package com.tripshare.dto;

import lombok.Data;

/**
 * A DTO for the {@link com.tripshare.entity.Role} entity
 */
@Data
public class RoleDto {

    private final long id;
    private final String name;
    private final String description;
    private final int order;

}