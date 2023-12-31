package com.advanceacademy.moonlighthotel.service.contact.impl;

import com.advanceacademy.moonlighthotel.converter.contact.ContactUsFormConverter;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import com.advanceacademy.moonlighthotel.exception.*;
import com.advanceacademy.moonlighthotel.repository.ContactUsFormRepository;
import com.advanceacademy.moonlighthotel.service.contact.ContactUsFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactUsFormServiceImpl implements ContactUsFormService {

    private final ContactUsFormConverter contactUsFormConverter;
    private final ContactUsFormRepository contactUsFormRepository;
    @Autowired
    public ContactUsFormServiceImpl(ContactUsFormConverter contactUsFormConverter, ContactUsFormRepository contactUsFormRepository) {
        this.contactUsFormConverter = contactUsFormConverter;
        this.contactUsFormRepository = contactUsFormRepository;

    }

    @Override
    public ContactUsForm saveContactUsForm(ContactUsFormRegisterRequest contactUsFormRegisterRequest) {
        ContactUsForm savedContactUsForm = contactUsFormConverter.toContactUsForm(contactUsFormRegisterRequest);
        return contactUsFormRepository.save(savedContactUsForm);
    }

    @Override
    public List<ContactUsFormResponse> getAllContactUsForms() {
        List<ContactUsForm> contactUsForms = contactUsFormRepository.findAll();
        return contactUsForms.stream().map(contactUsFormConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public ContactUsFormResponse getContactUsFormById(Long id) {
        ContactUsForm contactUsForm = contactUsFormRepository.findById(id)
                .orElseThrow(() -> new ContactUsFormNotFoundException(String.format("There is no contact-us form matching id %s.", id)));
        return contactUsFormConverter.toResponse(contactUsForm);
    }

    @Override
    public List<ContactUsFormResponse> getContactUsFormByUserName(String userName) {
        List<ContactUsForm> contactUsForms = contactUsFormRepository.findByUserName(userName).get();
        return contactUsForms.stream().map(contactUsFormConverter::toResponse).collect(Collectors.toList());

    }

    @Override
    public List<ContactUsFormResponse> getContactUsFormByEmail(String userEmail) {
        List<ContactUsForm> contactUsForms = contactUsFormRepository.findByUserEmail(userEmail).get();
        return contactUsForms.stream().map(contactUsFormConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ContactUsFormResponse> getContactUsFormByPhone(String userPhone) {
        List<ContactUsForm> contactUsForms = contactUsFormRepository.findByUserPhone(userPhone).get();
        return contactUsForms.stream().map(contactUsFormConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ContactUsFormResponse> getContactUsFormByMessageContaining(String message) {
        List<ContactUsForm> contactUsForms = contactUsFormRepository.findByMessageContaining(message).get();
        return contactUsForms.stream().map(contactUsFormConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public String deleteContactUsFormById(Long id) {
        String deletionMessage;
        if(contactUsFormRepository.existsById(id)){
            contactUsFormRepository.deleteById(id);
            deletionMessage = String.format("Contact-us form with id %s was deleted.", id);
        }
        else {
            deletionMessage = String.format("There is no contact-us form matching id %s", id);
        }

        return deletionMessage;

    }
}
