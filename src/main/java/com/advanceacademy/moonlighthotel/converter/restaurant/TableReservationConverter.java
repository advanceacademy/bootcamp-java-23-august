package com.advanceacademy.moonlighthotel.converter.restaurant;

import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationRequest;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationResponse;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class TableReservationConverter {

    private final TableRestaurantService tableRestaurantService;
    private final UserService userService;
    public String authUserEmail;

    public TableReservationConverter(TableRestaurantService tableRestaurantService, UserService userService) {
        this.tableRestaurantService = tableRestaurantService;
        this.userService = userService;
    }

    public TableReservation toTableReservation(TableReservationRequest reservationRequest){

        TableRestaurant foundTable = tableRestaurantService.getTableByNumber(reservationRequest.getTableNumber());

        String authUserEmail = userService.getAuthUserEmail();
        User authUser = userService.findByEmail(authUserEmail);

        return TableReservation.builder()
                .date(reservationRequest.getDate())
                .hour(reservationRequest.getHour())
                .price(reservationRequest.getNumberOfPeople()*10.00)
                .zone(foundTable.getZone())
                .isSmoking(reservationRequest.getIsSmoking())
                .table(tableRestaurantService.getTableByNumber(reservationRequest.getTableNumber()))
                .countPeople(reservationRequest.getNumberOfPeople())
                .user(authUser)
                .build();
    }

    public TableReservationResponse toTableReservationResponse(TableReservation reservation){

//        authUserEmail = userService.getAuthUserEmail();
//        User authUser = userService.findByEmail(authUserEmail);
        User foundUserByEmail = userService.findByEmail(reservation.getUser().getEmail());

        TableRestaurant reservedTableNumber = tableRestaurantService.getTableByNumber(reservation.getTable().getNumber());

        return TableReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .hour(reservation.getHour())
                .isSmoking(reservedTableNumber.getIsSmoking())
                .zone(reservation.getZone())
                .price(reservation.getPrice())
                .tableNumber(reservedTableNumber)
                .numberOfPeople(reservation.getCountPeople())
                .user(foundUserByEmail)
                .build();
    }
}
