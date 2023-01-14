package com.tripshare.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
        @JsonAlias("participant_ids")
        private List<Long> participantIds;
        private List<UserDTO> participants;

}
