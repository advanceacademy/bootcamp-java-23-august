package com.advanceacademy.moonlighthotel.controller.contact;


import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormRegisterRequest;
import com.advanceacademy.moonlighthotel.dto.contact.ContactUsFormResponse;
import com.advanceacademy.moonlighthotel.service.contact.ContactUsFormService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/contact-us-form")
public class ContactUsFormController {
    @Autowired
    private ContactUsFormService contactUsFormService;


    @PostMapping
    @Operation(
            description = "Endpoint to create a contact form",
            summary = "Create Contact Form",
            responses = {
                    @ApiResponse(
                            description = "Contact form created successfully",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid input",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)
                            )
                    )
            },
            operationId = "createContactForm",
            tags = {"Contact Form"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Contact form registration request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ContactUsFormRegisterRequest.class),
                            examples = @ExampleObject(value =
                                            "{\n" +
                                            "  \"userName\": \"Atanas Krastev\",\n" +
                                            "  \"userEmail\": \"nasko@gmail.com\",\n" +
                                            "  \"userPhone\": \"+359 894555666\",\n" +
                                            "  \"userMessage\": \"Your message goes here\"\n" +
                                            "}"
                            )
                    )
            ),
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> registerContactUsForm(@Valid @RequestBody ContactUsFormRegisterRequest contactUsFormRegisterRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactUsFormService.saveContactUsForm(contactUsFormRegisterRequest));
    }

    @GetMapping(path = "/get-all")
    @Operation(
            description = "Endpoint to retrieve all contact forms",
            summary = "Get All Contact Forms",
            responses = {
                    @ApiResponse(
                            description = "Contact forms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ContactUsFormResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact forms found",
                            responseCode = "404"
                    )
            },
            operationId = "getAllContactForms",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<ContactUsFormResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getAllContactUsForms());
    }

    @GetMapping(path = "/get-by-id/{id}")
    @Operation(
            description = "Get a contact form by ID",
            summary = "Retrieve Contact Form by ID",
            responses = {
                    @ApiResponse(
                            description = "Contact form retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ContactUsFormResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - Contact form not found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the contact form to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getContactUsFormById",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<ContactUsFormResponse> getContactUsFormById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormById(id));
    }

    @GetMapping(path = "/get-by-user-name")
    @Operation(
            description = "Get contact forms by user name",
            summary = "Retrieve Contact Forms by User Name",
            responses = {
                    @ApiResponse(
                            description = "Contact forms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ContactUsFormResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact forms found for the specified user name",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "user_name",
                            description = "The user name to search for in contact forms",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getContactUsFormByUserName",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getContactUsFormByUserName(@RequestParam ("user_name") String userName) {
        if(contactUsFormService.getContactUsFormByUserName(userName).size() == 0) {
            return ResponseEntity.  status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user name %s.", userName.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByUserName(userName));
    }

    @GetMapping(path = "/get-by-email")
    @Operation(
            description = "Retrieve contact forms by user name",
            summary = "Get Contact Forms by User Name",
            responses = {
                    @ApiResponse(
                            description = "Contact forms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ContactUsFormResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact forms found for the specified user name",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "user_name",
                            description = "The user name to filter contact forms by",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getContactFormsByUserName",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getContactUsFormByUserEmail(@RequestParam ("user_email") String userEmail) {
        if(contactUsFormService.getContactUsFormByEmail(userEmail).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user email %s.", userEmail.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByEmail(userEmail));
    }

    @GetMapping(path = "/get-by-phone")
    @Operation(
            description = "Retrieve contact forms by user phone",
            summary = "Get Contact Forms by User Phone",
            responses = {
                    @ApiResponse(
                            description = "Contact forms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ContactUsFormResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact forms found for the specified user phone",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "user_phone",
                            description = "The user phone number to filter contact forms by",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getContactFormsByUserPhone",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getContactUsFormByUserPhone(@RequestParam ("user_phone") String userPhone) {
        if(contactUsFormService.getContactUsFormByPhone(userPhone).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form matching user phone %s.", userPhone.trim()));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByPhone(userPhone));
    }

    @GetMapping(path = "/get-by-message-content")
    @Operation(
            description = "Retrieve contact forms by message content",
            summary = "Get Contact Forms by Message Content",
            responses = {
                    @ApiResponse(
                            description = "Contact forms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ContactUsFormResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact forms found with the specified message content",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "searched_text",
                            description = "The text to search for within contact form messages",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getContactFormsByMessageContent",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getContactUsFormByMessageContaining(@RequestParam ("searched_text") String message) {
        if(contactUsFormService.getContactUsFormByMessageContaining(message).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There is no contact-us form with message containing \"%s\".", message));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(contactUsFormService.getContactUsFormByMessageContaining(message));
    }

    @DeleteMapping(path = "/delete-by-id/{id}")
    @Operation(
            description = "Delete contact form by ID",
            summary = "Delete Contact Form by ID",
            responses = {
                    @ApiResponse(
                            description = "Contact form deleted successfully",
                            responseCode = "202"
                    ),
                    @ApiResponse(
                            description = "Not Found - No contact form found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the contact form to delete",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "deleteContactFormById",
            tags = {"Contact Form"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<String> deleteContactUsFormById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contactUsFormService.deleteContactUsFormById(id));
    }
}
