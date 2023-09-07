package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarServiceImpl;
import com.advanceacademy.moonlighthotel.service.car.impl.FileResourceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FileResourceServiceTest {
    @Mock
    private FileResourceRepository fileResourceRepository;




    @InjectMocks
    private FileResourceServiceImpl fileResourceService;
    private List<FileResource>  fileResourceList;
    private FileResource fileResource1;
    private FileResource fileResource2;
    private FileResource fileResource3;

    private Car car;
    private CarCategory carCategory;
    @BeforeEach
    public void setUp() {
        carCategory=CarCategory.builder()
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


        fileResource1 = FileResource.builder()
                .id(1L)
                .car(car)
                .imageName("audi.jpg")
                .value(new byte[]{17, 23, 45, 78, 91, 16, 53, 69})
                .build();

        fileResource2 = FileResource.builder()
                .id(1L)
                .car(car)
                .imageName("audi2.jpg")
                .value(new byte[]{17, 23, 45, 96, 91, 83, 53, 69})
                .build();
        fileResource3 = FileResource.builder()
                .id(1L)
                .car(car)
                .imageName("audi.jpg")
                .value(new byte[]{17, 48, 45, 78, 91, 16, 92, 69})
                .build();
        fileResourceList=new ArrayList<>();
        fileResourceList.add(fileResource1);
        fileResourceList.add(fileResource2);
        fileResourceList.add(fileResource3);
    }

    @DisplayName("Test for car get by id")
    @Test
    public void testGetFileResourceById(){
        given(fileResourceRepository.findByCarId(1L)).willReturn(Optional.of(fileResourceList));
        List<FileResource> gotResource=fileResourceService.findByCarId(car.getId()).get();
        assertThat(gotResource).isNotNull();

    }
}
