package com.novozy.foodcateloguemicroservice.controller;

import com.novozy.foodcateloguemicroservice.dto.FoodCataloguePage;
import com.novozy.foodcateloguemicroservice.dto.FoodItemDTO;
import com.novozy.foodcateloguemicroservice.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> saveFoodItemDetails(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO itemSaved = foodCatalogueService.saveFoodItemDetails(foodItemDTO);
        return new ResponseEntity<>(itemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu (@PathVariable Integer restaurantId){
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
