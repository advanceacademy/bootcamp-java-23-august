package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.repository.restaurant.SeatRestaurantRepository;
import com.advanceacademy.moonlighthotel.repository.restaurant.TableRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatRestaurantService;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RestaurantCommandRunner implements CommandLineRunner {

    private final TableRestaurantRepository tableRestaurantRepository;
    private final SeatRestaurantRepository seatRestaurantRepository;
    private final TableRestaurantService tableRestaurantService;
    private final SeatRestaurantService seatRestaurantService;
    @Autowired
    public RestaurantCommandRunner(TableRestaurantRepository tableRestaurantRepository, SeatRestaurantRepository seatRestaurantRepository, TableRestaurantService tableRestaurantService, SeatRestaurantService seatRestaurantService) {
        this.tableRestaurantRepository = tableRestaurantRepository;
        this.seatRestaurantRepository = seatRestaurantRepository;
        this.tableRestaurantService = tableRestaurantService;
        this.seatRestaurantService = seatRestaurantService;
    }

    public boolean isTableNumberUnique(TableRestaurant checkedTable){
        return tableRestaurantRepository.findAll().stream().noneMatch(tableRestaurant -> tableRestaurant.getNumber().equals(checkedTable.getNumber()));
    }

    public boolean isSeatNumberUnique(SeatRestaurant checkedSeat){
        return seatRestaurantRepository.findAll().stream().noneMatch(seatRestaurant -> seatRestaurant.getNumber().equals(checkedSeat.getNumber()));
    }

    @Override
    public void run(String... args) throws Exception {

        TableRestaurant table1 = TableRestaurant.builder()
                .number(1)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(4)
                .build();

        TableRestaurant table2 = TableRestaurant.builder()
                .number(2)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(4)
                .build();

        TableRestaurant table3 = TableRestaurant.builder()
                .number(3)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(4)
                .build();

        TableRestaurant table4 = TableRestaurant.builder()
                .number(4)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(4)
                .build();

        TableRestaurant table5 = TableRestaurant.builder()
                .number(5)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(4)
                .build();

        TableRestaurant table6 = TableRestaurant.builder()
                .number(6)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(2)
                .build();

        TableRestaurant table7 = TableRestaurant.builder()
                .number(7)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(7)
                .build();

        TableRestaurant table8 = TableRestaurant.builder()
                .number(8)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(7)
                .build();

        TableRestaurant table9 = TableRestaurant.builder()
                .number(9)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(8)
                .build();

        TableRestaurant table10 = TableRestaurant.builder()
                .number(10)
                .zone(RestaurantZone.SALOON)
                .isSmoking(false)
                .seats(2)
                .build();

        TableRestaurant table11 = TableRestaurant.builder()
                .number(11)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();

        TableRestaurant table12 = TableRestaurant.builder()
                .number(12)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();

        TableRestaurant table13 = TableRestaurant.builder()
                .number(13)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();

        TableRestaurant table14 = TableRestaurant.builder()
                .number(14)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();

        TableRestaurant table15 = TableRestaurant.builder()
                .number(15)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();

        TableRestaurant table16 = TableRestaurant.builder()
                .number(16)
                .zone(RestaurantZone.TERRACE)
                .isSmoking(true)
                .seats(4)
                .build();


        if(isTableNumberUnique(table1)) {
            tableRestaurantService.createTable(table1);
        }
        if(isTableNumberUnique(table2)) {
            tableRestaurantService.createTable(table2);
        }
        if(isTableNumberUnique(table3)) {
            tableRestaurantService.createTable(table3);
        }
        if(isTableNumberUnique(table4)) {
            tableRestaurantService.createTable(table4);
        }
        if(isTableNumberUnique(table5)) {
            tableRestaurantService.createTable(table5);
        }
        if(isTableNumberUnique(table6)) {
            tableRestaurantService.createTable(table6);
        }
        if(isTableNumberUnique(table7)) {
            tableRestaurantService.createTable(table7);
        }
        if(isTableNumberUnique(table8)) {
            tableRestaurantService.createTable(table8);
        }
        if(isTableNumberUnique(table9)) {
            tableRestaurantService.createTable(table9);
        }
        if(isTableNumberUnique(table10)) {
            tableRestaurantService.createTable(table10);
        }
        if(isTableNumberUnique(table11)) {
            tableRestaurantService.createTable(table11);
        }
        if(isTableNumberUnique(table12)) {
            tableRestaurantService.createTable(table12);
        }
        if(isTableNumberUnique(table13)) {
            tableRestaurantService.createTable(table13);
        }
        if(isTableNumberUnique(table14)) {
            tableRestaurantService.createTable(table14);
        }
        if(isTableNumberUnique(table15)) {
            tableRestaurantService.createTable(table15);
        }
        if(isTableNumberUnique(table16)) {
            tableRestaurantService.createTable(table16);
        }



        SeatRestaurant barSeat1 = SeatRestaurant.builder()
                .number(1)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat2 = SeatRestaurant.builder()
                .number(2)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat3 = SeatRestaurant.builder()
                .number(3)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat4 = SeatRestaurant.builder()
                .number(4)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat5 = SeatRestaurant.builder()
                .number(5)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat6 = SeatRestaurant.builder()
                .number(6)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat7 = SeatRestaurant.builder()
                .number(7)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat8 = SeatRestaurant.builder()
                .number(8)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat9 = SeatRestaurant.builder()
                .number(9)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat10 = SeatRestaurant.builder()
                .number(10)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();

        SeatRestaurant barSeat11 = SeatRestaurant.builder()
                .number(11)
                .zone(RestaurantZone.BAR)
                .isSmoking(false)
                .build();


        if(isSeatNumberUnique(barSeat1)){
            seatRestaurantService.createSeat(barSeat1);
        }
        if(isSeatNumberUnique(barSeat2)){
            seatRestaurantService.createSeat(barSeat2);
        }
        if(isSeatNumberUnique(barSeat3)){
            seatRestaurantService.createSeat(barSeat3);
        }
        if(isSeatNumberUnique(barSeat4)){
            seatRestaurantService.createSeat(barSeat4);
        }
        if(isSeatNumberUnique(barSeat5)){
            seatRestaurantService.createSeat(barSeat5);
        }
        if(isSeatNumberUnique(barSeat6)){
            seatRestaurantService.createSeat(barSeat6);
        }
        if(isSeatNumberUnique(barSeat7)){
            seatRestaurantService.createSeat(barSeat7);
        }
        if(isSeatNumberUnique(barSeat8)){
            seatRestaurantService.createSeat(barSeat8);
        }
        if(isSeatNumberUnique(barSeat9)){
            seatRestaurantService.createSeat(barSeat9);
        }
        if(isSeatNumberUnique(barSeat10)){
            seatRestaurantService.createSeat(barSeat10);
        }
        if(isSeatNumberUnique(barSeat11)){
            seatRestaurantService.createSeat(barSeat11);
        }
    }
}
