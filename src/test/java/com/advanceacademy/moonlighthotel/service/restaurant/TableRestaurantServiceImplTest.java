package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.repository.restaurant.TableRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.impl.TableRestaurantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TableRestaurantServiceImplTest {

    @Mock
    private TableRestaurantRepository tableRestaurantRepository ;

    @InjectMocks
    private TableRestaurantServiceImpl tableRestaurantService ;

    @Test
    public void testCreateTable() {
        TableRestaurant tableRestaurant = new TableRestaurant();
        tableRestaurant.setId(1L);
        tableRestaurant.setZone(RestaurantZone.SALOON);

        when(tableRestaurantRepository.save(any(TableRestaurant.class))).thenReturn(tableRestaurant);

        TableRestaurant createdTable = tableRestaurantService.createTable(tableRestaurant);

        Assertions.assertNotNull(createdTable);
        Assertions.assertEquals(tableRestaurant, createdTable);

        verify(tableRestaurantRepository, times(1)).save(tableRestaurant);

        assertEquals(tableRestaurant, createdTable);
    }


    @Test
    public void testGetTableById_WhenTableExists(){
        Long tableId = 1L;
        TableRestaurant expectedTable = new TableRestaurant();
        expectedTable.setId(tableId);

        when(tableRestaurantRepository.findById(tableId)).thenReturn(Optional.of(expectedTable));

        TableRestaurant resultTable = tableRestaurantService.getTableById(tableId);

        assertEquals(expectedTable, resultTable);
    }



//    @Test
//    public void testGetTableById_WhenTableDoesNotExist() {
//        Long tableId = 1L;
//
//        when(tableRestaurantRepository.findById(tableId)).thenReturn(Optional.empty());
//
//        TableRestaurant resultTable = tableRestaurantService.getTableById(tableId);
//        assertNull(resultTable);
//
//        verify(tableRestaurantRepository, times(1)).findById(tableId);
//    }


    @Test
    void getTableByNumber(){
        TableRestaurant tableRestaurant = new TableRestaurant();
        tableRestaurant.setNumber(1);
        Integer tableNumber = tableRestaurant.getNumber();

        when(tableRestaurantRepository.findByNumber(tableNumber)).thenReturn(Optional.of(tableRestaurant));

        TableRestaurant foundTableRestaurant = tableRestaurantService.getTableByNumber(tableNumber);

        Assertions.assertNotNull(foundTableRestaurant);
        Assertions.assertEquals(tableRestaurant, foundTableRestaurant);

        verify(tableRestaurantRepository, times(1)).findByNumber(tableNumber);

    }

    @Test
    public void testGetSmokingTables() {
        Boolean isSmoking = true;
        when(tableRestaurantRepository.findByIsSmoking(isSmoking)).thenReturn(Arrays.asList(new TableRestaurant(), new TableRestaurant()));

        List<TableRestaurant> tables = tableRestaurantService.getSmokingTables(isSmoking);

        assertNotNull(tables);
        assertEquals(2, tables.size());

        verify(tableRestaurantRepository, times(1)).findByIsSmoking(isSmoking);
    }


    @Test
    public void testGetTablesBySeats() {
        Integer seats = 4;
        when(tableRestaurantRepository.findBySeats(seats)).thenReturn(Arrays.asList(new TableRestaurant(), new TableRestaurant()));

        List<TableRestaurant> tables = tableRestaurantService.getTablesBySeats(seats);

        assertNotNull(tables);
        assertEquals(2, tables.size());

        verify(tableRestaurantRepository, times(1)).findBySeats(seats);
    }



    @Test
    public void testGetTablesByZone() {
        RestaurantZone zone = RestaurantZone.BAR;
        TableRestaurant table1 = new TableRestaurant();
        table1.setId(1L);
        table1.setZone(zone);
        TableRestaurant table2 = new TableRestaurant();
        table2.setId(2L);
        table2.setZone(zone);

        List<TableRestaurant> expectedTables = Arrays.asList(table1, table2);

        when(tableRestaurantRepository.findByZone(zone)).thenReturn(expectedTables);

        List<TableRestaurant> actualTables = tableRestaurantService.getTablesByZone(zone);

        assertEquals(expectedTables.size(), actualTables.size());
        assertEquals(expectedTables, actualTables);

    }

}













