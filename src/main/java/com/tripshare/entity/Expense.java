package com.tripshare.entity;

import com.tripshare.util.converters.ListConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense extends BaseEntity {

    private static final long serialVersionUID = -5701334493780053404L;
    @Id
    @TableGenerator
            (
                    name = "expense_gen",
                    table = "expense_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "expense",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "expense_gen")
    private long id;
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
