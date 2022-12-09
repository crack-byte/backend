package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class TripUser extends BaseEntity {

	@ManyToOne(
			fetch = FetchType.LAZY
	)
	private Trip trip;
	@ManyToOne(
			fetch = FetchType.LAZY
	)
	private User user;
	private double contribution;
	private double amountPaid;

}
