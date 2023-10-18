package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant-table")
public class TableRestaurantController {
    @Autowired
    private TableRestaurantService tableRestaurantService;

    @GetMapping(path = "/get-by-id/{id}")
    @Operation(
            description = "Get a restaurant table by ID",
            summary = "Retrieve Restaurant Table by ID",
            responses = {
                    @ApiResponse(
                            description = "Restaurant table retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TableRestaurant.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant table found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the restaurant table to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getRestaurantTableById",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<TableRestaurant> getRestaurantTableById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTableById(id));
    }

    @GetMapping(path = "/get-by-number/{number}")
    @Operation(
            description = "Get a restaurant table by table number",
            summary = "Retrieve Restaurant Table by Table Number",
            responses = {
                    @ApiResponse(
                            description = "Restaurant table retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TableRestaurant.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant table found for the specified table number",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "number",
                            description = "The table number to retrieve a restaurant table by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getRestaurantTableByNumber",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<TableRestaurant> getRestaurantTableByNumber(@PathVariable Integer number){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTableByNumber(number));
    }

    @GetMapping(path = "/get-by-zone")
    @Operation(
            description = "Get restaurant tables by zone",
            summary = "Retrieve Restaurant Tables by Zone",
            responses = {
                    @ApiResponse(
                            description = "Restaurant tables retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TableRestaurant.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant tables found for the specified zone",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "restaurant_zone",
                            description = "The restaurant zone to filter restaurant tables by",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", allowableValues = {"INDOOR", "OUTDOOR"})
                    )
            },
            operationId = "getRestaurantTablesByZone",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getRestaurantTablesByZone(@RequestParam("restaurant_zone") RestaurantZone restaurantZone){
        if(tableRestaurantService.getTablesByZone(restaurantZone).size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There are no restaurant tables matching restaurant zone %s.", restaurantZone));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTablesByZone(restaurantZone));
    }

    @GetMapping(path = "/get-by-smoking")
    @Operation(
            description = "Get restaurant tables by smoking preference",
            summary = "Retrieve Restaurant Tables by Smoking Preference",
            responses = {
                    @ApiResponse(
                            description = "Restaurant tables retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TableRestaurant.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant tables found for the specified smoking preference",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "is_smoking",
                            description = "Specify whether to retrieve smoking or non-smoking tables",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "boolean")
                    )
            },
            operationId = "getRestaurantTablesBySmoking",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getRestaurantTablesBySmoking(@RequestParam ("is_smoking") Boolean isSmoking){
        if(tableRestaurantService.getSmokingTables(isSmoking).size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isSmoking ? "There are no smoking tables." : "There are no non-smoking tables.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getSmokingTables(isSmoking));
    }

    @GetMapping(path = "/get-by-number-of-seats")
    @Operation(
            description = "Get restaurant tables by the number of seats",
            summary = "Retrieve Restaurant Tables by Number of Seats",
            responses = {
                    @ApiResponse(
                            description = "Restaurant tables retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TableRestaurant.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No restaurant tables found for the specified number of seats",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "number_of_seats",
                            description = "The number of seats to filter restaurant tables by",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getRestaurantTablesByNumberOfSeats",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Object> getRestaurantTablesByNumberOfSeats(@RequestParam("number_of_seats") Integer seats) {
        if (tableRestaurantService.getTablesBySeats(seats).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There are no restaurant tables with %s seats.", seats));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTablesBySeats(seats));

    }
}
