package com.tripshare.entity;

import com.tripshare.util.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private boolean deleted;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastUpdatedDate;

    @PrePersist
    void prePersist() {
        this.createdDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
