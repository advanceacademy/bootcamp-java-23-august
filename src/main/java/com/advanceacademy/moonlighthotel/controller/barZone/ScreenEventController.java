package com.advanceacademy.moonlighthotel.controller.barZone;

import com.advanceacademy.moonlighthotel.converter.barZone.ScreenEventConverter;
import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventDTO;
import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventResponse;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ScreenEventController {
    private final ScreenEventService screenEventService;
    private final ScreenEventConverter screenEventConverter;

    @Autowired
    public ScreenEventController(ScreenEventService screenEventService, ScreenEventConverter screenEventConverter) {
        this.screenEventService = screenEventService;
        this.screenEventConverter = screenEventConverter;
    }

    @PostMapping("/admin/screen-events/create")
    @Operation(
            description = "Create a screen event (Admin only)",
            summary = "Create a Screen Event (Admin only)",
            responses = {
                    @ApiResponse(
                            description = "Screen event created successfully",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ScreenEvent.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request - Maximum events for the day reached or duplicate record",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized - User does not have admin privileges",
                            responseCode = "401"
                    )
            },
            operationId = "createScreenEvent",
            tags = {"Screen Event"},
            security = @SecurityRequirement(name = "Bearer Token"),
            parameters = {
                    @Parameter(
                            name = "eventDTO",
                            description = "Screen Event data to create",
                            required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ScreenEventDTO.class),
                                    examples = @ExampleObject(
                                            name = "Example Request Body",
                                            value = "{" +
                                                    "  \"event\": \"League of Legends Tournament 2023\"," +
                                                    "  \"eventDate\": \"2023-10-18\"," +
                                                    "  \"screenId\": 1" +
                                            "}"
                                    )
                            )
                    )
            }
    )
    @PreAuthorize("hasRole('ADMIN')") // Only administrators can access this endpoint
    public ResponseEntity<ScreenEvent> createScreenEvent(@RequestBody ScreenEventDTO eventDTO) {
        try {
            // Check if the maximum number of events for the day has been reached
            LocalDate eventDate = eventDTO.getEventDate();
            long eventCount = screenEventService.countScreenEventsByEventDate(eventDate);

            if (eventCount < 3) {
                ScreenEvent createdEvent = screenEventService.createScreenEventWithCheck(eventDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (DuplicateRecordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("auth/screen-events/by-date/{eventDate}")
    @Operation(
            description = "Get all screen events by date",
            summary = "Retrieve All Screen Events by Date",
            responses = {
                    @ApiResponse(
                            description = "Screen events retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ScreenEventResponse.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No screen events found for the given date",
                            responseCode = "404"
                    )
            },
            operationId = "getAllScreenEventsByDate",
            tags = {"Screen Event"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<ScreenEventResponse>> getAllScreenEventsByDate (@PathVariable LocalDate eventDate){
        List<ScreenEvent> foundEvents = screenEventService.getByEventsDate(eventDate);

        if (foundEvents.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<ScreenEventResponse> screenEventResponses = foundEvents.stream()
                .map(screenEventConverter::response)
                .collect(Collectors.toList());
        return ResponseEntity.ok(screenEventResponses);
    }

    @GetMapping("auth/screen-events/by-name/{eventName}")
    @Operation(
            description = "Get a screen event by name",
            summary = "Retrieve a Screen Event by Name",
            responses = {
                    @ApiResponse(
                            description = "Screen event retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ScreenEventResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - Screen event with the given name not found",
                            responseCode = "404"
                    )
            },
            operationId = "getScreenEventByName",
            tags = {"Screen Event"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<ScreenEventResponse> getScreenEventByName (@PathVariable String eventName){
        ScreenEvent foundEvent = screenEventService.getEventByName(eventName);
        ScreenEventResponse response = screenEventConverter.response(foundEvent);
        return ResponseEntity.ok(response);
    }

    @GetMapping("auth/screen-events/by-id/{requestId}")
    @Operation(
            description = "Get a screen event by ID",
            summary = "Retrieve a Screen Event by ID",
            responses = {
                    @ApiResponse(
                            description = "Screen event retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ScreenEventResponse.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - Screen event with the given ID not found",
                            responseCode = "404"
                    )
            },
            operationId = "getScreenEventById",
            tags = {"Screen Event"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<ScreenEventResponse> getScreenEventById (@PathVariable Long requestId){
        ScreenEvent foundEvent = screenEventService.getScreenEventById(requestId);
        ScreenEventResponse response = screenEventConverter.response(foundEvent);
        return ResponseEntity.ok(response);
    }
}
