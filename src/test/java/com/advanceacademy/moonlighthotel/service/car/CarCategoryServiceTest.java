package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.service.car.impl.CarCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarCategoryServiceTest {
       @Mock
    private CarCategoryRepository carCategoryRepository;


    @InjectMocks
    private CarCategoryServiceImpl carCategoryService;
    
    private CarCategory carCategory;





    @BeforeEach
    public void setUp(){
        carCategory=CarCategory.builder()
                .id(1L)
                .carTypes(CarType.SEDAN)
                .seats(5)
                .pricePerDay(800.0)
                .build();
       
    }
    @DisplayName("Test for added category")
    @Test
    public void testAddCarCategory(){
        given(carCategoryRepository.findById(carCategory.getId())).willReturn(Optional.empty());
        given(carCategoryRepository.save(carCategory)).willReturn(carCategory);
        CarCategory addedCarCategory = carCategoryService.addCategory(carCategory);
        assertThat(addedCarCategory).isNotNull();
    }
    @DisplayName("Test for list of all car category")
    @Test
    public void testGetAllCarCategories(){
       CarCategory carCategory1=CarCategory.builder()
                .id(2L)
                .carTypes(CarType.SPORT)
                .seats(2)
                .pricePerDay(1000.0)
                .build();


        given(carCategoryRepository.findAll()).willReturn(List.of(carCategory,carCategory1));
        List<CarCategory> carCategoriesList= carCategoryService.getAllCarCategory();
        assertThat(carCategoriesList).isNotNull();
        assertThat(carCategoriesList.size()).isEqualTo(2);  }
    @DisplayName("Test for car category by id")
    @Test
    public void testGetCarCategoryById(){
        given(carCategoryRepository.findById(1L)).willReturn(Optional.of(carCategory));
        CarCategory gotCategory= carCategoryService.getById(carCategory.getId()).get();
        assertThat(gotCategory).isNotNull();

    }
    @DisplayName("Test for updated car category price by id")
    @Test
    public void testUpdatedCarCategory(){
        given(carCategoryRepository.save(carCategory)).willReturn(carCategory);

        carCategory.setPricePerDay(600.0);

        CarCategory updatedCategoryPrice= carCategoryService.updateCategory(carCategory);
        assertThat(updatedCategoryPrice.getPricePerDay()).isEqualTo(600.0);

    }
    @DisplayName("Test for deleted car category by id")
    @Test
    public void testDeleteCarCategory(){
        Long categoryId=1L;
        willDoNothing().given(carCategoryRepository).deleteById(categoryId);
        carCategoryService.deleteCategoryById(categoryId);
       verify(carCategoryRepository, times(1)).deleteById(categoryId);
    }

}
