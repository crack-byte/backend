package com.tripshare.controller;

import com.tripshare.dto.FilterDTO;
import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.UserDTO;
import com.tripshare.service.UserService;
import com.tripshare.util.Response;
import com.tripshare.util.Util;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        Response<UserDTO> response = new Response<>();
        response.setStatus("success");
        response.setMessage(messageSource.getMessage("success.message", null, Locale.ENGLISH));
        response.setData(userService.createUser(userDTO));
        return response.entity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<PaginationDTO<UserDTO>>> listAllUsers(FilterDTO filterDTO) {
        log.info("listing users:[{}]",filterDTO);
        return Util.generateResponse(userService.findAll(filterDTO.getLimit(), filterDTO.getPage()),
            messageSource.getMessage("success.message", null, Locale.ENGLISH),
            "success", HttpStatus.OK);
    }

}
