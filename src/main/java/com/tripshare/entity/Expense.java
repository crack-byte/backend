package com.tripshare.entity;

import com.tripshare.util.converters.ListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseEntity {

    private static final long serialVersionUID = -5701334493780053404L;

    private double amount;
    private LocalDateTime billDate;
    private String description;
    private String billType;
    @Convert(converter = ListConverter.class)
    private List<String> tags;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Account createdBy;

    public Expense() {
        this.amount = 0.0;
        this.billDate = LocalDateTime.now();
    }

}
