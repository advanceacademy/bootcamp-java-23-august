package com.advanceacademy.moonlighthotel.service.contact.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.advanceacademy.moonlighthotel.converter.ContactUsFormConverter;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.entity.ContactUsForm;
import com.advanceacademy.moonlighthotel.repository.ContactUsFormRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactUsFormServiceImplTest {
    @Mock
    private ContactUsFormConverter contactUsFormConverter;
    @Mock
    private ContactUsFormRepository contactUsFormRepository;

    @Mock
    private ContactUsFormServiceImpl contactUsFormServiceImpl;

    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        contactUsFormServiceImpl = new ContactUsFormServiceImpl(contactUsFormConverter, contactUsFormRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveContactUsForm() {

        ContactUsFormRegisterRequest contactUsFormRegisterRequest = new ContactUsFormRegisterRequest("Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");

        when(contactUsFormConverter.toContactUsForm(contactUsFormRegisterRequest)).thenReturn(contactUsForm);
        when(contactUsFormRepository.save(contactUsForm)).thenReturn(contactUsForm);
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.saveContactUsForm(contactUsFormRegisterRequest);

        verify(contactUsFormConverter, times(1)).toContactUsForm(contactUsFormRegisterRequest);
        verify(contactUsFormRepository, times(1)).save(contactUsForm);
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);
    }

    @Test
    void getAllContactUsForms() {
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        List<ContactUsForm> contactUsForms = new ArrayList<>();
        contactUsForms.add(contactUsForm);

        when(contactUsFormRepository.findAll()).thenReturn(contactUsForms);
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getAllContactUsForms();

        verify(contactUsFormRepository, times(1)).findAll();
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);
    }

    @Test
    void getContactUsFormById() {

        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        when(contactUsFormRepository.findById(anyLong())).thenReturn(Optional.of(contactUsForm));
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getContactUsFormById(anyLong());

        verify(contactUsFormRepository, times(1)).findById(anyLong());
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);

    }

    @Test
    void getContactUsFormByUserName() {

        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        String userName = contactUsForm.getUserName();

        List<ContactUsForm> contactUsForms = new ArrayList<>();
        contactUsForms.add(contactUsForm);

        when(contactUsFormRepository.findByUserName(userName)).thenReturn(Optional.of(contactUsForms));
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getContactUsFormByUserName(userName);

        verify(contactUsFormRepository, times(1)).findByUserName(userName);
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);

    }

    @Test
    void getContactUsFormByEmail() {
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        String userEmail = contactUsForm.getUserEmail();
        List<ContactUsForm> contactUsForms = new ArrayList<>();
        contactUsForms.add(contactUsForm);

        when(contactUsFormRepository.findByUserEmail(userEmail)).thenReturn(Optional.of(contactUsForms));
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getContactUsFormByEmail(userEmail);

        verify(contactUsFormRepository, times(1)).findByUserEmail(userEmail);
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);
    }

    @Test
    void getContactUsFormByPhone() {
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        String userPhone = contactUsForm.getUserPhone();
        List<ContactUsForm> contactUsForms = new ArrayList<>();
        contactUsForms.add(contactUsForm);

        when(contactUsFormRepository.findByUserPhone(userPhone)).thenReturn(Optional.of(contactUsForms));
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getContactUsFormByPhone(userPhone);

        verify(contactUsFormRepository, times(1)).findByUserPhone(userPhone);
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);
    }

    @Test
    void getContactUsFormByMessageContaining() {
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");
        String message = "";
        String userMessage = contactUsForm.getUserMessage();
        List<ContactUsForm> contactUsForms = new ArrayList<>();
        contactUsForms.add(contactUsForm);

        when(contactUsFormRepository.findByMessageContaining(message)).thenReturn(Optional.of(contactUsForms));
        when(contactUsFormConverter.toResponse(contactUsForm)).thenReturn(new ContactUsFormResponse());

        contactUsFormServiceImpl.getContactUsFormByMessageContaining(message);

        verify(contactUsFormRepository, times(1)).findByMessageContaining(message);
        verify(contactUsFormConverter, times(1)).toResponse(contactUsForm);

    }


    @Test
    void deleteContactUsFormById() {
        Long id = 1L;
        ContactUsForm contactUsForm = new ContactUsForm(1L, "Ivan Ivanov", "00561161566", "user1@domain.bg", "niasnvisvinsinv");

        contactUsFormServiceImpl.deleteContactUsFormById(id);

        assertThat(contactUsFormRepository.existsById(id)).isFalse();

    }

}