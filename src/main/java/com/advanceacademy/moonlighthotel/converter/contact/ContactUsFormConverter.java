package com.advanceacademy.moonlighthotel.converter.contact;

import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import org.springframework.stereotype.Component;

@Component
public class ContactUsFormConverter {

        public ContactUsForm toContactUsForm(ContactUsFormRegisterRequest contactUsFormRegisterRequest){
            ContactUsForm contactUsForm = ContactUsForm.builder()
                    .userName(contactUsFormRegisterRequest.getUserName())
                    .userEmail(contactUsFormRegisterRequest.getUserEmail())
                    .userPhone(contactUsFormRegisterRequest.getUserPhone())
                    .userMessage(contactUsFormRegisterRequest.getUserMessage())
                    .build();
            return contactUsForm;
        }

        public ContactUsFormResponse toResponse(ContactUsForm savedContactUsForm){

            ContactUsFormResponse contactUsFormResponse = ContactUsFormResponse.builder()
                    .userName(savedContactUsForm.getUserName())
                    .userEmail(savedContactUsForm.getUserEmail())
                    .userPhone(savedContactUsForm.getUserPhone())
                    .userMessage(savedContactUsForm.getUserMessage())
                    .build();

            return contactUsFormResponse;

        }
}
