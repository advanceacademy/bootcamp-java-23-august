package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.car.CarCategory;

import java.util.List;
import java.util.Optional;

public interface CarCategoryService {
    //Retrieve
   Optional<CarCategory> getById(Long id);
   List<CarCategory> getAllCarCategory();
    //Create
    CarCategory addCategory(CarCategory carCategory);
    //Update
   CarCategory updateCategory (CarCategory carCategory);
   //Delete
    void deleteCategoryById(Long id);

}
