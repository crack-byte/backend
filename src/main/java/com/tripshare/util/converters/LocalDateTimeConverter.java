package com.tripshare.util.converters;

import org.springframework.lang.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Converter(
    autoApply = true
)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }
        if (date instanceof Timestamp) {
            return ((Timestamp) date).toLocalDateTime();
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
