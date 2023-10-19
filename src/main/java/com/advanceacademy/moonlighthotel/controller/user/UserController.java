package com.advanceacademy.moonlighthotel.controller.user;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/admin/users/search-users")
    @Operation(
            description = "Search for users by specific criteria (Admin only)",
            summary = "Search Users (Admin only)",
            responses = {
                    @ApiResponse(
                            description = "Users retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No users found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized - User does not have admin privileges",
                            responseCode = "401"
                    )
            },
            operationId = "searchUsers",
            tags = {"User"},
            security = @SecurityRequirement(name = "Bearer Token"),
            parameters = {
                    @Parameter(name = "id", description = "User ID", in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
                    @Parameter(name = "email", description = "User Email", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "firstName", description = "User First Name", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "lastName", description = "User Last Name", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
                    @Parameter(name = "phoneNumber", description = "User Phone Number", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value =
                                    "{\n" +
                                            "  \"userId\": \"1\",\n" +
                                            "  \"firstName\": \"Atanas\",\n" +
                                            "  \"lastName\": \"Krastev\",\n" +
                                            "  \"email\": \"atanas.krastev@abv.bg\",\n" +
                                            "  \"phoneNumber\": \"+35923232354\",\n" +
                                            "  \"createdDate\": \"2023-10-16\",\n" +
                                            "  \"userRoleId\": \"2\"\n" +
                                            "}"
                            )
                    )
            )
    )
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam(required = false) Long id,
                                                          @RequestParam(required = false) String email,
                                                          @RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName,
                                                          @RequestParam(required = false) String phoneNumber){
        List<User> users = userService.searchUsers(id, email, firstName, lastName, phoneNumber);
        List<UserResponse> userResponses = users.stream()
                .map(userConverter::toUserResponse)
                .collect(Collectors.toList());

        if (userResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponses);
    }

}
