package com.advanceacademy.moonlighthotel.dto.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactUsFormResponse {

    private String userName;

    private String userEmail;

    private String userPhone;

    private String userMessage;
}
