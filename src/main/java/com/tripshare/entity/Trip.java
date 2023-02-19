package com.tripshare.entity;

import com.tripshare.dto.trips.TripRequestDTO;
import com.tripshare.util.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Trip extends BaseEntity {

    private static final long serialVersionUID = -2401823545005085832L;

    private String name;
    private String uuid;
    private String description;
    private String category;
    private String status;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDate;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDate;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "trip_participants",
            joinColumns = {@JoinColumn(name = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<Account> participants;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Account organizer;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Expense> expenses;

    public Trip() {
        this.uuid = UUID.randomUUID().toString();
        this.participants = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public Trip(TripRequestDTO tripRequest) {
        this();
        this.name = tripRequest.getName();
        this.description = tripRequest.getDescription();
        this.category = tripRequest.getCategory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.startDate = LocalDateTime.parse(tripRequest.getStartDate(), formatter);
        this.endDate = LocalDateTime.parse(tripRequest.getEndDate(), formatter);

    }
}
