package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
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
    public ResponseEntity<TableRestaurant> getRestaurantTableById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTableById(id));
    }

    @GetMapping(path = "/get-by-number/{number}")
    public ResponseEntity<TableRestaurant> getRestaurantTableByNumber(@PathVariable Integer number){
        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTableByNumber(number));
    }

    @GetMapping(path = "/get-by-zone")
    public ResponseEntity<Object> getRestaurantTablesByZone(@RequestParam("restaurant_zone") RestaurantZone restaurantZone){
        if(tableRestaurantService.getTablesByZone(restaurantZone).size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There are no restaurant tables matching restaurant zone %s.", restaurantZone));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTablesByZone(restaurantZone));
    }

    @GetMapping(path = "/get-by-smoking")
    public ResponseEntity<Object> getRestaurantTablesBySmoking(@RequestParam ("is_smoking") Boolean isSmoking){
        if(tableRestaurantService.getSmokingTables(isSmoking).size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isSmoking ? "There are no smoking tables." : "There are no non-smoking tables.");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getSmokingTables(isSmoking));
    }

    @GetMapping(path = "/get-by-number-of-seats")
    public ResponseEntity<Object> getRestaurantTablesByNumberOfSeats(@RequestParam("number_of_seats") Integer seats) {
        if (tableRestaurantService.getTablesBySeats(seats).size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There are no restaurant tables with %s seats.", seats));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(tableRestaurantService.getTablesBySeats(seats));

    }
}
