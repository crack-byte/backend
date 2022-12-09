package com.tripshare.entity;

import com.tripshare.util.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Trip extends BaseEntity {

	private static final long serialVersionUID = -2401823545005085832L;

	private String name;
	private String uuid;
	private String description;
	private String category;
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startDate;
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endDate;
	@OneToMany(
			mappedBy = "trip"
	)
	private List<TripUser> tripUsers;

}
