package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarTransferRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarTransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarTransferServiceTest {
       @Mock
    private CarTransferRepository carTransferRepository;
       @Mock
       private UserRepository userRepository;
       @Mock
       private CarRepository carRepository;


    @InjectMocks
    private CarTransferServiceImpl carTransferService;
    
    private CarTransfer carTransfer;
    private User user;
    private UserRole userRole;
    private CarCategory carCategory;
    private Car car;
    private LocalDate localDate;





    @BeforeEach
    public void setUp(){
        userRole=UserRole
                .builder().id(1L)
                .userRole("Regular User")
                .build();
        carCategory= CarCategory.builder()
                .id(1L)
                .carTypes(CarType.SEDAN)
                .seats(5)
                .pricePerDay(800.0)
                .build();
        car= Car.builder()
                .id(1L)
                .make("Audi")
                .model("A8")
                .year(2021)
                .carCategory(carCategory)
                .build();
        user= User.builder()
                .id(1L)
                .userRole(userRole)
                .firstName("Georgi")
                .lastName("Petrov")
                .phoneNumber("0883408901")
                .password("a7*bhY8@b+")
                .email("myEmail@myemai.com")
                .createdDate(LocalDate.of(2023,8,2))
                .build();


        carTransfer=CarTransfer.builder()
                .id(1L)
                .car(car)
                .price(100.0)
                .paymentStatus(PaymentStatus.PAID)
                .date(LocalDate.of(2023,8,23))
                .user(user)
                .build();


    }
    @DisplayName("Test for added car transfer")
    @Test
    public void testAddCarTransfer(){
        given(carTransferRepository.findById(carTransfer.getId())).willReturn(Optional.empty());
        given(carTransferRepository.save(carTransfer)).willReturn(carTransfer);
        CarTransfer addedCarTransfer = carTransferService.addCarTransfer(carTransfer);
        assertThat(addedCarTransfer).isNotNull();
    }
    @DisplayName("Test for list all car transfers")
    @Test
    public void testGetAllCarTransfers(){
       CarTransfer carTransfer1=CarTransfer.builder()
               .car(car)
               .price(100.0)
               .paymentStatus(PaymentStatus.PAID)
               .date(LocalDate.of(2023,8,23))
               .user(user)
               .build();


        given(carTransferRepository.findAll()).willReturn(List.of(carTransfer,carTransfer1));
        List<CarTransfer> carTransferList = carTransferService.getAllCarTransfer();
        assertThat(carTransferList).isNotNull();
        assertThat(carTransferList.size()).isEqualTo(2);  }
    @DisplayName("Test for list all cars")
    @Test
    public void testGetCarTransferById(){
        given(carTransferRepository.findById(1L)).willReturn(Optional.of(carTransfer));
        CarTransfer gotCarTransfer = carTransferService.getTransferByID(carTransfer.getId()).get();
        assertThat(gotCarTransfer).isNotNull();

    }
    @DisplayName("Test for updated car transfer date and price by id")
    @Test
    public void testUpdateCarTransfer(){
        given(carTransferRepository.save(carTransfer)).willReturn(carTransfer);

        carTransfer.setDate(LocalDate.of(2023,8,27));
        carTransfer.setPrice(100.0);

        CarTransfer updatedCategoryPrice= carTransferService.updateCarTransfer(carTransfer);
        assertThat(updatedCategoryPrice.getDate()).isEqualTo(LocalDate.of(2023,8,27));
        assertThat(updatedCategoryPrice.getPrice()).isEqualTo(100.0);

    }
    @DisplayName("Test for deleted car transfer by id")
    @Test
    public void testDeleteCarTransfer(){
        java.lang.Long carTransferId =1L;
        willDoNothing().given(carTransferRepository).deleteById(carTransferId);
        carTransferService.deleteTransferById(carTransferId);
        verify(carTransferRepository, times(1)).deleteById(carTransferId);
    }

}
