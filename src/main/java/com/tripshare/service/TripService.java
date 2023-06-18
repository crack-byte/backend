package com.tripshare.service;

import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.trips.TripDTO;
import com.tripshare.dto.trips.TripRequestDTO;
import com.tripshare.entity.Account;
import com.tripshare.entity.Trip;
import com.tripshare.repository.TripRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TripService {
    private static final Logger log = LoggerFactory.getLogger(TripService.class);
    private final TripRepository tripRepository;
    private final UserService userService;

    @Transactional
    public void createTrip(TripRequestDTO tripDTO, Account organizer) {
        log.info("Creating trip: {}", tripDTO);
        Trip trip = new Trip(tripDTO);
        List<Long> participantsIds = tripDTO.getParticipantIds();
        List<Account> participants = userService.findAllById(participantsIds);
        trip.setParticipants(participants);
        trip.setOrganizer(organizer);
        tripRepository.save(trip);
    }

    public void startOrStopTrip(long tripId, String status) {
        tripRepository.findById(tripId).ifPresent(trip -> {
            if (status.equalsIgnoreCase("start")) {
                trip.setStatus(status);
                trip.setActualStartDate(LocalDateTime.now());
            } else if (trip.getActualStartDate() != null && status.equalsIgnoreCase("end")) {
                trip.setStatus(status);
                trip.setActualEndDate(LocalDateTime.now());
            }
            tripRepository.save(trip);
        });
    }

    public PaginationDTO<TripDTO> getTrips(long userId) {
        log.info("Getting trips for user: {}", userId);
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by("id").descending());
        Page<Trip> tripPage = tripRepository.findAll(filterTripsSpecification(userId), pageRequest);
        PaginationDTO<TripDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setList(tripPage.map(TripDTO::new).getContent());
        paginationDTO.setPage(tripPage.getNumber());
        paginationDTO.setTotal(tripPage.getTotalElements());
        paginationDTO.setTotalPages(tripPage.getTotalPages());
        return paginationDTO;
    }

    public TripDTO getTrip(long tripId) {
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by("id").descending());
        Trip tripPage = tripRepository.findById(tripId).orElse(null);
        if(tripPage!=null){
            return new TripDTO(tripPage);
        }
        return null;
    }

    private Specification<Trip> filterTripsSpecification(long userId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate organizerPredicate = criteriaBuilder.equal(root.get("organizer").get("id"), userId);
            Predicate participantPredicate = criteriaBuilder.isMember(userId, root.get("participants"));
            predicates.add(criteriaBuilder.or(organizerPredicate, participantPredicate));
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
