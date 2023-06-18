package com.tripshare.dto.trips;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.tripshare.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class TripRequestDTO implements Serializable {
    private static final long serialVersionUID = -3201823545005085832L;

    private String name;
    private String description;
    private String category;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String endDate;
    private UserDTO organizer;
    @JsonAlias({"participant_ids", "participantIds"})
    private List<Long> participantIds;
}
