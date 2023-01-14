package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 3000843363273121778L;
	private String name;
	private String description;
	private int priority;
	private boolean active;

}
