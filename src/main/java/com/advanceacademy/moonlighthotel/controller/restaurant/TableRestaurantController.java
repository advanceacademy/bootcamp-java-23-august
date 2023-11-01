package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.converter.restaurant.TableRestaurantConverter;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableRestaurantResponse;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.TableReservationService;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class TableRestaurantController {

    private final TableRestaurantService tableRestaurantService;
    private final TableReservationService tableReservationService;
    private final TableRestaurantConverter tableRestaurantConverter;

    public TableRestaurantController(TableRestaurantService tableRestaurantService, TableReservationService tableReservationService, TableRestaurantConverter tableRestaurantConverter) {
        this.tableRestaurantService = tableRestaurantService;
        this.tableReservationService = tableReservationService;
        this.tableRestaurantConverter = tableRestaurantConverter;
    }

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

    @GetMapping("/auth/restaurant-table/get-all-available-table")
    @Operation(
            description = "Get all available restaurant tables based on the provided criteria",
            summary = "Retrieve Available Restaurant Tables",
            responses = {
                    @ApiResponse(
                            description = "Available restaurant tables retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TableRestaurantResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid date or time provided",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Not Found - No available restaurant tables found for the specified criteria",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "date",
                            description = "The reservation date (yyyy-MM-dd)",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "date")
                    ),
                    @Parameter(
                            name = "time",
                            description = "The reservation time (HH)",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "time")
                    ),
                    @Parameter(
                            name = "zone",
                            description = "The restaurant zone to filter by",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", allowableValues = {"INDOOR", "OUTDOOR"})
                    ),
                    @Parameter(
                            name = "is_smoking",
                            description = "Specify whether to retrieve smoking or non-smoking tables",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "boolean")
                    ),
                    @Parameter(
                            name = "tableId",
                            description = "The ID of the specific table to retrieve",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", format = "int64")
                    ),
                    @Parameter(
                            name = "people",
                            description = "The number of people for the reservation",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", format = "int32")
                    )
            },
            operationId = "getAllAvailableTables",
            tags = {"Restaurant Table"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<?> getAllAvailableTables(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam @DateTimeFormat(pattern = "HH") LocalTime time,
            @RequestParam(required = false) RestaurantZone zone,
            @RequestParam(required = false) Boolean isSmoking,
            @RequestParam(required = false) Long tableId,
            @RequestParam(required = false) Integer people) {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        if (date.isBefore(currentDate) || (date.isEqual(currentDate) && time.isBefore(currentTime))) {
            return ResponseEntity.badRequest().body("The date should be in the present or future.");
        }

        List<TableReservation> reservationsByDateAndHour = tableReservationService.getReservationsByDateAndHour(date, time);
        List<TableRestaurant> allTables = tableRestaurantService.getAllTables();
        List<TableRestaurant> allAvailableTables = new ArrayList<>();

        // Filter tables that are not reserved for the given date
        for (TableRestaurant table : allTables) {
            // Check if the table is reserved for the given date and time
            boolean isTableAvailable = reservationsByDateAndHour.stream()
                    .noneMatch(reservation -> reservation.getTable().getNumber().equals(table.getNumber()));

            if (isTableAvailable) {
                allAvailableTables.add(table);
            }
        }

        // If zone is specified, filter tables by zone
        if (zone != null) {
            allAvailableTables = allAvailableTables.stream()
                    .filter(table -> table.getZone() == zone)
                    .collect(Collectors.toList());
        }

        // If isSmoking is specified, filter tables by smoking preference
        if (isSmoking != null) {
            allAvailableTables = allAvailableTables.stream()
                    .filter(table -> table.getIsSmoking() == isSmoking)
                    .collect(Collectors.toList());
        }

        // If tableId is specified, filter tables by tableId
        if (tableId != null) {
            allAvailableTables = allAvailableTables.stream()
                    .filter(table -> table.getId().equals(tableId))
                    .collect(Collectors.toList());
        }

        // If people is specified, filter tables by the number of people
        if (people != null) {
            allAvailableTables = allAvailableTables.stream()
                    .filter(table -> tableRestaurantService.isNumberOfPeopleOk(people, table.getSeats()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(allAvailableTables.stream()
                .map(tableRestaurantConverter::toTableRestaurantResponse)
                .collect(Collectors.toList()));
    }
}
