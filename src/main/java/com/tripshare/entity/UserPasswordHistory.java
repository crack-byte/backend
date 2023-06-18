package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_password_history")
public class UserPasswordHistory extends BaseEntity {

    private static final long serialVersionUID = -3729291609003879486L;
    @Id
    @TableGenerator
            (
                    name = "user_password_history_gen",
                    table = "user_password_history_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "user_password_history",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_password_history_gen")
    private long id;
    private String encryptedPassword;
    private long createdBy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;

}
