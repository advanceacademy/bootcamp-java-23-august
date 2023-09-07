package com.advanceacademy.moonlighthotel.dto.contact;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactUsFormResponse {

    private String userName;

    private String userEmail;

    private String userPhone;

    private String userMessage;
}
