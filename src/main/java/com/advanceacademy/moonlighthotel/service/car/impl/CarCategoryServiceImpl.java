package com.advanceacademy.moonlighthotel.service.car.impl;

import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.repository.car.CarCategoryRepository;
import com.advanceacademy.moonlighthotel.service.car.CarCategoryService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class CarCategoryServiceImpl implements CarCategoryService {
    private final CarCategoryRepository carCategoryRepository;

    public CarCategoryServiceImpl(CarCategoryRepository carCategoryRepository) {
        this.carCategoryRepository = carCategoryRepository;
    }

    @Override
    public Optional<CarCategory> getById(Long id) {
       return carCategoryRepository.findById(id);
    }

    @Override
    public List<CarCategory> getAllCarCategory() {
        return carCategoryRepository.findAll();
    }

    @Override
    public CarCategory addCategory(CarCategory carCategory) {
        Optional<CarCategory> savedCategory=carCategoryRepository.findById(carCategory.getId());
        if(savedCategory.isPresent()){
            throw new EntityNotFoundException("Car category with Id# "+carCategory.getId()+" has already existed");
        }
        return carCategoryRepository.save(carCategory);
    }

    @Override
    public CarCategory updateCategory(CarCategory updatedCarCategory) {
        return carCategoryRepository.save(updatedCarCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
        carCategoryRepository.deleteById(id);
    }
}
