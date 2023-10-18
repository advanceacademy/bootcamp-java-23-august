package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant-bar-seat")
public class SeatRestaurantController{
    @Autowired
    private SeatRestaurantService seatRestaurantService;

    @GetMapping(path = "/get-by-id/{id}")
    @Operation(
            description = "Get a restaurant or bar seat by ID",
            summary = "Retrieve Restaurant or Bar Seat by ID",
            responses = {
                    @ApiResponse(
                            description = "Restaurant or bar seat retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SeatRestaurant.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant or bar seat found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the restaurant or bar seat to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getRestaurantBarSeatById",
            tags = {"Restaurant/Bar Seat"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<SeatRestaurant> getRestaurantBarSeatById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(seatRestaurantService.getSeatById(id));
    }

    @GetMapping(path = "/get-by-number/{number}")
    @Operation(
            description = "Get a restaurant or bar seat by seat number",
            summary = "Retrieve Restaurant or Bar Seat by Seat Number",
            responses = {
                    @ApiResponse(
                            description = "Restaurant or bar seat retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SeatRestaurant.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant or bar seat found for the specified seat number",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "number",
                            description = "The seat number to retrieve a restaurant or bar seat by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getRestaurantBarSeatByNumber",
            tags = {"Restaurant/Bar Seat"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<SeatRestaurant> getRestaurantBarSeatByNumber(@PathVariable Integer number){
        return ResponseEntity.status(HttpStatus.FOUND).body(seatRestaurantService.getSeatByNumber(number));
    }
}
