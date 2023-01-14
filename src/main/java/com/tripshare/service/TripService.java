package com.tripshare.service;

import com.tripshare.dto.TripDTO;
import com.tripshare.entity.Trip;
import com.tripshare.entity.User;
import com.tripshare.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private static final Logger log = LoggerFactory.getLogger(TripService.class);
    private final TripRepository tripRepository;
    private final UserService userService;

    @Transactional
    public void createTrip(TripDTO tripDTO, User organizer) {
        log.info("Creating trip: {}", tripDTO);
        Trip trip = new Trip(tripDTO);
        List<Long> participantsIds = tripDTO.getParticipantIds();
        List<User> participants = userService.findAllById(participantsIds);
        trip.setParticipants(participants);
        trip.setOrganizer(organizer);
        tripRepository.save(trip);
    }


}
