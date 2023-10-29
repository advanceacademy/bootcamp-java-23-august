package com.advanceacademy.moonlighthotel.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserInfoResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userRole;

}
