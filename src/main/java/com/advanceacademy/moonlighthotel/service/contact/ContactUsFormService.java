package com.advanceacademy.moonlighthotel.service.contact;

import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.entity.ContactUsForm;

import java.util.List;


public interface ContactUsFormService {

    ContactUsForm saveContactUsForm(ContactUsFormRegisterRequest contactUsFormRegisterRequest);

    List<ContactUsFormResponse> getAllContactUsForms();

    ContactUsFormResponse getContactUsFormById(Long id);

    List<ContactUsFormResponse> getContactUsFormByUserName(String userName);

    List<ContactUsFormResponse> getContactUsFormByEmail(String userEmail);

    List<ContactUsFormResponse> getContactUsFormByPhone(String userPhone);

    List<ContactUsFormResponse> getContactUsFormByMessageContaining(String Message);

    String deleteContactUsFormById(Long id);
}
