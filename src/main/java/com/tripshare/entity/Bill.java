package com.tripshare.entity;

import com.tripshare.util.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Bill extends BaseEntity {

    private static final long serialVersionUID = -5701334493780053404L;

    private double amount;

    private double contribution;

    private boolean paid;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime paymentDate;

    public Bill() {
        this.paid = true;
    }

}
