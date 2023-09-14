package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatRestaurantService;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RestaurantCommandRunner implements CommandLineRunner {

    private final TableRestaurantService tableRestaurantService;
    private final SeatRestaurantService seatRestaurantService;

    @Autowired
    public RestaurantCommandRunner(TableRestaurantService tableRestaurantService, SeatRestaurantService seatRestaurantService) {
        this.tableRestaurantService = tableRestaurantService;
        this.seatRestaurantService = seatRestaurantService;
    }

    @Override
    public void run(String... args) throws Exception {

        TableRestaurant table1 = new TableRestaurant();
        table1.setId(1L);
        table1.setZone(RestaurantZone.SALOON);
        table1.setIsSmoking(false);
        table1.setSeats(4);

        TableRestaurant table2 = new TableRestaurant();
        table2.setId(2L);
        table2.setZone(RestaurantZone.SALOON);
        table2.setIsSmoking(false);
        table2.setSeats(4);

        TableRestaurant table3 = new TableRestaurant();
        table3.setId(3L);
        table3.setZone(RestaurantZone.SALOON);
        table3.setIsSmoking(false);
        table3.setSeats(4);

        TableRestaurant table4 = new TableRestaurant();
        table4.setId(4L);
        table4.setZone(RestaurantZone.SALOON);
        table4.setIsSmoking(false);
        table4.setSeats(4);

        TableRestaurant table5 = new TableRestaurant();
        table5.setId(5L);
        table5.setZone(RestaurantZone.SALOON);
        table5.setIsSmoking(false);
        table5.setSeats(4);

        TableRestaurant table6 = new TableRestaurant();
        table6.setId(6L);
        table6.setZone(RestaurantZone.SALOON);
        table6.setIsSmoking(false);
        table6.setSeats(2);

        TableRestaurant table7 = new TableRestaurant();
        table7.setId(7L);
        table7.setZone(RestaurantZone.SALOON);
        table7.setIsSmoking(false);
        table7.setSeats(7);

        TableRestaurant table8 = new TableRestaurant();
        table8.setId(8L);
        table8.setZone(RestaurantZone.SALOON);
        table8.setIsSmoking(false);
        table8.setSeats(7);

        TableRestaurant table9 = new TableRestaurant();
        table9.setId(9L);
        table9.setZone(RestaurantZone.SALOON);
        table9.setIsSmoking(false);
        table9.setSeats(8);

        TableRestaurant table10 = new TableRestaurant();
        table10.setId(10L);
        table10.setZone(RestaurantZone.SALOON);
        table10.setIsSmoking(false);
        table10.setSeats(2);

        TableRestaurant table11 = new TableRestaurant();
        table11.setId(11L);
        table11.setZone(RestaurantZone.TERRACE);
        table11.setIsSmoking(true);
        table11.setSeats(4);

        TableRestaurant table12 = new TableRestaurant();
        table12.setId(12L);
        table12.setZone(RestaurantZone.TERRACE);
        table12.setIsSmoking(true);
        table12.setSeats(4);

        TableRestaurant table13 = new TableRestaurant();
        table13.setId(13L);
        table13.setZone(RestaurantZone.TERRACE);
        table13.setIsSmoking(true);
        table13.setSeats(4);

        TableRestaurant table14 = new TableRestaurant();
        table14.setId(14L);
        table14.setZone(RestaurantZone.TERRACE);
        table14.setIsSmoking(true);
        table14.setSeats(4);

        TableRestaurant table15 = new TableRestaurant();
        table15.setId(15L);
        table15.setZone(RestaurantZone.TERRACE);
        table15.setIsSmoking(true);
        table15.setSeats(4);

        TableRestaurant table16 = new TableRestaurant();
        table16.setId(16L);
        table16.setZone(RestaurantZone.TERRACE);
        table16.setIsSmoking(true);
        table16.setSeats(4);

        tableRestaurantService.createTable(table1);
        tableRestaurantService.createTable(table2);
        tableRestaurantService.createTable(table3);
        tableRestaurantService.createTable(table4);
        tableRestaurantService.createTable(table5);
        tableRestaurantService.createTable(table6);
        tableRestaurantService.createTable(table7);
        tableRestaurantService.createTable(table8);
        tableRestaurantService.createTable(table9);
        tableRestaurantService.createTable(table10);
        tableRestaurantService.createTable(table11);
        tableRestaurantService.createTable(table12);
        tableRestaurantService.createTable(table13);
        tableRestaurantService.createTable(table14);
        tableRestaurantService.createTable(table15);
        tableRestaurantService.createTable(table16);



        SeatRestaurant barSeat1 = new SeatRestaurant();
        barSeat1.setId(1L);
        barSeat1.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat2 = new SeatRestaurant();
        barSeat2.setId(2L);
        barSeat2.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat3 = new SeatRestaurant();
        barSeat3.setId(3L);
        barSeat3.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat4 = new SeatRestaurant();
        barSeat4.setId(4L);
        barSeat4.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat5 = new SeatRestaurant();
        barSeat5.setId(5L);
        barSeat5.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat6 = new SeatRestaurant();
        barSeat6.setId(6L);
        barSeat6.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat7 = new SeatRestaurant();
        barSeat7.setId(7L);
        barSeat7.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat8 = new SeatRestaurant();
        barSeat8.setId(8L);
        barSeat8.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat9 = new SeatRestaurant();
        barSeat9.setId(9L);
        barSeat9.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat10 = new SeatRestaurant();
        barSeat10.setId(10L);
        barSeat10.setZone(RestaurantZone.BAR);
        SeatRestaurant barSeat11 = new SeatRestaurant();
        barSeat11.setId(11L);
        barSeat11.setZone(RestaurantZone.BAR);

        seatRestaurantService.createSeat(barSeat1);
        seatRestaurantService.createSeat(barSeat2);
        seatRestaurantService.createSeat(barSeat3);
        seatRestaurantService.createSeat(barSeat4);
        seatRestaurantService.createSeat(barSeat5);
        seatRestaurantService.createSeat(barSeat6);
        seatRestaurantService.createSeat(barSeat7);
        seatRestaurantService.createSeat(barSeat8);
        seatRestaurantService.createSeat(barSeat9);
        seatRestaurantService.createSeat(barSeat10);
        seatRestaurantService.createSeat(barSeat11);

    }
}
