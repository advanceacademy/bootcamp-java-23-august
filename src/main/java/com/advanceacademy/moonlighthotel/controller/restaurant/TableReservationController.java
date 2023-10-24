package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.converter.restaurant.TableReservationConverter;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationRequest;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationResponse;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.TableReservationService;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TableReservationController {

    private final TableReservationConverter tableReservationConverter;
    private final TableReservationService tableReservationService;
    private final TableRestaurantService tableRestaurantService;
    private final UserService userService;

    public TableReservationController(TableReservationConverter tableReservationConverter, TableReservationService tableReservationService, TableRestaurantService tableRestaurantService, UserService userService) {
        this.tableReservationConverter = tableReservationConverter;
        this.tableReservationService = tableReservationService;
        this.tableRestaurantService = tableRestaurantService;
        this.userService = userService;
    }

    @PostMapping("/auth/table-reservation/create")
    public ResponseEntity<?> createTableReservation(@RequestBody TableReservationRequest request) {
        Integer tableNumber = request.getTableNumber();
        LocalTime requestedHour = request.getHour();
        TableRestaurant table = tableRestaurantService.getTableByNumber(tableNumber);

        if (table != null) {
            // Check if the number of people doesn't exceed the table's capacity
            if (tableRestaurantService.isNumberOfPeopleOk(request.getNumberOfPeople(), table.getSeats())) {
                // Find all existing reservations for the same table and date
                List<TableReservation> existingReservations = tableReservationService.findReservationsByTableAndDate(table, request.getDate());

                if (existingReservations.isEmpty() || tableReservationService.isRequestedHourValid(requestedHour, existingReservations)) {
                    // If no existing reservations or the requested hour is valid, proceed with creating the reservation
                    TableReservation reservation = tableReservationConverter.toTableReservation(request);
                    reservation.setUser(userService.findByEmail(userService.getAuthUserEmail()));

                    TableReservation savedReservation = tableReservationService.createReservation(reservation);

                    TableReservationResponse response = tableReservationConverter.toTableReservationResponse(savedReservation);

                    return ResponseEntity.ok(response);
                } else {
                    // Return a 400 Bad Request with a custom error message
                    return ResponseEntity.badRequest().body("Hour conflicts with existing reservations for the same table.");
                }
            } else {
                // Return a 400 Bad Request with a custom error message
                return ResponseEntity.badRequest().body("Number of people exceeds the table's capacity.");
            }
        } else {
            // Return a 404 Not Found if the specified table doesn't exist
            return ResponseEntity.notFound().build();
        }
    }
}
