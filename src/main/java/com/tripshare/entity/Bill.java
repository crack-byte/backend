package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Bill extends BaseEntity {

	private static final long serialVersionUID = -5701334493780053404L;

	private double amount;
	private LocalDateTime billDate;
	private String description;
	private String billType;
	private String tags;
	private long createdBy;

}
