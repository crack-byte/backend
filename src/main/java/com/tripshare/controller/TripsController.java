package com.tripshare.controller;

import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.trips.TripDTO;
import com.tripshare.dto.trips.TripRequestDTO;
import com.tripshare.entity.CustomUserDetails;
import com.tripshare.service.TripService;
import com.tripshare.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/trips")
public class TripsController {
    private final TripService tripService;

    @GetMapping
    public ResponseEntity<Response<PaginationDTO<TripDTO>>> getTrips(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Response<PaginationDTO<TripDTO>> response = new Response<>();
        response.setData(tripService.getTrips(userDetails.getAccount().getId()));
        response.setStatus("success");
        return response.entity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> createTrip(@RequestBody TripRequestDTO tripRequest,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        Response<Object> response = new Response<>();
        response.setData(null);
        try {
            tripService.createTrip(tripRequest, userDetails.getAccount());
            response.setMessage("Trip created");
            response.setStatus("success");
            return response.entity(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Failed to create trip");
            response.setStatus("failed");
            return response.entity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
