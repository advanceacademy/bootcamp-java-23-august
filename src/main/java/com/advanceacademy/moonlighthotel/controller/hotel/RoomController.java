package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    @Operation(
            description = "Get all rooms",
            summary = "Retrieve All Rooms",
            responses = {
                    @ApiResponse(
                            description = "Rooms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Room.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No rooms found",
                            responseCode = "404"
                    )
            },
            operationId = "getAllRooms",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get a room by ID",
            summary = "Retrieve Room by ID",
            responses = {
                    @ApiResponse(
                            description = "Room retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Room.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No room found for the specified ID",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the room to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "getRoomById",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-room-type/{roomType}")
    @Operation(
            description = "Get rooms by room type",
            summary = "Retrieve Rooms by Room Type",
            responses = {
                    @ApiResponse(
                            description = "Rooms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Room.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No rooms found for the specified room type",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "roomType",
                            description = "The room type to filter rooms by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getRoomsByRoomType",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable RoomType roomType) {
        List<Room> rooms = roomService.getRoomsByRoomType(roomType);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-room-number/{roomNumber}")
    @Operation(
            description = "Get a room by room number",
            summary = "Retrieve Room by Room Number",
            responses = {
                    @ApiResponse(
                            description = "Room retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Room.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No room found for the specified room number",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "roomNumber",
                            description = "The room number to retrieve a room by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getRoomByRoomNumber",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<Room> getRoomByRoomNumber(@PathVariable Integer roomNumber) {
        Room room = roomService.getRoomByRoomNumber(roomNumber);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/by-room-view/{roomView}")
    @Operation(
            description = "Get rooms by room view",
            summary = "Retrieve Rooms by Room View",
            responses = {
                    @ApiResponse(
                            description = "Rooms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Room.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No rooms found for the specified room view",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "roomView",
                            description = "The room view to filter rooms by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string")
                    )
            },
            operationId = "getRoomsByRoomView",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<Room>> getRoomsByRoomView(@PathVariable RoomView roomView) {
        List<Room> rooms = roomService.getRoomsByRoomView(roomView);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-room-price/{price}")
    @Operation(
            description = "Get rooms by room price",
            summary = "Retrieve Rooms by Room Price",
            responses = {
                    @ApiResponse(
                            description = "Rooms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Room.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No rooms found for the specified room price",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "price",
                            description = "The room price to filter rooms by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "number", format = "double")
                    )
            },
            operationId = "getRoomsByPrice",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<List<Room>> getRoomsByPrice(@PathVariable Double price) {
        List<Room> rooms = roomService.getRoomsByPrice(price);
        return ResponseEntity.ok(rooms);
    }


    @GetMapping("/by-max-people/{maxPeople}")
    @Operation(
            description = "Get rooms by maximum capacity",
            summary = "Retrieve Rooms by Maximum Capacity",
            responses = {
                    @ApiResponse(
                            description = "Rooms retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Room.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid maximum capacity",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Not Found - No rooms found for the specified maximum capacity",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "maxPeople",
                            description = "The maximum capacity to filter rooms by",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer")
                    )
            },
            operationId = "getRoomsByMaxPeople",
            tags = {"Room"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<?> getRoomsByMaxPeople(@PathVariable Integer maxPeople) {
        List<Integer> validMaxPeopleValues = Arrays.asList(2, 3, 4);

        if (!validMaxPeopleValues.contains(maxPeople)) {
            return ResponseEntity.badRequest().body( "No available rooms ! There are no existing rooms of such capacity in the hotel!");
        }

        List<Room> rooms = roomService.getRoomsByMaxPeople(maxPeople);

        return ResponseEntity.ok(rooms);


   }
}




