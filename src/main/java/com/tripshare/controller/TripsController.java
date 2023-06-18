package com.tripshare.controller;

import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.trips.TripDTO;
import com.tripshare.dto.trips.TripRequestDTO;
import com.tripshare.entity.CustomUserDetails;
import com.tripshare.service.TripService;
import com.tripshare.util.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/trips")
public class TripsController {
    private static final Logger log= LoggerFactory.getLogger(TripsController.class);
    private final TripService tripService;

    @GetMapping
    public ResponseEntity<Response<PaginationDTO<TripDTO>>> getTrips(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Response<PaginationDTO<TripDTO>> response = new Response<>();
        response.setData(tripService.getTrips(userDetails.getAccount().getId()));
        response.setStatus("success");
        return response.entity(HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Response<TripDTO>> getTrip(@PathVariable long tripId) {

        Response<TripDTO> response = new Response<>();
        response.setData(tripService.getTrip(tripId));
        response.setStatus("success");
        return response.entity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> createTrip(@RequestBody TripRequestDTO tripRequest,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        Response<Object> response = new Response<>();
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

    @PutMapping("/{tripId}/status/{status}")
    public ResponseEntity<Response<Object>> startOrStopTrip(@PathVariable String status,@PathVariable long tripId){
        Response<Object> response = new Response<>();
        try {
            tripService.startOrStopTrip(tripId, status);
            response.setStatus("success");
            return response.entity(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus("failed");
            return response.entity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
