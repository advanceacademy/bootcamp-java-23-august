package com.advanceacademy.moonlighthotel.service.impl;

import com.advanceacademy.moonlighthotel.repository.TableRestaurantRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TableRestaurantServiceImplTest {

    @Mock
    private TableRestaurantRepository tableRestaurantRepository ;

    @InjectMocks
    private TableRestaurantServiceImplTest restaurantServiceImplTest ;

}
