package com.advanceacademy.moonlighthotel.controller.contact;


import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.service.contact.ContactUsFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact-us-form")
public class ContactUsFormController {
    @Autowired
    private ContactUsFormService contactUsFormService;


    @PostMapping(path = "/register")
    public ResponseEntity<ContactUsFormResponse> registerContactUsForm(@Valid @RequestBody ContactUsFormRegisterRequest contactUsFormRegisterRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactUsFormService.saveContactUsForm(contactUsFormRegisterRequest));
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<ContactUsFormResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getAllContactUsForms());
    }

    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<ContactUsFormResponse> getContactUsFormById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormById(id));
    }

    @GetMapping(path = "/get-by-user-name")
    public ResponseEntity<Object> getContactUsFormByUserName(@RequestParam ("user_name") String userName) {
        if(contactUsFormService.getContactUsFormByUserName(userName).size() == 0) {
            return ResponseEntity.  status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user name %s.", userName.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByUserName(userName));
    }

    @GetMapping(path = "/get-by-email")
    public ResponseEntity<Object> getContactUsFormByUserEmail(@RequestParam ("user_email") String userEmail) {
        if(contactUsFormService.getContactUsFormByEmail(userEmail).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user email %s.", userEmail.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByEmail(userEmail));
    }

    @GetMapping(path = "/get-by-phone")
    public ResponseEntity<Object> getContactUsFormByUserPhone(@RequestParam ("user_phone") String userPhone) {
        if(contactUsFormService.getContactUsFormByPhone(userPhone).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user phone %s.", userPhone.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByPhone(userPhone));
    }

    @GetMapping(path = "/get-by-message-content")
    public ResponseEntity<Object> getContactUsFormByMessageContaining(@RequestParam ("searched_text") String message) {
        if(contactUsFormService.getContactUsFormByMessageContaining(message).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form with message containing \"%s\".", message));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByMessageContaining(message));
    }

    @DeleteMapping(path = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteContactUsFormById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactUsFormService.deleteContactUsFormById(id));
    }
}
