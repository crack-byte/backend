package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class UserPasswordHistory extends BaseEntity {

    private static final long serialVersionUID = -3729291609003879486L;
    private String encryptedPassword;
    private long createdBy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;

}
