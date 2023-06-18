package com.tripshare.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 3000843363273121778L;
    @Id
    @TableGenerator
            (
                    name = "role_gen",
                    table = "role_id",
                    pkColumnName = "seq_name",
                    valueColumnName = "seq_number",
                    pkColumnValue = "role",
                    initialValue = 1000,
                    allocationSize = 1
            )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "role_gen")
    private long id;
    private String name;
    private String description;
    private int priority;
    private boolean active;

}
