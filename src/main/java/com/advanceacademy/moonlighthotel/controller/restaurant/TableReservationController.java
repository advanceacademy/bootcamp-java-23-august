package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.converter.restaurant.TableReservationConverter;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationRequest;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationResponse;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.TableReservationService;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/user/table-reservation/create")
    public ResponseEntity<?> createTableReservation(@RequestBody TableReservationRequest request) {
        Integer tableNumber = request.getTableNumber();
        LocalTime requestedHour = request.getHour();
        TableRestaurant table = tableRestaurantService.getTableByNumber(tableNumber);

        LocalDate currentDate = LocalDate.now();

        if (request.getDate().isBefore(currentDate)) {
            return ResponseEntity.badRequest().body("The date should be in the present or future.");
        }

        if (table != null) {
            // Check if the number of people doesn't exceed the table's capacity
            if (tableRestaurantService.isNumberOfPeopleOk(request.getNumberOfPeople(), table.getSeats())) {
                // Find all existing reservations for the same table and date
                List<TableReservation> existingReservations = tableReservationService.findReservationsByTableAndDate(table, request.getDate());

                for (TableReservation existingReservation : existingReservations) {
                    LocalTime existingStartTime = existingReservation.getHour();
                    LocalTime existingEndTime = existingReservation.getHour().plusHours(1);

                    if (requestedHour.isAfter(existingStartTime) && requestedHour.isBefore(existingEndTime)) {
                        // There is a time conflict with an existing reservation
                        return ResponseEntity.badRequest().body("The table is not available at the requested time. Please choose a different time.");
                    }
                }

                // Proceed with creating the reservation
                TableReservation reservation = tableReservationConverter.toTableReservation(request);
                reservation.setUser(userService.findByEmail(userService.getAuthUserEmail()));

                TableReservation savedReservation = tableReservationService.createReservation(reservation);

                TableReservationResponse response = tableReservationConverter.toTableReservationResponse(savedReservation);

                return ResponseEntity.ok(response);
            } else {
                // Return a 404 Not Found if the specified table doesn't exist
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid table number.");
        }
    }

    @GetMapping(path = "/admin/get-all-table-reservations")
    public ResponseEntity<?> getAllTableReservations(){
        List<TableReservation> tableReservations = tableReservationService.getAllTableReservations();
        if(tableReservations.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No table reservations found.");
        }
        List<TableReservationResponse> response = tableReservations.stream().map(tableReservationConverter::toTableReservationResponse).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }



//    @PostMapping("/user/table-reservation/create")
//    public ResponseEntity<?> createTableReservation(@RequestBody TableReservationRequest request) {
//        Integer tableNumber = request.getTableNumber();
//        LocalTime requestedHour = request.getHour();
//        TableRestaurant table = tableRestaurantService.getTableByNumber(tableNumber);
//
//        LocalDate currentDate = LocalDate.now();
//
//        if (request.getDate().isBefore(currentDate)) {
//            return ResponseEntity.badRequest().body("The date should be in the present or future.");
//        }
//
//        if (table != null) {
//            // Check if the number of people doesn't exceed the table's capacity
//            if (tableRestaurantService.isNumberOfPeopleOk(request.getNumberOfPeople(), table.getSeats())) {
//                // Find all existing reservations for the same table and date
//                List<TableReservation> existingReservations = tableReservationService.findReservationsByTableAndDate(table, request.getDate());
//
//                if (!existingReservations.isEmpty()) {
//                    for (TableReservation existingReservation : existingReservations) {
//                        LocalTime endTimeOfExistingReservation = existingReservation.getHour().plusHours(1); // Add 1 hour to the end time
//                        if (requestedHour.isBefore(endTimeOfExistingReservation)) {
//                            return ResponseEntity.badRequest().body("The table is not available at the requested time. Please choose a later time.");
//                        }
//                    }
//                }
//
//                // Proceed with creating the reservation
//                TableReservation reservation = tableReservationConverter.toTableReservation(request);
//                reservation.setUser(userService.findByEmail(userService.getAuthUserEmail()));
//
//                TableReservation savedReservation = tableReservationService.createReservation(reservation);
//
//                TableReservationResponse response = tableReservationConverter.toTableReservationResponse(savedReservation);
//
//                return ResponseEntity.ok(response);
//            } else {
//                // Return a 404 Not Found if the specified table doesn't exist
//                return ResponseEntity.notFound().build();
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Invalid table number.");
//        }
//    }
}
