package com.tripshare.dto.trips;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tripshare.dto.UserDTO;
import com.tripshare.entity.Trip;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class TripDTO implements Serializable {

    private static final long serialVersionUID = -2401823545005085832L;

    private String name;
    private String uuid;
    private String description;
    private String category;
    private String startDate;
    private String endDate;
    private UserDTO organizer;
    private String status;
    private List<UserDTO> participants;

    public TripDTO(Trip trip) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.name = trip.getName();
        this.uuid = trip.getUuid();
        this.description = trip.getDescription();
        this.category = trip.getCategory();
        this.startDate = formatter.format(trip.getStartDate());
        this.endDate = formatter.format(trip.getEndDate());
        this.organizer = new UserDTO(trip.getOrganizer());
        this.status = trip.getStatus();
        this.participants = trip.getParticipants().stream().map(UserDTO::new).collect(Collectors.toList());
    }

}
