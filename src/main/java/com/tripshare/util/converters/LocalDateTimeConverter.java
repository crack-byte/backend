package com.tripshare.util.converters;

import org.springframework.lang.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(
		autoApply = true
)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Long> {

	public LocalDateTimeConverter() {
	}

	@Nullable
	public Long convertToDatabaseColumn(LocalDateTime date) {
		return date == null ? null : ZonedDateTime.of(date, ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	@Nullable
	public LocalDateTime convertToEntityAttribute(Long timeInMillis) {
		return timeInMillis == null ? null : Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}