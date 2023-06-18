package com.tripshare.entity;

import com.tripshare.util.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "bill")
public class Bill extends BaseEntity {

    private static final long serialVersionUID = -5701334493780053404L;
    @Id
    @TableGenerator
            (
                    name = "bill_gen",
                    table = "bill_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "bill",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bill_gen")
    private long id;
    private double amount;

    private double contribution;

    private boolean paid;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime paymentDate;

    public Bill() {
        this.paid = true;
    }

}
