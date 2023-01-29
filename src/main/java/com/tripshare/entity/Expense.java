package com.tripshare.entity;

import com.tripshare.util.converters.ListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseEntity {

    private static final long serialVersionUID = -5701334493780053404L;

    private BigDecimal amount;
    private LocalDateTime billDate;
    private String description;
    private String billType;
    @Convert(converter = ListConverter.class)
    private List<String> tags;
    @ManyToOne
    private Account createdBy;

    public Expense() {
        this.amount = BigDecimal.ZERO;
    }

}
