package com.advanceacademy.moonlighthotel.payload.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserInfoResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String roles;


    public UserInfoResponse(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
